package io.github.cmansfield;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;

import java.io.*;


public final class UpdateCardList {
  private static final String GET_VER_URL = "https://mtgjson.com/json/version-full.json";
  private static final String VERSION_FILE_NAME = "cardListVersion.txt";
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

    if(!UpdateCardList.promptUserToUpdate()) {
      return;
    }

    // if not then call downloadLatestCardList
    UpdateCardList.downloadLatestCardList();

    // saveNewVersion
    UpdateCardList.saveNewVersion(currentVersionJson);

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
      System.out.printf("Unable to read the json version at this time%n%s%n", e.getMessage());
      return "";
    }

    return version;
  }

  // TODO
  private static void downloadLatestCardList() {


  }

  private static boolean promptUserToUpdate() {
    final String VALID_POSITIVE_INPUT = "yes";
    final String VALID_NEGATIVE_INPUT = "no";
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = "";

    System.out.println("Looks like there is a new card list available");
    System.out.println("Would you like to download it now?");

    do {
      System.out.printf("Please enter '%s' or '%s'%n", VALID_POSITIVE_INPUT, VALID_NEGATIVE_INPUT);

      try {
        input = br.readLine();
      } catch (Exception e) { }
    } while(!input.equalsIgnoreCase(VALID_POSITIVE_INPUT) && !input.equalsIgnoreCase(VALID_NEGATIVE_INPUT));

    return input.equalsIgnoreCase(VALID_POSITIVE_INPUT);
  }

  private static void saveNewVersion(final String version) {
    File file = new File(VERSION_FILE_NAME);
    FileWriter fileWriter = null;

    Validate.notEmpty(version);

    if(!file.exists()) {
      try {
        if(!file.createNewFile()) {
          throw new IOException();
        }

        fileWriter = new FileWriter(file);
        fileWriter.write(version);
        fileWriter.flush();
      }
      catch (IOException e) {
        System.out.printf("Unable to create/save file '%s' at this time", VERSION_FILE_NAME);
        return;
      }
      finally {
        IOUtils.closeQuietly(fileWriter);
      }
    }
  }
}
