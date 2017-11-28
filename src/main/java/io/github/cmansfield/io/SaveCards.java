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
   * Saves a Deck object as a json file
   *
   * @param deck  - The Deck object to be saved as a json file
   * @return      - Returns a string with the filepath and filename
   * @throws IOException
   */
  public static String saveDeck(Deck deck) throws IOException {
    final String DECK_SAVE_NAME = "Deck%d.json";
    ObjectMapper mapper = new ObjectMapper();
    File saveFolder = createSaveDir();

    String saveFileName = IoConstants.SAVE_DIR + "/" + String.format(DECK_SAVE_NAME, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {

      Map<String, Object> cardMap = new HashMap<>();

      deck.getCards().forEach(card -> {
        for(int i = 0; i < deck.getQuantity(card.getName()); ++i) {
          cardMap.put(String.format("%s%d", card.getName(), i), card.getCardPojo());
        }
      });

      Map<String, Object> deckMap = new HashMap<>();
      if(deck.getFeaturedCard() != null) {
        deckMap.put(IoConstants.FEATURED_KEY, deck.getFeaturedCard().getCardPojo());
      }
      if(deck.getFormat() != null) {
        deckMap.put(Legality.FORMAT.toString(), deck.getFormat().toString());
      }
      deckMap.put(IoConstants.CARDS_KEY, cardMap);

      String jsonCards = mapper.writerWithDefaultPrettyPrinter()
              .writeValueAsString(deckMap);

      writer.write(jsonCards);
    }
    catch (Exception e) {
      System.out.printf("Unable to save the deck at this time%n%s%n", e.getMessage());
      throw new IOException(e);
    }

    return saveFileName;
  }

  /**
   * Saves the deck list in a 'raw' text format with only the card name and quantity
   * This format can be used in places like http://tappedout.net
   *
   * @param deck  - Deck to save as a simple string format
   * @return      - The filename of the newly saved file
   * @throws IOException
   */
  public static String saveDeckRaw(Deck deck) throws IOException {
    final String DECK_SAVE_NAME = "Deck%d.txt";
    ObjectMapper mapper = new ObjectMapper();
    File saveFolder = createSaveDir();

    String saveFileName = IoConstants.SAVE_DIR + "/" + String.format(DECK_SAVE_NAME, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {
      writer.write(deck.toString());
    }
    catch (Exception e) {
      System.out.printf("Unable to save the deck at this time%n%s%n", e.getMessage());
      throw new IOException(e);
    }

    return saveFileName;
  }

  /**
   * Creates the predetermined save directory
   *
   * @return - Returns a File object of the newly created directory
   */
  private static File createSaveDir() {
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
