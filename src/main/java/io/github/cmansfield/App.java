package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.io.CardListConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.UpdateCardList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {

  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }

  public static void main(String[] args) {

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
      System.out.printf("Unable to load card list from file '%s'%n", CardListConstants.ALL_CARDS_FILE_NAME);
      return;
    }

    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .colors(Collections.singletonList("White"))
            .superTypes(Collections.singletonList("Legendary"))
            .build();
//    CardFilter cardFilter = new CardFilter
//            .CardBuilder()
//            .manaCost("{1}{U}{U}")
//            .layout("normal")
//            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);
    System.out.println(filteredCards);
  }
}
