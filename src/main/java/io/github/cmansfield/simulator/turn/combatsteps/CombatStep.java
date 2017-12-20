package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.CombatPhase;

public interface CombatStep {
  public void perform(Game game, CombatPhase combatPhase);
}
