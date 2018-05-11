package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.ability.CardAbility;
import io.github.cmansfield.card.interpreter.CardTextListener;
import io.github.cmansfield.card.interpreter.antlr.TextGrammarLexer;
import io.github.cmansfield.card.interpreter.antlr.TextGrammarParser;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.io.*;
import io.github.cmansfield.io.web.GetUpdates;
import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.validator.DeckValidator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


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

    importFromTappedOut();

    LOGGER.info("End of App");
  }



  private static void formatKeywordForGrammar() {
    String input = "Deathtouch\n" +
            "Defender\n" +
            "Double strike\n" +
            "Enchant";

    List<String> inputs = Arrays.asList(input.split("\n"));
    inputs = inputs.stream()
            .map(val -> {
              String first = val.substring(0, 1);
              return String.format(
                      "| [%s%s]'%s'",
                      first.toUpperCase(),
                      first.toLowerCase(),
                      val.substring(1));
            })
            .collect(Collectors.toList());

    inputs.forEach(System.out::println);
  }


  private static void parseCardText() throws IOException {
    String testStr = "Flash\nDouble strike, vigilance, haste\nOther creatures you control have haste.\n{W}, {T}: Untap another target creature.";
    TextGrammarLexer lexer = new TextGrammarLexer(
            CharStreams.fromStream(
                    new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.UTF_8));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    TextGrammarParser parser = new TextGrammarParser(tokens);
    ParseTree tree = parser.cardText();
    ParseTreeWalker walker = new ParseTreeWalker();
    CardTextListener listener = new CardTextListener();
    walker.walk(listener, tree);

    List<CardAbility> cardAbilities = listener.getCardAbilities();
    System.out.println(cardAbilities.size());
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


  private static void importFromTappedOut() throws IOException {
    List<String> files = Arrays.asList(
            "TappedCrawler\\decks\\brago-king-eternal",
            "TappedCrawler\\decks\\ephara-god-of-the-polis",
            "TappedCrawler\\decks\\lavinia-of-the-tenth");

    List<Deck> decks = files.stream()
            .map(TappedImporter::importFilesFromTappedOut)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    Map<String,Integer> cardCounts = DeckUtils.getCardCount(decks)
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 2)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    List<Map.Entry<String, Integer>> sorted = new ArrayList<>(cardCounts.entrySet());
    sorted.sort(Comparator.comparing(Map.Entry::getValue));
    Collections.reverse(sorted);

    List<Card> cards = decks.stream()
            .map(Deck::getCards)
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());
    Card filter = new Card.CardBuilder()
            .legalities(new CardUtils.LegalitiesBuilder()
                    .format(Format.COMMANDER)
                    .build())
            .build();
    cards = CardFilter.filter(cards, filter);
    cards = cards.stream()
            .filter(card -> {
              if(card.getColors() == null) {
                return true;
              }

              return !card.getColors().contains(Color.BLACK.toString())
                      && !card.getColors().contains(Color.GREEN.toString());
            })
            .collect(Collectors.toList());

    CardWriter.saveCards(cards);

    sorted = removeFromSorted(cards, sorted);
    sorted.forEach(entry ->
            System.out.printf("%d %s%n", entry.getValue(), entry.getKey())
    );
  }

  private static List<Map.Entry<String, Integer>> removeFromSorted(List<Card> cards, List<Map.Entry<String, Integer>> sorted) {
    return sorted.stream()
            .filter(entry -> !CardFilter.filter(
                    cards,
                    new Card.CardBuilder()
                            .name(entry.getKey())
                            .build()).isEmpty())
            .collect(Collectors.toList());
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
