package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.Colors;
import io.github.cmansfield.card.constants.Legality;
import io.github.cmansfield.card.filter.CardFilter;
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

    Map<String,String> legalities = new HashMap<>();
    legalities.put(Legality.FORMAT.toString(), "Commander");
    legalities.put(Legality.LEGALITY.toString(), "Legal");

    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .legalities(Collections.singletonList(legalities))
            .text("Indestructible")
            .build();

    CardFilter cardFilter2 = new CardFilter
            .CardBuilder()
            .colors(Collections.singletonList(Colors.RED.toString()))
            .build();

    List<CardFilter> filters = new ArrayList<>();
    filters.add(cardFilter);
    filters.add(cardFilter2);

    List<Card> filteredCards = CardFilter.filter(cards, filters);
    printCards(filteredCards);
    System.out.println(filteredCards.size());
    String fileName = SaveCards.saveCards(filteredCards);

//    Deck deck = LoadCards.loadDeck("src\\test\\resources\\inputRaw.txt");
//    Deck deck = LoadCards.loadDeck("EldraziDeck.txt");
//    SaveCards.saveDeck(deck);
//    System.out.println(deck.toString());
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
