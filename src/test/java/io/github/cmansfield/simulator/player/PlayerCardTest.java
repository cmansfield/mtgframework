package io.github.cmansfield.simulator.player;

import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.io.DeckReader;
import io.github.cmansfield.deck.Deck;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.io.File;

import static org.testng.Assert.*;


public class PlayerCardTest {

  @Test
  public void test_getOwner() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = DeckReader.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);

    Player player = new Player(deck);
    List<PlayerCard> library = player.getZone(Zone.LIBRARY);
    assertTrue(library.size() > 0);
    assertTrue(library.get(0) instanceof PlayerCard);
    assertEquals(player.getPlayerName(), library.get(0).getOwnerName());
    assertEquals(player.getPlayerName(), library.get(0).getControllerName());
  }

  @Test
  public void test_copyConstructor() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = DeckReader.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);

    Player player = new Player(deck);
    List<PlayerCard> library = player.getZone(Zone.LIBRARY);
    PlayerCard playerCard = library.get(0);
    PlayerCard copyCard = new PlayerCard(playerCard);
    copyCard.setCardState(CardState.TAPPED);
    copyCard.setControllerName(null);

    assertNotEquals(playerCard, copyCard);
    assertEquals(playerCard.getControllerName(), player.getPlayerName());
    assertEquals(playerCard.getCardState(), CardState.UNTAPPED);
    assertNull(copyCard.getControllerName());
    assertEquals(copyCard.getCardState(), CardState.TAPPED);
  }
}
