package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.io.SaveCards;
import io.github.cmansfield.io.web.GetUpdates;

import java.io.IOException;
import java.util.*;


public class App {

  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }

  public static void main_(String[] args) throws IOException {
    List<Card> cards;

    try {
      cards = LoadCards.loadCards();
    }
    catch (Exception e) {
      System.out.printf("Unable to load card list from file '%s'%n", IoConstants.ALL_CARDS_FILE_NAME);
      return;
    }

    Card card = LoadCards.lookupCard("Animar, Soul of Elements");
    System.out.println(card);
  }


  public static void main(String[] args) throws IOException {

    if(args.length > 0) {
      if(args[0].equals("-u") || args[0].equals("--update")) {
        GetUpdates.checkForUpdates();
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

    Deck doranDeck = new Deck(LoadCards.loadCards("Ents-Awakening.txt"));
    doranDeck.setFormat(Format.COMMANDER);
    doranDeck.setFeaturedCard(LoadCards.lookupCard("Doran, the Siege Tower"));
    String newFileName = SaveCards.saveDeck(doranDeck);
    System.out.println(newFileName);
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
