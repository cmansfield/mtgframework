package io.github.cmansfield;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.io.LoadDeck;
import io.github.cmansfield.io.SaveCards;
import io.github.cmansfield.io.web.GetUpdates;
import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipFile;


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

    List<Card> cardList = LoadCards.loadCards();
    String filename = SaveCards.saveCards(cardList);
    List<Card> cardList1 = LoadCards.loadCards(filename);

    if(!cardList.equals(cardList1)) {
      System.out.println("Problems");
    }

    System.out.println("End");
  }


  private void importFromTappedOut() throws IOException {
    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\animar-soul-of-elements");
    Map<String,Integer> cardCounts = DeckUtils.getCardCount(decks);
    List<Map.Entry<String, Integer>> sorted = new ArrayList<>(cardCounts.entrySet());
    sorted.sort(Comparator.comparing(Map.Entry::getValue));
    Collections.reverse(sorted);
    sorted.forEach(entry -> {
      System.out.printf("%d %s%n", entry.getValue(), entry.getKey());
    });
  }


  private void playGame() throws IOException {
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
