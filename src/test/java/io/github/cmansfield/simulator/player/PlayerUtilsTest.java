package io.github.cmansfield.simulator.player;

import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.LoadDeck;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.constants.CardState;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PlayerUtilsTest {

  @Test
  public void test_getUntappedMana() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());
    Player player = new Player(deck);
    int expectedMana = 32;

    assertEquals(PlayerUtils.getUntappedMana(player).size(), 0);

    player.moveZone(Zone.LIBRARY, Zone.BATTLEFIELD, Format.COMMANDER.getMaxDeckSize() - 1);
    List<PlayerCard> untappedMana = PlayerUtils.getUntappedMana(player);

    assertEquals(untappedMana.size(), expectedMana);

    int manaToTap = 10;
    for(int i = 0; i < manaToTap; ++i) {
      untappedMana.get(i).setCardState(CardState.TAPPED);
    }

    untappedMana = PlayerUtils.getUntappedMana(player);

    assertEquals(untappedMana.size(), expectedMana - manaToTap);
  }
}
