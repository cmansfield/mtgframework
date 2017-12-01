package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.actions.PlayLandAction;
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

    gameManager.getStack().add(new PlayLandAction(gameManager));
    gameManager.resolveStack();

    gameManager.setPhase(new CombatPhase());
  }
}
