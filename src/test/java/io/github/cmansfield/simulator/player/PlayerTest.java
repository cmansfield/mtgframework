package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.simulator.constants.Zone;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.File;


public class PlayerTest {

  @Test
  // TODO - Complete this test
  public void test_shuffle() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());

    Player player = new Player(deck);
    System.out.println(player.getZone(Zone.LIBRARY).size());
    System.out.println();
    player.draw(7);
    CardUtils.printCards(player.getZone(Zone.HAND));
    player.shuffle(Zone.HAND);
    System.out.println();
    CardUtils.printCards(player.getZone(Zone.HAND));
    System.out.println();
    System.out.println(player.getZone(Zone.LIBRARY).size());
  }
}
