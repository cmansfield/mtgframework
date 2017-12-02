package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.Card;

import java.util.Collections;
import java.util.List;


public class PlayLandAction implements Action {

  private GameManager gameManager;

  public PlayLandAction(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = gameManager.getActivePlayer();

    // If the player hasn't played a land yet, and has one to play
    // this will randomly select one to add to the battlefield
    if(!gameManager.hasActivePlayerPlayedLand()) {
      List land = CardFilter.filter(
              (List)activePlayer.getZone(Zone.HAND),
              new Card.CardBuilder()
                      .types(Collections.singletonList("Land"))
                      .build());

      if(!land.isEmpty()) {
        Collections.shuffle(land);
        activePlayer.moveZone(Zone.HAND, Zone.BATTLEFIELD, (PlayerCard)land.get(0));
        gameManager.setActivePlayerPlayedLand(true);

        System.out.println(String.format("\t\tLand Played: %s", ((PlayerCard)land.get(0)).getName()));
      }
    }
  }
}
