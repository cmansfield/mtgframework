package io.github.cmansfield;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.io.web.GetUpdates;
import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.*;

import java.io.IOException;
import java.util.*;


public class App {


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



    System.out.println("End");
  }


  private static void importFromTappedOut() throws IOException {
//    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\test");
    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\animar-soul-of-elements");
    Map<String,Integer> cardCounts = DeckUtils.getCardCount(decks);
    List<Map.Entry<String, Integer>> sorted = new ArrayList<>(cardCounts.entrySet());
    sorted.sort(Comparator.comparing(Map.Entry::getValue));
    Collections.reverse(sorted);
    sorted.forEach(entry -> {
      System.out.printf("%d %s%n", entry.getValue(), entry.getKey());
    });
  }


  private static void playGame() throws IOException {
    Deck doranDeck = LoadDeck.loadDeck("SavedCardLists/DoranDeck.json");
    Deck ghaveDeck = LoadDeck.loadDeck("SavedCardLists/GhaveDeck.json");

    Player player1 = new Player(doranDeck);
    Player player2 = new Player(ghaveDeck);
    Player player3 = new Player(ghaveDeck);

    GameManager gameManager = new GameManager
            .GameManagerBuilder()
            .player(player1)
            .player(player2)
            .player(player3)
            .build();

    gameManager.startGame();
  }


  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }
}
