package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.actions.game.actions.DrawAction;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DrawStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(DrawStep.class);

  @Override
  public void perform(Game game, BeginningPhase beginningPhase) {
    LOGGER.trace("Draw Step");

    game.getGameStack().add(new DrawAction(game, game.getActivePlayer(), 1));

    if(beginningPhase.isEndPhase()) {
      return;
    }

    game.getGameStack().resolveStack();

    beginningPhase.setBeginningStep(null);
  }
}
