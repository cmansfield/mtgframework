package io.github.cmansfield;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.DeckAppraiser;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.filters.PlayerCardFilter;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.io.SaveCards;
import io.github.cmansfield.io.web.GetUpdates;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.player.PlayerCard;

import java.io.IOException;
import java.util.*;


public class App {

  private static void usage() {
    System.out.printf("Usage: mtgframework [-u | --update]");
  }

  public static void main_(String[] args) throws IOException {
    List<Card> cards;

    try {
      cards = LoadCards.loadCards();
    }
    catch (Exception e) {
      System.out.printf("Unable to load card list from file '%s'%n", IoConstants.ALL_CARDS_FILE_NAME);
      return;
    }

    Card card = LoadCards.lookupCard("Animar, Soul of Elements");
    System.out.println(card);
  }


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

    Deck doranDeck = LoadCards.loadDeck("SavedCardLists/DoranDeck.json");
    Deck ghaveDeck = LoadCards.loadDeck("SavedCardLists/GhaveDeck.json");

    Player player1 = new Player(doranDeck, 40);
    Player player2 = new Player(ghaveDeck, 40);

    List<PlayerCard> player1Cards = player1.getZone(Zone.LIBRARY);
    PlayerCard playerCardFilter = new PlayerCard
            .PlayerCardBuilder()
            .owner(player1)
            .card(new Card
                    .CardBuilder()
                    .types(Collections.singletonList("Land"))
                    .build()
                    .getCardPojo())
            .build();
    List filteredCards = PlayerCardFilter.filter(player1Cards, playerCardFilter);
    CardUtils.printCards(filteredCards);
  }
}
