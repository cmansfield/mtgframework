package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;


public class LoadCardList {
  private LoadCardList() {}

  public static List<Card> loadCards() throws IOException {
    List<Card> cards;

    ZipFile zip = new ZipFile(CardListConstants.CARD_LIST_FILE_NAME);
    InputStream inputStream = zip.getInputStream(zip.getEntry(CardListConstants.ALL_CARDS_FILE_NAME));

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
