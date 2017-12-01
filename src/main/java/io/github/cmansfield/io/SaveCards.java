package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.constants.Legality;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


public final class SaveCards {
  private SaveCards() {}

  /**
   * Saves a list of card objects to a predefined output location
   *
   * @param cards - List of cards to save as a json file
   * @return      - Returns a string with the filepath and filename
   * @throws IOException
   */
  public static String saveCards(List<Card> cards) throws IOException {
    final String CARD_LIST_SAVE_NAME = "CardList%d.json";
    ObjectMapper mapper = new ObjectMapper();
    File saveFolder = createSaveDir();

    String saveFileName = IoConstants.SAVE_DIR + "/" + String.format(CARD_LIST_SAVE_NAME, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {

      Map<String, Object> cardMap = cards.stream()
              .collect(Collectors.toMap(Card::getName, card -> card.getCardPojo()));

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
