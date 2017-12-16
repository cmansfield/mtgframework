package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpkeepStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(UpkeepStep.class);

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    LOGGER.trace("Upkeep Step");
    beginningPhase.setBeginningStep(new DrawStep());
  }
}
