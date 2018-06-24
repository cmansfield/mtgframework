package io.github.cmansfield;

import io.github.cmansfield.io.web.MtgJsonAdapter;
import io.github.cmansfield.io.web.TappedImporter;
import io.github.cmansfield.constants.CliOptions;
import io.github.cmansfield.set.constants.Rarity;
import io.github.cmansfield.io.web.MtgAdapter;
import org.apache.commons.lang.StringUtils;
import org.python.util.PythonInterpreter;
import io.github.cmansfield.set.SetUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.io.*;
import org.apache.commons.cli.*;
import org.python.core.PyObject;
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
    options.addOption(CliOptions.DOWNLOAD_IMAGES, false, "Download an image for each card in the deck lists");
    options.addOption(CliOptions.SAVE_CARD_LIST, false, "Will save the generated card list as a json file");
    options.addOption(CliOptions.EXCLUDE_COMMONS, false, "This option will remove all commons from the card list");
    options.addOption(CliOptions.SAVE_IMAGES_ORGANIZED, false, "This will automatically organize saved images into folders");
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
    if(cmd.hasOption(CliOptions.GENERATE_CARD_LIST_FROM_DIR) && cmd.hasOption(CliOptions.COMMANDER)) {
      usage(options);
      LOGGER.error(
              "Cannot use option '{}' with option '{}'",
              CliOptions.GENERATE_CARD_LIST_FROM_DIR,
              CliOptions.COMMANDER);
      System.exit(-1);
    }
    if(cmd.hasOption(CliOptions.SAVE_IMAGES_ORGANIZED) && !cmd.hasOption(CliOptions.DOWNLOAD_IMAGES)) {
      usage(options);
      LOGGER.error(
              "Cannot use option '{}' without option '{}'",
              CliOptions.SAVE_IMAGES_ORGANIZED,
              CliOptions.DOWNLOAD_IMAGES);
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

    List<Card> cardList = Collections.emptyList();

    if(cmd.hasOption(CliOptions.COMMANDER)) {
      Map<Card, Integer> cardMap = commanderOption(cmd);
      cardList = cardMap.entrySet().stream()
              .map(Map.Entry::getKey)
              .collect(Collectors.toList());
      cardMap.forEach((card, qty) -> System.out.printf("%d %s%n", qty, card.getName()));      // NOSONAR
    }
    if(cmd.hasOption(CliOptions.GENERATE_CARD_LIST_FROM_DIR)) {
      cardList = createCardListFromFileNamesInFolder(cmd, null);
    }
    if(cmd.hasOption(CliOptions.EXCLUDE_COMMONS)) {
      cardList = cardList.stream()
              .filter(card -> SetUtils.getLowestRarity(card).getValue() != Rarity.COMMON)
              .collect(Collectors.toList());
    }
    if(cmd.hasOption(CliOptions.SAVE_CARD_LIST)) {
      CardWriter.saveCards(cardList);
    }
    if(cmd.hasOption(CliOptions.DOWNLOAD_IMAGES)) {
      MtgAdapter.saveCardImages(cardList, cmd.hasOption(CliOptions.SAVE_IMAGES_ORGANIZED));
    }

    LOGGER.info("End of App");
  }

  /**
   * This method houses the logic for the command option
   *
   * @param cmd   The command line object
   * @return      A map of cards and their quantities
   */
  private static Map<Card, Integer> commanderOption(CommandLine cmd) throws IOException {
    String commanderName = cmd.getOptionValue(CliOptions.COMMANDER);
    int numberOfCards = 250;
    if(StringUtils.isBlank(commanderName)) {
      LOGGER.error("The commander's name cannot be blank");
      System.exit(-1);
    }
    if(cmd.hasOption(CliOptions.NUMBER_OF_CARDS)) {
      try {
        numberOfCards = Integer.parseInt(cmd.getOptionValue(CliOptions.NUMBER_OF_CARDS));
      }
      catch (NumberFormatException e) {
        LOGGER.error("Unable to parse option '-{}' it must be an integer value", CliOptions.NUMBER_OF_CARDS, e);
        System.exit(-1);
      }
    }
    if(cmd.hasOption(CliOptions.EXCLUDE_COMMONS)) {
      // This will help us download the correct number of cards if we are planning
      // on excluding about 60% of the downloaded cards.
      // n / 0.40 = desiredNumberOfCards
      // n = desiredNumberOfCards * 0.40
      numberOfCards /= .4;
    }

    Card featuredCard = CardReader.lookupCard(commanderName);
    if(featuredCard == null) {
      LOGGER.error("Could not find the commander '{}'", commanderName);
      System.exit(-1);
    }

    try(PythonInterpreter interpreter = new PythonInterpreter()) {
//      File file = new File("TappedCrawler/main.py");
//      interpreter.execfile(file.getAbsolutePath());
//      PyObject str = interpreter.eval(String.format("main(\"%s\",%d)", featuredCard.getName(), numberOfCards));
//      String featuredCardSlug = str.toString();
      String featuredCardSlug = "zedruu-the-greathearted";

      if(StringUtils.isBlank(featuredCardSlug)) {
        throw new IllegalArgumentException("Unable to download cards from TappedOut at this time. Be sure you have logged in recently");
      }
      
      return TappedImporter.importFromTappedOutFolders(
              numberOfCards,
              featuredCard,
              cmd.hasOption(CliOptions.EXCLUDE_COMMONS),
              "TappedCrawler\\decks\\" + featuredCardSlug);
    }
    catch (Exception e) {
      LOGGER.error("Unable to crawl for decks at this time", e);
      System.exit(-1);
    }
    
    return Collections.emptyMap();
  }

  /**
   * This method will iterate over all of the files in a directory and generate a card list
   * from the file names
   *
   * @param cmd         The command line object
   * @param directory   The directory to pull file names from
   * @return            A Card list of all of the files with valid card names
   */
  private static List<Card> createCardListFromFileNamesInFolder(CommandLine cmd, File directory) {
    List<String> cardNames = new ArrayList<>();

    File file = new File(directory == null
            ? cmd.getOptionValue(CliOptions.GENERATE_CARD_LIST_FROM_DIR)
            : directory.getAbsolutePath());
    if(file.listFiles() == null) {
      LOGGER.error("Unable to open directory '{}'", file.getAbsolutePath());
      System.exit(-1);
    }

    for (final File fileEntry : file.listFiles()) {
      if (fileEntry.isDirectory()) {
        createCardListFromFileNamesInFolder(cmd, fileEntry);
      }
      else {
        cardNames.add(fileEntry.getName().replaceAll("\\..*", ""));
      }
    }
    return cardNames.stream()
            .map(CardReader::lookupCard)
            .collect(Collectors.toList());
  }
}
