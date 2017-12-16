package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EndStep implements EndingStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(EndStep.class);

  @Override
  public void perform(GameManager gameManager, EndingPhase endingPhase) {
    LOGGER.trace("End Step");
    endingPhase.setEndingStep(new CleanupStep());
  }
}
