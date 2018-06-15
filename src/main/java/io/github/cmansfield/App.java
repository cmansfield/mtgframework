package io.github.cmansfield;

import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.io.web.MtgJsonAdapter;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.constants.CliOptions;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.*;
import org.apache.commons.cli.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.io.IOException;
import java.io.File;
import java.util.*;


public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * This method will create an Options object with all of the program's
   * command line options
   * 
   * @return          The command line options
   */
  private static Options createOptions() {
    Options options = new Options();
    
    options.addOption(Option.builder(CliOptions.COMMANDER_ABBR)
            .hasArg()
            .argName("CommanderName")
            .longOpt(CliOptions.COMMANDER)
            .desc("The name of the deck commander")
            .build());
    options.addOption(Option.builder(CliOptions.NUMBER_OF_CARDS)
            .hasArg()
            .argName("number")
            .desc("The number of cards to save")
            .build());
    options.addOption(Option.builder(CliOptions.GENERATE_CARD_LIST_FROM_DIR)
            .hasArg()
            .argName("directory")
            .desc("This will generate and save a card list composed of all files with card names found in a directory")
            .build());
    options.addOption(CliOptions.UPDATE_ABBR, CliOptions.UPDATE, false, "Check for card list updates");
    options.addOption(CliOptions.SAVE_DECK_LISTS, false, "Save each downloaded deck list as a json file");
    options.addOption(CliOptions.DOWNLOAD_IMAGES, false, "Download an image for each card in the deck lists");
    return options;
  }

  /**
   * This method will parse the command line arguments based on the supplied Options
   * 
   * @param args      The arguments passed into the program
   * @param options   The command line options
   * @return          The CommandLine object with the parsed arguments 
   */
  private static CommandLine parseArgs(String[] args, Options options) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    return parser.parse(options, args);
  }

  /**
   * This method will display the program's uage out to the user
   * 
   * @param options   The command line options
   */
  private static void usage(Options options) {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp("mtg-framework", options);
  }
  
  /**
   * This method will make sure all of the required command line arguments have been supplied
   * 
   * @param cmd       The CommandLine object with the parsed arguments
   * @param options   The command line options
   */
  private static void checkForRequiredOptions(CommandLine cmd, Options options) {
    if(cmd == null) {
      usage(options);
      System.exit(-1);
    }
    if(cmd.getOptions().length == 0) {
      usage(options);
      System.exit(-1);
    }
    if(cmd.hasOption(CliOptions.GENERATE_CARD_LIST_FROM_DIR) && cmd.getOptions().length > 1) {
      usage(options);
      LOGGER.error("Cannot use option '{}' with any other option", CliOptions.GENERATE_CARD_LIST_FROM_DIR);
      System.exit(-1);
    }
  }
  
  public static void main(String[] args) throws IOException {
    Options options = createOptions();
    CommandLine cmd;
    
    try {
      cmd = parseArgs(args, options);
    }
    catch (Exception e) {
      usage(options);
      LOGGER.error(e.getMessage());
      return;
    }
    checkForRequiredOptions(cmd, options);    
    
    if(cmd.hasOption(CliOptions.UPDATE) || cmd.hasOption(CliOptions.UPDATE_ABBR)) {
      MtgJsonAdapter.checkForUpdates();
    }

    try {
      CardReader.loadCards();
    }
    catch (Exception e) {
      LOGGER.error("Unable to load card list from file '{}'", IoConstants.ALL_CARDS_FILE_NAME, e);
      return;
    }

//    importFromTappedOut("TappedCrawler\\decks\\keranos-god-of-storms");
//    List<Card> loadedCards = CardReader.loadCards(
//            IoConstants.SAVE_DIR
//                    + File.separator
//                    + "CardList3.json");
//    MtgAdapter.saveCardImages(loadedCards);

    LOGGER.info("End of App");
  }

  // TODO - Clean this up and move it to the correct class
  public static List<String> listFilesForFolder(final File folder) {
    List<String> cardNames = new ArrayList<>();

    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        listFilesForFolder(fileEntry);
      }
      else {
        cardNames.add(fileEntry.getName().replace(".jpg", ""));
      }
    }
    return cardNames;
  }

  // TODO - Clean this method up before standardizing it
  private static void importFromTappedOut(String... folders) throws IOException {
    final int maxNumCards = 250;
    List<String> files = Arrays.asList(folders);

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

    if(sorted.size() > maxNumCards) {
      sorted = sorted.subList(0, maxNumCards);
    }

    List<Card> cards = sorted.stream()
            .map(Map.Entry::getKey)
            .map(CardReader::lookupCard)
            .collect(Collectors.toList());
    Card filter = new Card.CardBuilder()
            .legalities(new CardUtils.LegalitiesBuilder()
                    .format(Format.COMMANDER)
                    .build())
            .build();
    cards = CardFilter.filter(cards, filter);
    cards = cards.stream()
            .filter(card -> {
              return !card.getColors().contains(Color.BLACK.toString())
                      && !card.getColors().contains(Color.WHITE.toString())
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
}
