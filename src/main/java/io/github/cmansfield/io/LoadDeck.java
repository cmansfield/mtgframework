package io.github.cmansfield.io;

import io.github.cmansfield.deck.constants.Legality;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.deck.constants.Format;
import org.apache.commons.io.FilenameUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


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
        Map<String, Object> jsonMap = mapper.readValue(inputstream, Map.class);

        if(jsonMap.containsKey(IoConstants.CARDS_KEY)) {
          deck = new Deck(LoadCards.loadCards((Map<String,Object>)jsonMap.get(IoConstants.CARDS_KEY), false));
        }
        else {
          return new Deck(LoadCards.loadCards(fileName));
        }

        if(jsonMap.containsKey(IoConstants.FEATURED_KEY)) {
          deck.setFeaturedCard(new Card(jsonMap.get(IoConstants.FEATURED_KEY)));
        }
        if(jsonMap.containsKey(Legality.FORMAT.toString())) {
          deck.setFormat(Format.find((String)jsonMap.get(Legality.FORMAT.toString())));
        }
      }
      catch(Exception e) {
        System.out.printf("Unable to load file %s%n", fileName);
        throw new IOException(e);
      }
    }

    return deck;
  }
}
