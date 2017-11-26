package io.github.cmansfield.io.web;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import io.github.cmansfield.io.IoConstants;
import org.apache.commons.lang.Validate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;


public final class GetUpdates {
  private static final String GET_CARD_LIST_URL = "https://mtgjson.com/json/AllCards-x.json.zip";
  private static final String GET_VER_URL = "https://mtgjson.com/json/version-full.json";
  private static final String VERSION_FILE_NAME = "cardListVersion.json";
  private static final String VERSION_KEY = "version";
  private GetUpdates() {}

  /**
   * Checks for a new card list from the web
   */
  public static void checkForUpdates() {
    String currentVersionJson;
    String currentVersion;
    String myVersion;

    try {
      currentVersionJson = GetUpdates.getCurrentVersionJson();
      currentVersion = GetUpdates.parseVersionFromJson(currentVersionJson);
      myVersion = GetUpdates.getMyVersion();
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
      GetUpdates.downloadLatestCardList();

      // saveNewVersion
      GetUpdates.saveNewVersion(currentVersionJson);
    }
    catch(Exception e) {
      System.out.printf("Unable to update at this time%n%s%n", e.getMessage());
      return;
    }

    System.out.println("Updated to version: " + currentVersion);
  }

  /**
   * Gets the latest version number from the web
   *
   * @return - String of the latest card list version
   */
  private static String getCurrentVersion() {
    return GetUpdates.parseVersionFromJson(GetUpdates.getCurrentVersionJson());
  }

  /**
   * Gets the latest version number from the web
   *
   * @return - String of the latest card list version
   */
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

  /**
   * Gets the version of the card list that the program has stored
   *
   * @return - String of the card list version the program currently has
   */
  private static String getMyVersion() {
    File file = new File(VERSION_FILE_NAME);
    String myVersion;

    if(!file.exists()) {
      return "";
    }

    try {
      myVersion = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
    catch(IOException e) {
      System.out.printf("Unable to read from file %s%n%s%n", VERSION_FILE_NAME, e.getMessage());
      return "";
    }

    return GetUpdates.parseVersionFromJson(myVersion);
  }

  /**
   * Gets a version number from a json string
   *
   * @param json  - A json string that contains a version number
   * @return      - String of the card list version pulled from the json supplied
   */
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

  /**
   * Downloads the latest card list from mtgjson.com
   *
   * @throws IOException
   */
  private static void downloadLatestCardList() throws IOException {
    final int TIMEOUT = 500;
    File file = new File(IoConstants.CARD_LIST_FILE_NAME);
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
      System.out.printf("Unable to save '%s' to file '%s'%n", url.getPath(), IoConstants.CARD_LIST_FILE_NAME);
      throw e;
    }
  }

  /**
   * Saves a supplied version to a json file
   *
   * @param version - Version to save to a json file
   * @throws IOException
   */
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
