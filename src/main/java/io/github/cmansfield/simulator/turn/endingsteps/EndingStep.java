package io.github.cmansfield.simulator.turn.endingsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.EndingPhase;

public interface EndingStep {
  public void perform(Game game, EndingPhase endingPhase);
}
