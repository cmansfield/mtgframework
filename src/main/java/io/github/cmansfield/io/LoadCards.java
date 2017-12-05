package io.github.cmansfield.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.filters.CardFilter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.WordUtils;
import io.github.cmansfield.card.Card;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


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
    if(LoadCards.cardMap != null) {
      return new ArrayList<>(LoadCards.cardMap.values());
    }

    ZipFile zip = new ZipFile(IoConstants.CARD_LIST_FILE_NAME);
    ObjectMapper mapper = new ObjectMapper();

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_CARDS_FILE_NAME))) {
      LoadCards.cardMap = mapper.readValue(inputstream, new TypeReference<Map<String,Card>>(){});
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", IoConstants.ALL_CARDS_FILE_NAME);
      throw new IOException(e);
    }
    finally {
      zip.close();
    }

    return new ArrayList<>(LoadCards.cardMap.values());
  }

  /**
   * Loads a list of cards from the specified file
   *
   * @param fileName  - The filename of the file to load
   * @return          - Returns a List of card objects from the loaded file
   * @throws IOException
   */
  public static List<Card> loadCards(final String fileName) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    List<Card> cards = null;

    String extension = FilenameUtils.getExtension(fileName);

    try(InputStream inputstream = new FileInputStream(fileName)) {
      if(extension.equalsIgnoreCase(IoConstants.JSON_EXT)) {
        Map<String, Card> cardMap = mapper.readValue(inputstream, new TypeReference<Map<String,Card>>(){});
        return new ArrayList<>(cardMap.values());
      }
      if(extension.equalsIgnoreCase(IoConstants.TXT_EXT)) {
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
   * Loads a list of cards from a text file with a list of card names
   *
   * Looking for strings that look like the following
   * 1x cardName
   * 4x cardName2
   * 2x cardName3
   *
   * @param cardsStr  - The un-formatted string of card names
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
   * This method is used to look up a single card from the complete
   * collection of cards
   *
   * @param cardName  - The name of the card to be looked up
   * @return          - The card object if found, null if not
   */
  public static Card lookupCard(final String cardName) {
    Card card = null;

    if(LoadCards.cardMap == null) {
      throw new IllegalStateException("The complete list of cards has not been loaded yet");
    }

    if(LoadCards.cardMap.containsKey(cardName)) {
      return LoadCards.cardMap.get(cardName);
    }

    String treatedName = cardNameTreatment(cardName);

    if(LoadCards.cardMap.containsKey(treatedName)) {
      return LoadCards.cardMap.get(cardName);
    }

    Card filter = new Card.CardBuilder().name(treatedName).build();
    List<Card> filteredList = null;

    try {
      filteredList = CardFilter.filter(loadCards(), filter);
    }
    catch (Exception e) {}

    if(filteredList != null && filteredList.size() == 1) {
      return filteredList.get(0);
    }

    System.out.printf("Could not find card %s    Treated Name: '%s'%n", cardName, treatedName);

    return null;
  }

  /**
   * This method is used to treat and normalize bad card names
   *
   * @param cardName  - The bad card name that needs to be treated
   * @return          - The treated card name that should be good
   */
  private static String cardNameTreatment(String cardName) {
    String treatedCardName = cardName.replace("’", "'");
    List<Pattern> patterns = new ArrayList<>();
    patterns.add(Pattern.compile("\\[(?:(?!\\s).)*]$"));    // Remove set tags at the end: Phantasmal Image [M12]
    patterns.add(Pattern.compile("\\((?:(?!\\s).)*\\)$"));  // Remove set tags at the end: Evolving Wilds (BFZ)
    patterns.add(Pattern.compile("\\["));                   // Remove opening brackets
    patterns.add(Pattern.compile("]"));                     // Remove closing brackets
    patterns.add(Pattern.compile("\\*.*\\*"));              // Remove foil tags: Island *F*
    patterns.add(Pattern.compile("^\\s+"));                 // Remove leading spaces
    patterns.add(Pattern.compile("\\s+$"));                 // Remove trailing spaces

    for (Pattern pattern: patterns) {
      treatedCardName = pattern.matcher(treatedCardName).replaceAll("");
    }

    return WordUtils.capitalizeFully(treatedCardName);
  }
}
