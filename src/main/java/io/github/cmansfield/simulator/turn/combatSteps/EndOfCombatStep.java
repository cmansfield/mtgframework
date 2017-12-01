package io.github.cmansfield.simulator.turn.combatSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;


public class EndOfCombatStep implements CombatStep {

  @Override
  public void perform(GameManager gameManager, CombatPhase combatPhase) {
    System.out.printf("\tEnd of Combat Step%n");
    combatPhase.setCombatStep(null);
  }
}
