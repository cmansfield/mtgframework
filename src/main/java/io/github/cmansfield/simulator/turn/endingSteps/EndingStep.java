package io.github.cmansfield.simulator.turn.endingSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;

public interface EndingStep {
  public void perform(GameManager gameManager, EndingPhase endingPhase);
}
