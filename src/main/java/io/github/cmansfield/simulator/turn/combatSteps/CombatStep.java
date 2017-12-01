package io.github.cmansfield.simulator.turn.combatSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;

public interface CombatStep {
  public void perform(GameManager gameManager, CombatPhase combatPhase);
}
