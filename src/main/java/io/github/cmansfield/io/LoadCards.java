package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;


public final class LoadCards {
  private static Map<String, Card> cardMap;

  private LoadCards() {}

  /**
   * Loads a list of cards into a List object
   *
   * @return - Returns a List of card objects from the loaded file
   * @throws IOException
   */
  public static List<Card> loadCards() throws IOException {
    List<Card> cards;

    if(LoadCards.cardMap != null) {
      return new ArrayList<Card>(LoadCards.cardMap.values());
    }

    ZipFile zip = new ZipFile(IoConstants.CARD_LIST_FILE_NAME);

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_CARDS_FILE_NAME))) {
      cards = loadCards(inputstream, true);
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", IoConstants.ALL_CARDS_FILE_NAME);
      throw new IOException(e);
    }
    finally {
      zip.close();
    }

    return cards;
  }

  /**
   * Loads a list of cards from the specified file
   *
   * @param fileName  - The filename of the file to load
   * @return          - Returns a List of card objects from the loaded file
   * @throws IOException
   */
  public static List<Card> loadCards(final String fileName) throws IOException {
    final String JSON_EXT = "json";
    final String TXT_EXT = "txt";
    List<Card> cards = null;

    String extension = FilenameUtils.getExtension(fileName);

    try(InputStream inputstream = new FileInputStream(fileName)) {
      if(extension.equalsIgnoreCase(JSON_EXT)) {
        cards = loadCards(inputstream, false);
      }
      if(extension.equalsIgnoreCase(TXT_EXT)) {
        String cardListRaw = IOUtils.toString(inputstream, StandardCharsets.UTF_8);
        cards = loadCardsFromString(cardListRaw);
      }
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", fileName);
      throw new IOException(e);
    }

    return cards;
  }

  /**
   * Loads a json file into a Deck object
   *
   * @param fileName  - The filename of the file to load
   * @return          - Returns a Deck object loaded with cards from the specified file
   * @throws IOException
   */
  public static Deck loadDeck(final String fileName) throws IOException {
    return new Deck(loadCards(fileName));
  }

  /**
   * Loads a list of cards from a text file with a list of card names
   *
   * @param cardsStr  - The filename for the string of card names
   * @return          - A list of cards produced from the file
   * @throws IOException
   */
  private static List<Card> loadCardsFromString(final String cardsStr) throws IOException {
    List<String> cardsRaw = Arrays.asList(cardsStr.split("\n"));
    Map<String,Integer> cardsToFind = new HashMap<>();
    List<Card> cards = new ArrayList<>();

    Pattern rawPattern = Pattern.compile("^(?:(\\d+)x?\\s*(.*))");
    Matcher matcher;

    try {
      for (String card : cardsRaw) {
        matcher = rawPattern.matcher(card);

        if (matcher.find()) {
          cardsToFind.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
        }
      }

      cardsToFind
        .entrySet()
        .forEach(e -> {
          Card card = lookupCard(e.getKey());

          if(card == null) {
            throw new RuntimeException(String.format("Unable to load card: %s", e.getKey()));
          }

          for (int i = 0; i < cardsToFind.get(card.getName()); ++i) {
            cards.add(card);
          }
        });
    }
    catch (Exception e) {
      throw new IOException("Unable to load cards from string provided", e);
    }

    return cards;
  }

  /**
   * Copies one card list into another list
   *
   * @param cards - A list of cards to be copied
   * @return      - The newly created list
   */
  public static List<Card> copyCardList(List<Card> cards) {
    List<Card> newCardList = new ArrayList<>();

    cards.forEach(card -> newCardList.add(new Card(card.getCardPojo())));

    return newCardList;
  }

  /**
   * This method is used to look up a single card from the complete
   * collection of cards
   *
   * @param cardName  - The name of the card to be looked up
   * @return          - The card object if found, null if not
   */
  public static Card lookupCard(final String cardName) {
    Card card = null;

    if(LoadCards.cardMap == null) {
      throw new IllegalStateException("The complete list of cards have not been loaded yet");
    }

    if(LoadCards.cardMap.containsKey(cardName)) {
      card = LoadCards.cardMap.get(cardName);
    }

    return card;
  }

  /**
   * Creates a list of cards from an inputstream
   *
   * @param inputStream - An inputstream that will supply a buffer for json input
   * @param saveSource  - If true then store the jsonMap produced
   * @return            - Returns a list of card objects
   * @throws IOException
   */
  private static List<Card> loadCards(InputStream inputStream, boolean saveSource) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);

    Map<String, Card> cardMap = jsonMap
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    e -> new Card(e.getValue())
            ));

    LoadCards.cardMap = saveSource ? cardMap : LoadCards.cardMap;

    return new ArrayList<Card>(cardMap.values());
  }
}
