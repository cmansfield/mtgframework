package io.github.cmansfield.io;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import org.apache.commons.lang.Validate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;


public final class UpdateCardList {
  private static final String GET_CARD_LIST_URL = "https://mtgjson.com/json/AllCards-x.json.zip";
  private static final String GET_VER_URL = "https://mtgjson.com/json/version-full.json";
  private static final String VERSION_FILE_NAME = "cardListVersion.json";
  private static final String VERSION_KEY = "version";
  private UpdateCardList() {}

  public static void checkForUpdates() {
    String currentVersionJson;
    String currentVersion;
    String myVersion;

    try {
      currentVersionJson = UpdateCardList.getCurrentVersionJson();
      currentVersion = UpdateCardList.parseVersionFromJson(currentVersionJson);
      myVersion = UpdateCardList.getMyVersion();
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return;
    }

    // check to see if the versions match
    if(currentVersion.equals(myVersion)) {
      System.out.println("No updates at this time");
      return;
    }

    try {
      // if not then call downloadLatestCardList
      UpdateCardList.downloadLatestCardList();

      // saveNewVersion
      UpdateCardList.saveNewVersion(currentVersionJson);
    }
    catch(Exception e) {
      System.out.printf("Unable to update at this time%n%s%n", e.getMessage());
      return;
    }

    System.out.println("Updated to version: " + currentVersion);
  }

  private static String getCurrentVersion() {
    return UpdateCardList.parseVersionFromJson(UpdateCardList.getCurrentVersionJson());
  }

  private static String getCurrentVersionJson() {
    Client client = Client.create();
    String response;

    try {
      WebResource webResource = client.resource(GET_VER_URL);
      response = webResource.get(String.class);
    }
    catch(UniformInterfaceException e) {
      throw new RuntimeException(String.format("Unable to get card list version at this time%n%s", e.getResponse()));
    }

    return response;
  }

  private static String getMyVersion() {
    File file = new File(VERSION_FILE_NAME);
    String myVersion;

    if(!file.exists()) {
      return "";
    }

    try {
      myVersion = FileUtils.readFileToString(file, "UTF-8");
    }
    catch(IOException e) {
      System.out.printf("Unable to read from file %s%n%s%n", VERSION_FILE_NAME, e.getMessage());
      return "";
    }

    return UpdateCardList.parseVersionFromJson(myVersion);
  }

  private static String parseVersionFromJson(final String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    String version;

    try {
      JsonNode jsonNode = objectMapper.readTree(json);
      version = jsonNode.get(VERSION_KEY).asText();
    }
    catch(Exception e) {
      System.out.printf("Unable to read the json version at this time%n%s%n", e.getMessage() == null ? "" : e.getMessage());
      return "";
    }

    return version;
  }

  private static void downloadLatestCardList() throws IOException {
    final int TIMEOUT = 500;
    File file = new File(CardListConstants.CARD_LIST_FILE_NAME);
    URL url = null;

    try {
      url = new URL(GET_CARD_LIST_URL);
      FileUtils.copyURLToFile(url, file, TIMEOUT, TIMEOUT);
    }
    catch(MalformedURLException e) {
      System.out.printf("Unable create URL object with url: %s%n", GET_CARD_LIST_URL);
      throw e;
    }
    catch(IOException e) {
      System.out.printf("Unable to save '%s' to file '%s'%n", url.getPath(), CardListConstants.CARD_LIST_FILE_NAME);
      throw e;
    }
  }

  private static void saveNewVersion(final String version) throws IOException {
    File file = new File(VERSION_FILE_NAME);
    FileWriter fileWriter = null;

    Validate.notEmpty(version);

    try {
      file.createNewFile();

      fileWriter = new FileWriter(file);
      fileWriter.write(version);
      fileWriter.flush();
    }
    catch (IOException e) {
      System.out.printf("Unable to create/save file '%s' at this time%n", VERSION_FILE_NAME);
      System.out.println(e);
      throw e;
    }
    finally {
      IOUtils.closeQuietly(fileWriter);
    }
  }
}
