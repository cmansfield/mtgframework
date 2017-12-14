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


public final class CardReader {
  private static Map<String, Card> cardMap;

  private CardReader() {}

  /**
   * Loads a list of cards into a List object
   *
   * @return - Returns a List of card objects from the loaded file
   * @throws IOException
   */
  public static List<Card> loadCards() throws IOException {
    if(CardReader.cardMap != null) {
      return new ArrayList<>(CardReader.cardMap.values());
    }

    ZipFile zip = new ZipFile(IoConstants.CARD_LIST_FILE_NAME);
    ObjectMapper mapper = new ObjectMapper();

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_CARDS_FILE_NAME))) {
      CardReader.cardMap = mapper.readValue(inputstream, new TypeReference<Map<String,Card>>(){});
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", IoConstants.ALL_CARDS_FILE_NAME);
      throw new IOException(e);
    }
    finally {
      zip.close();
    }

    return new ArrayList<>(CardReader.cardMap.values());
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
            throw new RuntimeException(String.format("Unable to load card: %s", e.getKey()));   // NOSONAR
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

    if(CardReader.cardMap == null) {
      System.out.println("The complete list of cards has not been loaded yet, loading now.");
      try {
        loadCards();
      }
      catch (Exception e) {
        throw new RuntimeException(e);    // NOSONAR
      }
    }

    if(CardReader.cardMap.containsKey(cardName)) {
      return CardReader.cardMap.get(cardName);
    }

    String treatedName = cardNameTreatment(cardName);

    if(CardReader.cardMap.containsKey(treatedName)) {
      return CardReader.cardMap.get(treatedName);
    }

    card = getBestMatchingCard(treatedName);
    if(card != null) {
      return card;
    }

    // Check to see if the deck owner added an 's' to the end
    //  of the card name, if so we need to remove it
    if(treatedName.matches(".+s$")) {
      StringBuilder sb = new StringBuilder(treatedName);
      sb.deleteCharAt(treatedName.length() - 1);
      treatedName = sb.toString();

      card = getBestMatchingCard(treatedName);
      if(card != null) {
        return card;
      }
    }

    // Sometimes split cards show up like 'Ready Willing' rather
    // than 'Ready / Willing', this treatment will grab the first
    // word and try a search on it
    List<String> cardNames = Arrays.asList(treatedName.split("\\s"));
    if(cardNames.size() > 1) {
      card = getBestMatchingCard(cardNames.get(0));
      if(card != null) {
        return card;
      }
    }

    System.out.printf("Could not find card %s    Treated Name: '%s'%n", cardName, treatedName);

    return null;
  }

  /**
   * This method tries to find the best matching card based on
   * the card name supplied
   *
   * @param cardName  - Name of the card to search for
   * @return          - The card object if found, null if not
   */
  private static Card getBestMatchingCard(final String cardName) {
    Card filter = new Card.CardBuilder().name(cardName).build();
    List<Card> filteredList = null;

    try {
      filteredList = CardFilter.filter(loadCards(), filter);
    }
    catch (Exception e) {
      throw new RuntimeException(e);    // NOSONAR
    }

    if(filteredList != null && filteredList.size() == 1) {
      return filteredList.get(0);
    }
    else if(filteredList != null && filteredList.size() > 1) {
      return filteredList
              .stream()
              .reduce(
                      filteredList.get(0),
                      (a,c) -> {
                        int aDif = Math.abs(a.getName().length() - cardName.length());
                        int cDif = Math.abs(c.getName().length() - cardName.length());

                        return aDif <= cDif ? a : c;
                      });
    }

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
    patterns.add(Pattern.compile("\\*.*\\*"));              // Remove foil tags: Island *F*
    patterns.add(Pattern.compile("\\((?:(?!\\s).)*\\)"));   // Remove set tags at the end: Evolving Wilds (BFZ)
    patterns.add(Pattern.compile("\\["));                   // Remove opening brackets
    patterns.add(Pattern.compile("]"));                     // Remove closing brackets
    patterns.add(Pattern.compile("^\\s+"));                 // Remove leading spaces
    patterns.add(Pattern.compile("\\s+$"));                 // Remove trailing spaces

    Pattern bracketPattern = Pattern.compile("\\s*(.+)\\s\\[.+].*");
    Matcher match = bracketPattern.matcher(treatedCardName);
    if(match.find()) {
      treatedCardName = match.group(1);
    }

    Pattern splitPattern = Pattern.compile("\\s*([^\\s]+)\\s?\\/\\s?.*");
    match = splitPattern.matcher(treatedCardName);
    if(match.find()) {
      treatedCardName = match.group(1);
    }

    for (Pattern pattern: patterns) {
      treatedCardName = pattern.matcher(treatedCardName).replaceAll("");
    }

    return WordUtils.capitalizeFully(treatedCardName);
  }
}
