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

  private static List<Card> preLoadedAllCards;

  private LoadCards() {}

  public static List<Card> loadCards() throws IOException {
    List<Card> cards;

    if(LoadCards.preLoadedAllCards != null) {
      return copyCardList(LoadCards.preLoadedAllCards);
    }

    ZipFile zip = new ZipFile(IoConstants.CARD_LIST_FILE_NAME);

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_CARDS_FILE_NAME))) {
      cards = loadCards(inputstream);
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", IoConstants.ALL_CARDS_FILE_NAME);
      throw new IOException(e);
    }
    finally {
      zip.close();
    }

    LoadCards.preLoadedAllCards = copyCardList(cards);

    return cards;
  }

  public static List<Card> loadCards(final String fileName) throws IOException {
    final String JSON_EXT = "json";
    final String TXT_EXT = "txt";
    List<Card> cards = null;

    String extension = FilenameUtils.getExtension(fileName);

    try(InputStream inputstream = new FileInputStream(fileName)) {
      if(extension.equalsIgnoreCase(JSON_EXT)) {
        cards = loadCards(inputstream);
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

  public static Deck loadDeck(final String fileName) throws IOException {
    return new Deck(loadCards(fileName));
  }

  public static List<Card> loadCardsFromString(final String cardsStr) throws IOException {
    List<String> cardsRaw = Arrays.asList(cardsStr.split("\n"));
    Map<String,Integer> cardsToFind = new HashMap<>();
    List<Card> cards = new ArrayList<>();
    List<Card> allCards = loadCards();

    Pattern rawPattern = Pattern.compile("^(?:(\\d+)x\\s*(.*))");
    Matcher matcher;

    try {
      for (String card : cardsRaw) {
        matcher = rawPattern.matcher(card);

        if (matcher.find()) {
          cardsToFind.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
        }
      }

      allCards.forEach(card -> {
        if (cardsToFind.containsKey(card.getName())) {
          for (int i = 0; i < cardsToFind.get(card.getName()); ++i) {
            cards.add(card);
          }
        }
      });
    }
    catch (Exception e) {
      throw new IOException("Unable to load cards from string provided", e);
    }

    return cards;
  }

  public static List<Card> copyCardList(List<Card> cards) {
    List<Card> newCardList = new ArrayList<>();

    cards.forEach(card -> newCardList.add(new Card(card.getCardPojo())));

    return newCardList;
  }

  private static List<Card> loadCards(InputStream inputStream) throws IOException {
    List<Card> cards = null;

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
    cards = jsonMap
            .values()
            .stream()
            .map(v -> new Card(v))
            .collect(Collectors.toList());

    return cards;
  }
}
