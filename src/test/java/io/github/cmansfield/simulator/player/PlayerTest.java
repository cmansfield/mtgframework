package io.github.cmansfield.simulator.player;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.DeckReader;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.deck.Deck;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;


public class PlayerTest {
  private Player player;

  @BeforeMethod
  public void setUp() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = DeckReader.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);
    this.player = new Player(deck);
  }

  @Test
  public void test_draw() throws IOException {
    assertEquals(player.getZone(Zone.LIBRARY).size(), Format.COMMANDER.getMaxDeckSize() - 1);
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

  @Test
  public void test_copyConstructor() {
    String copyName = "Shape shifter";
    int copyLife = 100_000;
    player.setLife(40);
    Player copyPlayer = new Player(player);
    copyPlayer.setPlayerName(copyName);
    copyPlayer.setLife(copyLife);
    copyPlayer.getZone(Zone.LIBRARY).forEach(playerCard -> playerCard.setControllerName(null));
    copyPlayer.moveZone(Zone.LIBRARY, Zone.GRAVEYARD, 30);

    assertNotEquals(player, copyPlayer);
    assertEquals(player.getZone(Zone.LIBRARY).size(), 99);
    assertEquals((int)player.getLife(), 40);
    assertNotEquals(player.getPlayerName(), copyName);
    assertEquals(copyPlayer.getPlayerName(), copyName);
    assertEquals(copyPlayer.getZone(Zone.GRAVEYARD).size(), 30);
    assertEquals((int)copyPlayer.getLife(), copyLife);
    player.getZone(Zone.LIBRARY).forEach(playerCard -> {
      assertNotNull(playerCard.getControllerName());
    });
  }
}
