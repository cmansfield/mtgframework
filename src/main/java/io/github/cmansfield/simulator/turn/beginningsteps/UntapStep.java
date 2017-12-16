package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.actions.UntapAction;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UntapStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(UntapStep.class);

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    LOGGER.trace("Untap Step");

    gameManager.addToStack(new UntapAction(gameManager));
    gameManager.resolveStack();

    beginningPhase.setBeginningStep(new UpkeepStep());
  }
}
