package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.deck.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


public final class DeckWriter {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeckWriter.class);

  private DeckWriter() {}

  /**
   * Saves a Deck object as a json file
   *
   * @param deck  - The Deck object to be saved as a json file
   * @return      - Returns a string with the filepath and filename
   * @throws IOException
   */
  public static String saveDeck(Deck deck) throws IOException {
    final String deckSaveName = "Deck%d.json";
    ObjectMapper mapper = new ObjectMapper();
    File saveFolder = CardWriter.createSaveDir();

    String saveFileName = IoConstants.SAVE_DIR + File.separator + String.format(deckSaveName, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {
      String jsonCards = mapper.writerWithDefaultPrettyPrinter()
              .writeValueAsString(deck);

      writer.write(jsonCards);
    }
    catch (Exception e) {
      LOGGER.error("Unable to save the deck at this time", e);
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
    final String deckSaveName = "Deck%d.txt";
    File saveFolder = CardWriter.createSaveDir();

    String saveFileName = IoConstants.SAVE_DIR + File.separator + String.format(deckSaveName, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {
      writer.write(deck.toString());
    }
    catch (Exception e) {
      LOGGER.error("Unable to save the deck at this time", e);
      throw new IOException(e);
    }

    return saveFileName;
  }
}
