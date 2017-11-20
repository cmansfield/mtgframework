package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.filter.CardFilter;
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

    Map<String,String> legal = new HashMap<>();
    legal.put("format", "Commander");
    legal.put("legality", "Legal");

    List<String> colors = new ArrayList<>();
    colors.add("White");
    colors.add("Black");
    colors.add("Red");

    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .legalities(Collections.singletonList(legal))
            .text("Indestructible")
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);
//    printCards(filteredCards);
//    String fileName = SaveCardList.saveCards(filteredCards);

//    List<Card> testCards = LoadCardList.loadCards(fileName);
//    printCards(testCards);

//    String cardsRaw = "1x Abzan Beastmaster\n" +
//            "1x Ambition's Cost\n" +
//            "1x Animate Wall\n" +
//            "1x Assault Formation\n" +
//            "1x Axebane Guardian\n" +
//            "1x Behind the Scenes\n" +
//            "1x Belbe's Armor\n" +
//            "1x Blossoming Sands\n" +
//            "1x Brave the Sands\n" +
//            "1x Chromatic Lantern";
//
//    List<Card> myDeck = LoadCardList.loadCardsFromString(cardsRaw);
//    printCards(myDeck);
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
