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
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CastSpellActionTest {

  @Test
  public void test_CastSpellAction() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    PlayerCard playerCard = new PlayerCard(CardReader.lookupCard("Shimmer Myr"), playerName);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    CastSpellAction action = new CastSpellAction(mockGame, playerCard);

    // Check values before the test
    mana.forEach(card -> {
      assertEquals(card.getCardState(), CardState.UNTAPPED);
    });
    assertEquals(playerCard.getCardState(), CardState.UNTAPPED);

    // Run the test
    action.execute();

    // Verify changes
    mana.forEach(card -> {
      assertEquals(card.getCardState(), CardState.TAPPED);
    });
    assertEquals(playerCard.getCardState(), CardState.SUMMONING_SICKNESS);
    verify(mockGame).getActivePlayer();
    verify(mockPlayer).getZone(Zone.BATTLEFIELD);
    verify(mockPlayer).moveZone(Zone.HAND, Zone.BATTLEFIELD, playerCard);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_CastSpellAction_notEnoughMana() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    PlayerCard playerCard = new PlayerCard(CardReader.lookupCard("Shimmer Myr"), playerName);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    CastSpellAction action = new CastSpellAction(mockGame, playerCard);

    // Check values before the test
    mana.forEach(card -> {
      assertEquals(card.getCardState(), CardState.UNTAPPED);
    });
    assertEquals(playerCard.getCardState(), CardState.UNTAPPED);

    // Run the test
    action.execute();
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_CastSpellAction_notEnoughUntappedMana() {
    String playerName = "Player1";
    List<PlayerCard> mana = new ArrayList<>();
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.add(new PlayerCard(CardReader.lookupCard("Forest"), playerName));
    mana.get(0).setCardState(CardState.TAPPED);
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getZone(any(Zone.class))).thenReturn(mana);
    PlayerCard playerCard = new PlayerCard(CardReader.lookupCard("Shimmer Myr"), playerName);
    Game mockGame = mock(Game.class);
    when(mockGame.getActivePlayer()).thenReturn(mockPlayer);
    CastSpellAction action = new CastSpellAction(mockGame, playerCard);

    // Run the test
    action.execute();
  }
}
