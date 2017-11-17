package io.github.cmansfield;

import io.github.cmansfield.io.LoadCardList;
import io.github.cmansfield.io.UpdateCardList;

import java.io.IOException;


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

    // TODO - add error check here
    LoadCardList.loadCards();
  }
}
