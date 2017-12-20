package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;


// This interface is for using the state pattern on the Beginning
// phase's steps
public interface BeginningStep {
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) throws GameException;
}
