package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.Colors;
import io.github.cmansfield.card.constants.Legality;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.SaveCardList;
import io.github.cmansfield.io.UpdateCardList;
import io.github.cmansfield.validator.CardValidator;

import java.io.IOException;
import java.util.*;


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

//    Map<String,String> legal = new HashMap<>();
//    legal.put(Legality.FORMAT.toString(), "Commander");
//    legal.put(Legality.LEGALITY.toString(), "Legal");
//
//    List<String> colors = new ArrayList<>();
//    colors.add(Colors.WHITE.toString());
//    colors.add(Colors.BLACK.toString());
//    colors.add(Colors.RED.toString());
//
//    CardFilter cardFilter = new CardFilter
//            .CardBuilder()
//            .legalities(Collections.singletonList(legal))
//            .text("Indestructible")
//            .build();
//
//    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);
//    printCards(filteredCards);
//    String fileName = SaveCardList.saveCards(filteredCards);


    Deck deck = LoadCardList.loadDeck("src\\test\\resources\\inputRaw.txt");
    System.out.println(deck);

//    SaveCardList.saveCards(new Deck(myDeck));
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
