package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.SaveCardList;
import io.github.cmansfield.io.UpdateCardList;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {

  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }

  public static void main(String[] args) throws IOException {

    if(args.length > 0) {
      if(args[0].equals("-u") || args[0].equals("--update")) {
        UpdateCardList.checkForUpdates();
      }
      else {
        usage();
      }
    }

    List<Card> cards;

    try {
      cards = LoadCardList.loadCards();
    }
    catch (Exception e) {
      System.out.printf("Unable to load card list from file '%s'%n", IoConstants.ALL_CARDS_FILE_NAME);
      return;
    }

    Map<String,String> legal = new HashMap<>();
    legal.put("format", "Commander");
    legal.put("legality", "Legal");

    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .legalities(Collections.singletonList(legal))
            .superTypes(Collections.singletonList("Legendary"))
            .subTypes(Collections.singletonList("Angel"))
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);
    String fileName = SaveCardList.saveCards(filteredCards);

    List<Card> testCards = LoadCardList.loadCards(fileName);
    printCards(testCards);
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
