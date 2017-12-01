package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.Card;

import java.util.Collections;
import java.util.List;


public class PreCombatMainPhase implements Phase {

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Pre-Combat Main phase");

    Player activePlayer = gameManager.getActivePlayer();

    // If the player hasn't played a land yet, and has one to play
    // this will randomly select one to add to the battlefield
    if(!gameManager.hasActivePlayerPlayedLand()) {
      List<PlayerCard> land = CardFilter
              .filter((List)activePlayer
                      .getZone(Zone.HAND),
                      new Card.CardBuilder()
                              .types(Collections.singletonList("Land"))
                              .build());

      if(!land.isEmpty()) {
        Collections.shuffle(land);
        activePlayer.moveZone(Zone.HAND, Zone.BATTLEFIELD, land.get(0));
      }
    }

    gameManager.setPhase(new CombatPhase());
  }
}
