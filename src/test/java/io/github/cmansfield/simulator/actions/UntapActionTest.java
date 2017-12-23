package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.CardReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class UntapActionTest {

  @Test
  public void test_uptapAction() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.forEach(card -> card.setCardState(CardState.TAPPED));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    UntapAction action = new UntapAction(mockGame);

    // Run the test
    action.execute();

    // Verify changes
    assertEquals(mana.size(), 3);
    mana.forEach(card -> assertEquals(card.getCardState(), CardState.UNTAPPED));
    verify(mockGame).getActivePlayer();
    verify(mockPlayer, times(2)).getZone(Zone.BATTLEFIELD);
  }

  @Test
  public void test_uptapAction_dontSetSicknessToUntapped() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.get(0).setCardState(CardState.SUMMONING_SICKNESS);
    mana.get(1).setCardState(CardState.UNTAPPED);
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    UntapAction action = new UntapAction(mockGame);

    // Run the test
    action.execute();

    // Verify changes
    assertEquals(mana.size(), 3);
    assertEquals(mana.get(0).getCardState(), CardState.SUMMONING_SICKNESS);
    assertEquals(mana.get(1).getCardState(), CardState.UNTAPPED);
    assertEquals(mana.get(2).getCardState(), CardState.UNTAPPED);
    verify(mockGame).getActivePlayer();
    verify(mockPlayer, times(2)).getZone(Zone.BATTLEFIELD);
  }
}
