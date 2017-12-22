package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UpkeepStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(UpkeepStep.class);

  @Override
  public void perform(Game game, BeginningPhase beginningPhase) {
    LOGGER.trace("Upkeep Step");

    if(beginningPhase.isEndPhase()) {
      return;
    }

    beginningPhase.setBeginningStep(new DrawStep());
  }
}
