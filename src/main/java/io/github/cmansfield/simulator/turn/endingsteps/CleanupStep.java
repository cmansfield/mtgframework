package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.actions.DiscardAction;
import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CleanupStep implements EndingStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(CleanupStep.class);
  private static final Logger NEW_LINE_LOGGER = LoggerFactory.getLogger("newline");

  @Override
  public void perform(GameManager gameManager, EndingPhase endingPhase) {
    LOGGER.trace("Cleanup Step");

    Player activePlayer = gameManager.getActivePlayer();
    int amount = activePlayer.getZone(Zone.HAND).size() - GameConstants.MAX_HAND_SIZE.value();

    if(amount > 0) {
      gameManager.addToStack(new DiscardAction(gameManager, amount));
      gameManager.resolveStack();
    }

    gameManager.nextPlayersTurn();

    endingPhase.setEndingStep(null);

    NEW_LINE_LOGGER.trace("");
  }
}
