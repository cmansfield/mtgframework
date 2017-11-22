package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.constants.Colors;
import io.github.cmansfield.card.constants.Formats;
import io.github.cmansfield.card.constants.Legality;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.io.SaveCards;
import io.github.cmansfield.io.UpdateCards;
import org.omg.IOP.CodecPackage.FormatMismatch;

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

    List<Map<String,String>> legalities = new CardUtils
            .LegalitiesBuilder()
            .format(Formats.COMMANDER)
            .format(Formats.BATTLE_FOR_ZENDIKAR_BLOCK)
            .build();

    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .legalities(legalities)
//            .text("Indestructible")
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, Collections.singletonList(cardFilter));
    printCards(filteredCards);
    System.out.println(filteredCards.size());
//    String fileName = SaveCards.saveCards(filteredCards);

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
