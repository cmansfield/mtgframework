package io.github.cmansfield.io.web;

import io.github.cmansfield.validator.DeckValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.DeckWriter;
import io.github.cmansfield.io.CardReader;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.*;


public final class TappedImporter {

  private static final Logger LOGGER = LoggerFactory.getLogger(TappedImporter.class);

  private TappedImporter() {}

  /**
   * Iterates over all json objects in the supplied directory, creates a list of deck objects
   *
   * @param directory     - File directory full of tappedout json files
   * @return              - List of decks generated from the json files
   */
  public static List<Deck> importFilesFromTappedOut(String directory) {
    File folder = new File(directory);
    File[] fileArray = folder.listFiles();
    List<Deck> decks = new ArrayList<>();

    if(fileArray == null) {
      return Collections.emptyList();
    }

    List<File> files = Arrays.asList(fileArray);
    ObjectMapper mapper = new ObjectMapper();

    files.forEach(file -> {
      if(!file.isFile()) {
        return;
      }
      try(InputStream inputStream = new FileInputStream(file.getAbsolutePath())) {
        //noinspection unchecked
        Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
        Deck deck = createDeckFromJson(jsonMap);
        if(deck != null) {
          decks.add(deck);
        }
      }
      catch(Exception e) {
        LOGGER.error("Unable to load file {}", file.getName(), e);
        throw new RuntimeException(e);    // NOSONAR
      }

    });

    return decks;
  }

  /**
   * Generates a deck object from a supplied jsonMap object
   *
   * @param jsonMap - jsonMap object created from the json file
   * @return        - A deck object generated from the jsonMap
   */
  private static Deck createDeckFromJson(Map<String, Object> jsonMap) {
    List<Card> featuredCards = null;
    List inventory = (List)jsonMap.get(IoConstants.INVENTORY_KEY);

    if(jsonMap.containsKey(IoConstants.FEATURED_KEY)) {
      featuredCards = Collections.singletonList(CardReader.lookupCard((String)jsonMap.get(IoConstants.FEATURED_KEY)));
    }

    Map<String,Integer> cardMap = new HashMap<>();

    //noinspection unchecked
    inventory.forEach(list -> {
      String name;
      int qty;

      Object obj0 = ((List)list).get(0);
      Object obj1 = ((List)list).get(1);

      if(obj0 instanceof String) {
        name = (String)obj0;
        qty = (int)((Map)obj1).get(IoConstants.QUANTITY_KEY);
      }
      else {
        name = (String)obj1;
        qty = (int)((Map)obj0).get(IoConstants.QUANTITY_KEY);
      }

      cardMap.put(name, qty);
    });

    List<Card> cards = new ArrayList<>();
    cardMap.forEach((k, v) -> {
      for(int i = 0; i < v; ++i) {
        Card foundCard = CardReader.lookupCard(k);
        if(foundCard != null) {
          cards.add(foundCard);
        }
      }
    });

    Deck deck = new Deck(cards);
    if(featuredCards != null) {
      deck.setFeaturedCards(featuredCards);
      deck.setFormat(Format.COMMANDER);
    }

    return deck;
  }

  /**
   * This method will generate a List of Decks from the supplied directory's json files
   * and then will save only the decks that are format compliant
   * 
   * @param directory     The folder directory where the json deck lists are stored
   */
  public static void saveFormatCompliantDecksFromTappedOut(String directory) {
    List<Deck> decks = TappedImporter.importFilesFromTappedOut(directory);
    decks.forEach(deck -> {
      try{
        DeckValidator.isFormatCompliant(deck);
        DeckWriter.saveDeck(deck);
      }
      catch(Exception e) {
        LOGGER.error("Unable to save decks from TappedOut deck lists", e);
      }
    });
  }
}
