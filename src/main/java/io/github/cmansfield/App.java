package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.CardUtils;
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

    Card templateCard = CardUtils.generateTemplateCard();
    SaveCards.saveCards(Collections.singletonList(templateCard));
  }

  private static void printCards(List<Card> cards) {
    cards.forEach(card -> {
      System.out.println(card.getName());
    });
  }
}
