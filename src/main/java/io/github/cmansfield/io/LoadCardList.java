package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;

import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;


public final class LoadCardList {
  private LoadCardList() {}

  public static List<Card> loadCards() throws IOException {
    List<Card> cards;

    ZipFile zip = new ZipFile(IoConstants.CARD_LIST_FILE_NAME);

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_CARDS_FILE_NAME))) {
      cards = loadCards(inputstream);
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", IoConstants.ALL_CARDS_FILE_NAME);
      throw new IOException(e);
    }

    return cards;
  }

  public static List<Card> loadCards(final String fileName) throws IOException {
    List<Card> cards;

    try(InputStream inputstream = new FileInputStream(fileName)) {
      cards = loadCards(inputstream);
    }
    catch (Exception e) {
      System.out.printf("Unable to load file %s%n", fileName);
      throw new IOException(e);
    }

    return cards;
  }

  public static List<Card> loadCardsFromString(final String cardsStr) throws IOException {
    List<Card> cards = loadCards();
    List<String> cardsRaw = Arrays.asList(cardsStr.split("\n"));
    Map<String,Integer> cardsToFind = new HashMap<>();

    Pattern rawPattern = Pattern.compile("^(?:(\\d+)x\\s*(.*))");
    Matcher matcher;

    for(String card : cardsRaw) {
      matcher = rawPattern.matcher(card);

      System.out.println(matcher.group(0));

//      String test = card.
    }

    return cards;
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
