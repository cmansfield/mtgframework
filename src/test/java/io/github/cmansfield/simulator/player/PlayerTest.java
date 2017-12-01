package io.github.cmansfield.simulator.player;

import io.github.cmansfield.io.LoadDeck;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.deck.Deck;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;


public class PlayerTest {
  private Player player;

  @BeforeMethod
  public void setUp() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);
    this.player = new Player(deck);
  }

  @Test
  public void test_draw() throws IOException {
    assertEquals(player.getZone(Zone.LIBRARY).size(), 99);
    assertEquals(player.getZone(Zone.HAND).size(), 0);
    assertEquals(player.getZone(Zone.COMMAND).size(), 1);

    player.draw(7);
    assertEquals(player.getZone(Zone.LIBRARY).size(), 92);
    assertEquals(player.getZone(Zone.HAND).size(), 7);

    List<Zone> unchangedZones = new ArrayList<>();
    unchangedZones.add(Zone.GRAVEYARD);
    unchangedZones.add(Zone.EXILE);
    unchangedZones.add(Zone.ANTE);
    unchangedZones.add(Zone.SCRAPYARD);

    unchangedZones.forEach(zone -> {
      assertEquals(player.getZone(zone).size(), 0);
    });
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void test_getZone_bad() throws IOException {
    player.getZone(Zone.STACK);
  }

  @Test
  public void test_moveZone() {
    player.moveZone(Zone.LIBRARY, Zone.GRAVEYARD, 11);

    List<PlayerCard> library = player.getZone(Zone.LIBRARY);
    List<PlayerCard> graveyard = player.getZone(Zone.GRAVEYARD);

    assertNotNull(library);
    assertNotNull(graveyard);
    assertEquals(library.size(), 88);
    assertEquals(graveyard.size(), 11);
  }
}
