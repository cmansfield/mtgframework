package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.io.SaveCards;
import io.github.cmansfield.io.UpdateCards;

import java.io.IOException;
import java.util.*;


public class App {

  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }

  public static void main(String[] args) throws IOException {

    if(args.length > 0) {
      if(args[0].equals("-u") || args[0].equals("--update")) {
        UpdateCards.checkForUpdates();
      }
      else {
        usage();
      }
    }

    List<Card> cards;

    try {
      cards = LoadCards.loadCards();
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
//    String fileName = SaveCards.saveCards(filteredCards);


    Deck deck = LoadCards.loadDeck("src\\test\\resources\\inputRaw.txt");

    SaveCards.saveDeck(deck);

    Deck deck2 = LoadCards.loadDeck(String.format("%s\\%s", IoConstants.SAVE_DIR, "Deck0.json"));
    System.out.println(deck2.toString());
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
