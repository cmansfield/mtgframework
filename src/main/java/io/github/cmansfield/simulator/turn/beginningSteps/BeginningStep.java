package io.github.cmansfield.simulator.turn.beginningSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;


// This interface is for using the state pattern on the Beginning
// phase's steps
public interface BeginningStep {
  public void perform(GameManager gameManager, BeginningPhase beginningPhase);
}
