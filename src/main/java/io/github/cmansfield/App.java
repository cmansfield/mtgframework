package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.io.CardListConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.UpdateCardList;
import io.github.cmansfield.validater.Validater;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

//    CardFilter cardFilter = new CardFilter
//            .CardBuilder()
//            .colors(Collections.singletonList("White"))
//            .superTypes(Collections.singletonList("Legendary"))
//            .build();

//    Map<String,String> legal = new HashMap<>();
//    legal.put("format", "Commander");
//    legal.put("legality", "Legal");
//
//    CardFilter cardFilter = new CardFilter
//            .CardBuilder()
//            .legalities(Collections.singletonList(legal))
//            .subTypes(Collections.singletonList("Angel"))
//            .build();
//
//    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);
//    filteredCards.forEach(card -> {
//      System.out.println(card.getName());
//    });

    List<Card> incompleteCards = Validater.getListOfIncompleteCards(cards);
    incompleteCards.forEach(c -> {
      System.out.println(c.getName());
    });
  }
}
