package io.github.cmansfield.io.web;

import io.github.cmansfield.deck.constants.Legality;
import io.github.cmansfield.validator.DeckValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.CardWriter;
import io.github.cmansfield.io.DeckWriter;
import io.github.cmansfield.io.CardReader;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.*;


public final class TappedImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(TappedImporter.class);
  private static final int QUANTITY_THRESHOLD = 2;

  private TappedImporter() {}

  /**
   * Iterates over all json objects in the supplied directory, creates a list of deck objects
   *
   * @param directory     - File directory full of tappedout json files
   * @return              - List of decks generated from the json files
   */
  private static List<Deck> importFilesFromTappedOut(String directory) {
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
   * This method will load the json files from TappedOut into a LinkedHashMap
   *
   * @param directory     The directory where all of the json files are stored
   * @return              A LinkedHashMap of all of the loaded cards and the number of times
   *                      They were used
   */
  private static LinkedHashMap<Card, Integer> importFilesFromTappedOutIntoMap(String directory) {
    File folder = new File(directory);
    File[] fileArray = folder.listFiles();
    LinkedHashMap<Card, Integer> cardMap = new LinkedHashMap<>();

    if(fileArray == null) {
      return cardMap;
    }
    ObjectMapper mapper = new ObjectMapper();

    Arrays.stream(fileArray).forEach(file -> {
      if(!file.isFile()) {
        return;
      }
      LOGGER.info("......Importing file '{}'", file.getName());
      try(InputStream inputStream = new FileInputStream(file.getAbsolutePath())) {
        //noinspection unchecked
        Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
        populateCardMapFromJson(cardMap, jsonMap);
      }
      catch(Exception e) {
        LOGGER.error("Unable to load file {}", file.getName(), e);
        throw new RuntimeException(e);    // NOSONAR
      }
    });

    return cardMap;
  }

  /**
   * Generates a deck object from a supplied jsonMap object
   *
   * @param jsonMap - jsonMap object created from the json file
   * @return        - A deck object generated from the jsonMap
   */
  private static Deck createDeckFromJson(Map<String, Object> jsonMap) {
    LinkedHashMap<Card, Integer> cardMap = new LinkedHashMap<>();
    populateCardMapFromJson(cardMap, jsonMap);
    List<Card> featuredCards = getFeaturedCards(jsonMap);

    List<Card> cards = new ArrayList<>();
    cardMap.forEach((card, qty) -> {
      for(int i = 0; i < qty; ++i) {
        cards.add(card);
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
   * This will get the featured cards from the supplied jsonMap
   *
   * @param jsonMap     A key-value map of the original json object
   * @return            A list of featured cards
   */
  private static List<Card> getFeaturedCards(Map<String, Object> jsonMap) {
    List<Card> featuredCards = Collections.emptyList();

    if(jsonMap.containsKey(IoConstants.FEATURED_KEY)) {
      featuredCards = Collections.singletonList(CardReader.lookupCard((String)jsonMap.get(IoConstants.FEATURED_KEY)));
    }

    return featuredCards;
  }

  /**
   * This will populate the supplied map with the new values found in the jsonMap
   *
   * @param jsonMap     A key-value map of the original json object
   */
  private static void populateCardMapFromJson(Map<Card, Integer> cardMap, Map<String, Object> jsonMap) {
    List inventory = (List)jsonMap.get(IoConstants.INVENTORY_KEY);

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

      cardMap.merge(CardReader.lookupCard(name), qty, (a, b) -> a + b);
    });
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

  /**
   * This will import all of the cards from the supplied folders and store them and their
   * quantity into a map
   *
   * @param maxNumCards   The max number of unique cards to store
   * @param folders       The array of folders to import from
   * @return              A map of all of the imported cards and their quantities
   */
  public static Map<Card, Integer> importFromTappedOutFolders(int maxNumCards, Card featuredCard, String... folders) throws IOException {
    LinkedHashMap<Card, Integer> cardMap = new LinkedHashMap<>();

    if(featuredCard == null) {
      LOGGER.warn("The featured card cannot be null");
      return cardMap;
    }

    LOGGER.info("Importing from TappedOut deck lists could take several minutes");

    for(String folder : folders) {
      LOGGER.info("...Importing from folder '{}'", folder);
      for(Map.Entry<Card, Integer> entry : importFilesFromTappedOutIntoMap(folder).entrySet()) {
        cardMap.merge(entry.getKey(), entry.getValue(), (a, b) -> a + b);
      }
    }

    List<String> colors = Arrays.stream(Color.values())
            .filter(color -> color != Color.COLORLESS)
            .map(Object::toString)
            .collect(Collectors.toList());
    colors.removeAll(featuredCard.getColors());

    cardMap = cardMap.entrySet().stream()
            .filter(entry -> entry.getKey().getLegalities().stream()
                    .anyMatch(map -> {
                      if(!Format.COMMANDER.toString().equals(map.get(Legality.FORMAT.toString()))) {
                        return false;
                      }
                      return "Legal".equals(map.get(Legality.LEGALITY.toString()));
                    }))
            .filter(entry -> entry.getKey().getColors().stream()
                      .noneMatch(colors::contains))
            .filter(entry -> entry.getValue() > QUANTITY_THRESHOLD)
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(maxNumCards)
            .collect(LinkedHashMap::new,
                    (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                    Map::putAll);

    CardWriter.saveCards(cardMap.entrySet().stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList()));

    return cardMap;
  }
}
