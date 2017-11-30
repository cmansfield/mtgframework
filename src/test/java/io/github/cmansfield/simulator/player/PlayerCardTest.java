package io.github.cmansfield.simulator.player;

import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.simulator.constants.Zone;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class PlayerCardTest {

  @Test
  public void test_getOwner() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);

    Player player = new Player(deck, 20);
    List<PlayerCard> library = player.getZone(Zone.LIBRARY);
    assertTrue(library.size() > 0);
    assertTrue(library.get(0) instanceof PlayerCard);
    assertEquals(player, library.get(0).getOwner());
    assertEquals(player, library.get(0).getController());
  }
}
