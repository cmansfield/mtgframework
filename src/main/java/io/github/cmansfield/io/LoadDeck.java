package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import io.github.cmansfield.deck.Deck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class LoadDeck {

  /**
   * Loads a json file into a Deck object
   *
   * @param fileName  - The filename of the file to load
   * @return          - Returns a Deck object loaded with cards from the specified file
   * @throws IOException
   */
  public static Deck loadDeck(final String fileName) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Deck deck = null;

    String extension = FilenameUtils.getExtension(fileName);

    if(extension.equalsIgnoreCase(IoConstants.TXT_EXT)) {
      return new Deck(LoadCards.loadCards(fileName));
    }

    if(extension.equalsIgnoreCase(IoConstants.JSON_EXT)) {
      try(InputStream inputstream = new FileInputStream(fileName)) {
        deck = mapper.readValue(inputstream, Deck.class);
      }
      catch(Exception e) {
        System.out.printf("Unable to load file %s%n", fileName);
        throw new IOException(e);
      }
    }

    return deck;
  }
}
