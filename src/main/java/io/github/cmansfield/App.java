package io.github.cmansfield;

import io.github.cmansfield.Card.Card;
import io.github.cmansfield.io.CardListConstants;
import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.UpdateCardList;

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

    List<Card> test;

    try {
      test = LoadCardList.loadCards();
    }
    catch (Exception e) {
      System.out.printf("Unable to load card list from file '%s'%n", CardListConstants.ALL_CARDS_FILE_NAME);
      return;
    }

    System.out.println(test.get(0).getName());
    System.out.println(test.get(0).getCmc());
    System.out.println(test.get(0).getColors());
  }
}
