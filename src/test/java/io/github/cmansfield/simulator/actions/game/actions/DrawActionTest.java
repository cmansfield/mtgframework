package io.github.cmansfield.simulator.actions.game.actions;

import io.github.cmansfield.simulator.actions.game.actions.DrawAction;
import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.game.events.GameEventHandler;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.CardReader;
import io.github.cmansfield.io.DeckReader;
import io.github.cmansfield.deck.Deck;
import javafx.util.Pair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.testng.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class DrawActionTest {

  @Test
  public void test_drawAction() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    when(mockPlayer.draw(anyInt())).thenReturn(true);
    GameEventHandler mockEventHandler = mock(GameEventHandler.class);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    when(mockGame.getEventHandler()).thenReturn(mockEventHandler);
    DrawAction action = new DrawAction(mockGame, mockPlayer, 2);

    // Run the test
    action.execute();

    // Verify changes
    verify(mockGame).getEventHandler();
    verify(mockEventHandler).notifyObservers(
            GameEventType.DRAW_ACTION.toString(),
            new Pair<>(2, mockPlayer));
    verify(mockEventHandler, never()).notifyObservers(GameEventType.PLAYER_LOSS.toString(), mockPlayer);
    verify(mockPlayer).draw(2);
  }

  @Test
  public void test_drawAction_noCardToDraw() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = DeckReader.loadDeck(file.getAbsolutePath());
    assertNotNull(deck);
    Player player = new Player(deck);
    GameEventHandler mockEventHandler = mock(GameEventHandler.class);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(player);
    when(mockGame.getEventHandler()).thenReturn(mockEventHandler);
    DrawAction action = new DrawAction(mockGame, player, 100);

    // Run the test
    action.execute();

    // Verify changes
    verify(mockGame).getEventHandler();
    verify(mockEventHandler).notifyObservers(GameEventType.PLAYER_LOSS.toString(), player);
  }
}
