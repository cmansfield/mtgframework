package io.github.cmansfield.simulator.actions.game.actions;

import io.github.cmansfield.simulator.actions.game.actions.PlayLandAction;
import io.github.cmansfield.simulator.game.events.GameEventHandler;
import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.CardReader;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


public class PlayLandActionTest {

  @Test
  public void test_playLandAction() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    Game mockGame = mock(Game.class);
    GameEventHandler mockEventHandler = mock(GameEventHandler.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    when(mockGame.getEventHandler()).thenReturn(mockEventHandler);
    when(mockGame.hasActivePlayerPlayedLand()).thenReturn(false).thenReturn(true);
    PlayLandAction action = new PlayLandAction(mockGame);

    // Run the test
    action.execute();

    // Verify changes
    verify(mockGame).getActivePlayer();
    verify(mockGame).setActivePlayerPlayedLand(true);
    verify(mockPlayer).getZone(Zone.HAND);
    verify(mockPlayer).moveZone(eq(Zone.HAND), eq(Zone.BATTLEFIELD), any(PlayerCard.class));
    verify(mockEventHandler).notifyObservers(eq(GameEventType.PLAY_LAND_ACTION.toString()), any(PlayerCard.class));
  }

  @Test
  public void test_playLandAction_noLand() {
    String playerName = "Player1";
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(Collections.emptyList());
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    when(mockGame.hasActivePlayerPlayedLand()).thenReturn(false).thenReturn(true);
    PlayLandAction action = new PlayLandAction(mockGame);

    // Run the test
    action.execute();

    // Verify changes
    verify(mockGame).getActivePlayer();
    verify(mockGame, never()).setActivePlayerPlayedLand(true);
    verify(mockPlayer).getZone(Zone.HAND);
    verify(mockPlayer, never()).moveZone(eq(Zone.HAND), eq(Zone.BATTLEFIELD), any(PlayerCard.class));
  }
}
