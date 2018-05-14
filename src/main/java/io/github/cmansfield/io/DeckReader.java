package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import io.github.cmansfield.deck.Deck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public final class DeckReader {

  private DeckReader() {}

  /**
   * Loads a json file into a Deck object
   *
   * @param fileName  - The filename of the file to load
   * @return          - Returns a Deck object loaded with cards from the specified file
   */
  public static Deck loadDeck(final String fileName) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Deck deck = null;

    String extension = FilenameUtils.getExtension(fileName);

    if(extension.equalsIgnoreCase(IoConstants.TXT_EXT)) {
      return new Deck(CardReader.loadCards(fileName));
    }

    if(extension.equalsIgnoreCase(IoConstants.JSON_EXT)) {
      try(InputStream inputstream = new FileInputStream(fileName)) {
        deck = mapper.readValue(inputstream, Deck.class);
      }
      catch(Exception e) {
        throw new IOException("Unable to load file " + fileName, e);
      }
    }

    return deck;
  }
}
