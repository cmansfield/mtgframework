package io.github.cmansfield;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.validator.DeckValidator;
import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.io.web.GetUpdates;
import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import java.io.IOException;
import java.util.*;


public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

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
      cards = CardReader.loadCards();
    }
    catch (Exception e) {
      LOGGER.error("Unable to load card list from file '{}'", IoConstants.ALL_CARDS_FILE_NAME, e);
      return;
    }

    // Wrap method we want to test in a lambda
//    timeMethod(() -> {
//      CardFilter.filter(cards, new Card.CardBuilder().name("awol2").build());
//      return null;
//    });

    LOGGER.info("Pre layout");

    playGame();

//    String testStr = "hello world";
//    TextGrammarLexer lexer = new TextGrammarLexer(
//            CharStreams.fromStream(
//                    new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8)),
//                    StandardCharsets.UTF_8));
//    TextGrammarParser parser = new TextGrammarParser(new CommonTokenStream(lexer));


    LOGGER.info("End of App");
  }


  /**
   * Simple profiler to help optimize methods
   *
   * @param supplier
   * @throws IOException
   */
  private static void timeMethod(Supplier supplier) throws IOException {
    List<Card> cards = CardReader.loadCards();

    final int testIterations = 100;
    long startTime = System.nanoTime();
    for (int i = 0; i < testIterations; i++) {
      supplier.get();
    }
    long endTime = System.nanoTime();
    long averageTime = ((endTime - startTime) / 1000000 / testIterations);

    LOGGER.info("{} ms", averageTime);
  }


  private static void importFromTappedOut() {
//    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\test");
    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\animar-soul-of-elements");
//    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\ghave-guru-of-spores");
    Map<String,Integer> cardCounts = DeckUtils.getCardCount(decks);
    List<Map.Entry<String, Integer>> sorted = new ArrayList<>(cardCounts.entrySet());
    sorted.sort(Comparator.comparing(Map.Entry::getValue));
    Collections.reverse(sorted);
    sorted.forEach(entry ->
      System.out.printf("%d %s%n", entry.getValue(), entry.getKey())
    );
  }


  private static void saveFormatCompliantDecksFromTappedOut() {
    List<Deck> decks = TappedImporter.importFilesFromTappedOut("TappedCrawler\\decks\\animar-soul-of-elements");
    decks.forEach(deck -> {
      try{
        DeckValidator.isFormatCompliant(deck);
        DeckWriter.saveDeck(deck);
      }
      catch(Exception e) {
        LOGGER.error("", e);
      }
    });
  }


  private static void playGame() throws IOException {
    Deck doranDeck = DeckReader.loadDeck("SavedCardLists/DoranDeck.json");
    Deck ghaveDeck = DeckReader.loadDeck("SavedCardLists/GhaveDeck.json");

    Player player1 = new Player(doranDeck, "Player1");
    Player player2 = new Player(ghaveDeck, "Player2");
    Player player3 = new Player(ghaveDeck, "Player3");

//    Game game = new Game.GameBuilder().player(player1).player(player2).player(player3).build();
//    game.addToPlayerStack(new DrawAction(game, 5));
//    game.addToPlayerStack(new DiscardAction(game, 3));
//    Game game2 = new Game(game);

//    System.out.println("testing");
    GameManager gameManager = new GameManager(
            new Game.GameBuilder()
            .player(player1)
            .player(player2)
            .player(player3)
            .build());
    gameManager.startGame();
  }


  private static void usage() {
    System.out.println("Usage: mtgframework [-u | --update]");    // NOSONAR
  }
}
