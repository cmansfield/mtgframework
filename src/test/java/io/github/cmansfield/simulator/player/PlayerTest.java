package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.simulator.constants.Zone;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class PlayerTest {

  @Test
  public void test_draw() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);

    Player player = new Player(deck);
    assertEquals(player.getZone(Zone.LIBRARY).size(), 100);
    assertEquals(player.getZone(Zone.HAND).size(), 0);

    player.draw(7);
    assertEquals(player.getZone(Zone.LIBRARY).size(), 93);
    assertEquals(player.getZone(Zone.HAND).size(), 7);

    List<Zone> unchangedZones = new ArrayList<>();
    unchangedZones.add(Zone.BATTLEFIELD);
    unchangedZones.add(Zone.GRAVEYARD);
    unchangedZones.add(Zone.EXILE);
    unchangedZones.add(Zone.COMMAND);
    unchangedZones.add(Zone.ANTE);
    unchangedZones.add(Zone.SCRAPYARD);

    unchangedZones.forEach(zone -> {
      assertEquals(player.getZone(zone).size(), 0);
    });
  }
}
