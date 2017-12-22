package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.EndingPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class EndStep implements EndingStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(EndStep.class);

  @Override
  public void perform(Game game, EndingPhase endingPhase) {
    LOGGER.trace("End Step");

    if(endingPhase.isEndPhase()) {
      return;
    }

    endingPhase.setEndingStep(new CleanupStep());
  }
}
