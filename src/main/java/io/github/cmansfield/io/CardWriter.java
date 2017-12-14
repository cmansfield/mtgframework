package io.github.cmansfield.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;


public final class CardWriter {
  private CardWriter() {}

  /**
   * Saves a list of card objects to a predefined output location
   *
   * @param cards - List of cards to save as a json file
   * @return      - Returns a string with the filepath and filename
   * @throws IOException
   */
  public static String saveCards(List<Card> cards) throws IOException {
    final String cardListSaveName = "CardList%d.json";
    ObjectMapper mapper = new ObjectMapper();
    File saveFolder = createSaveDir();

    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    String saveFileName = IoConstants.SAVE_DIR + File.separator + String.format(cardListSaveName, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {

      Map<String, Card> cardMap = cards.stream()
              .collect(Collectors.toMap(Card::getName, card -> card));

      String jsonCards = mapper.writerWithDefaultPrettyPrinter()
              .writeValueAsString(cardMap);

      writer.write(jsonCards);
    }
    catch (Exception e) {
      System.out.printf("Unable to save the card list at this time%n%s%n", e.getMessage());
      throw new IOException(e);
    }

    return saveFileName;
  }

  /**
   * Creates the predetermined save directory
   *
   * @return - Returns a File object of the newly created directory
   */
  static File createSaveDir() {
    File saveFolder = new File(IoConstants.SAVE_DIR);

    try {
      // Try to create the save directory
      saveFolder.mkdir();
    }
    catch (Exception e) {
      System.out.printf("Unable to create directory '%s' at this time%n", IoConstants.SAVE_DIR);
    }

    return saveFolder;
  }
}
