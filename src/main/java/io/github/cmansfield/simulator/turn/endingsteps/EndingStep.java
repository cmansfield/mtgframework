package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;

public interface EndingStep {
  public void perform(GameManager gameManager, EndingPhase endingPhase);
}
