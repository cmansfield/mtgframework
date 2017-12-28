package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.actions.DiscardAction;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.EndingPhase;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class CleanupStep implements EndingStep {
  private static final Logger NEW_LINE_LOGGER = LoggerFactory.getLogger("newline");
  private static final Logger LOGGER = LoggerFactory.getLogger(CleanupStep.class);

  @Override
  public void perform(Game game, EndingPhase endingPhase) {
    LOGGER.trace("Cleanup Step");

    Player activePlayer = game.getActivePlayer();
    int amount = activePlayer.getZone(Zone.HAND).size() - GameConstants.MAX_HAND_SIZE.value();

    if(amount > 0) {
      game.addToStack(new DiscardAction(game, activePlayer, amount));
      game.resolveStack();
    }

    if(endingPhase.isEndPhase()) {
      return;
    }

    game.nextPlayersTurn();

    endingPhase.setEndingStep(null);

    NEW_LINE_LOGGER.trace("");
  }
}
