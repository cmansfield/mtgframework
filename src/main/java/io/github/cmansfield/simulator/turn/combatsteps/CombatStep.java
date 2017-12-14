package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;

public interface CombatStep {
  public void perform(GameManager gameManager, CombatPhase combatPhase);
}
