package io.github.cmansfield.simulator.turn.endingSteps;

import io.github.cmansfield.simulator.gameManager.constants.GameConstants;
import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;


public class CleanupStep implements EndingStep {

  @Override
  public void perform(GameManager gameManager, EndingPhase endingPhase) {
    System.out.printf("\tCleanup Step%n");

    Player activePlayer = gameManager.getActivePlayer();
    while(activePlayer.getZone(Zone.HAND).size() > GameConstants.MAX_HAND_SIZE.value()) {
      activePlayer.shuffle(Zone.HAND);
      activePlayer.moveZone(Zone.HAND, Zone.GRAVEYARD, 1);
    }

    gameManager.nextPlayersTurn();

    endingPhase.setEndingStep(null);
  }
}
