package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.CardReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;


public class DiscardActionTest {

  @Test
  public void test_discardAction() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    DiscardAction action = new DiscardAction(mockGame, 2);

    // Run the test
    action.execute();

    // Verify changes
    verify(mockGame).getActivePlayer();
    verify(mockPlayer).shuffle(Zone.HAND);
    verify(mockPlayer).moveZone(Zone.HAND, Zone.GRAVEYARD, 2);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void test_discardAction_notEnoughCards() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    DiscardAction action = new DiscardAction(mockGame, 2);

    // Run the test
    action.execute();
  }
}
