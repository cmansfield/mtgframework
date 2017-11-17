package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.io.CardListConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.UpdateCardList;
import io.github.cmansfield.validater.Validater;

import java.lang.reflect.Field;
import java.util.Arrays;
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

    List<Card> incompleteCards = Validater.getListOfIncompleteCards(cards);
    incompleteCards.forEach(card -> System.out.println(card.getName()));
  }
}
