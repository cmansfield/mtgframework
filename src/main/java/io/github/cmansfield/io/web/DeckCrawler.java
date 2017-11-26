package io.github.cmansfield.io.web;

import java.util.ArrayList;
import java.util.List;


public final class DeckCrawler {
  // First string insert is the format, example: edh
  // Second string insert is the commander to search on
  private final String DECK_SEARCH_URL = "http://tappedout.net/mtg-decks/search/?q=&format=%s&cards=&general=%s&is_top=on&price_0=&price_1=&o=-rating&submit=Filter+results";

  private DeckCrawler() {}

  public static List<String> getDeckList(final String commander) {
    List<String> decks = new ArrayList<>();

    if(commander == null) {
      throw new IllegalArgumentException("The commander argument cannot be null");
    }



    return decks;
  }
}
