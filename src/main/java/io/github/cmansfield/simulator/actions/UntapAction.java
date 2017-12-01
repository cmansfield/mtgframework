package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.player.constants.CardState;


public class UntapAction implements Action {

  private GameManager gameManager;

  public UntapAction(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = this.gameManager.getActivePlayer();

    activePlayer.getZone(Zone.BATTLEFIELD).forEach(card -> {
      card.setCardState(CardState.UNTAPPED);
    });
  }
}
