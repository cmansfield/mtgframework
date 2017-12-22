package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.gamemanager.Game;


// This interface is for using the state pattern on the Beginning
// phase's steps
public interface BeginningStep {
  public void perform(Game game, BeginningPhase beginningPhase);
}
