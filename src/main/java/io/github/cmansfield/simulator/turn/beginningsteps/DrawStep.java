package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.actions.DrawAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DrawStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(DrawStep.class);

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) throws GameException{
    LOGGER.trace("Draw Step");

    gameManager.addToStack(new DrawAction(gameManager, 1));
    gameManager.resolveStack();

    beginningPhase.setBeginningStep(null);
  }
}
