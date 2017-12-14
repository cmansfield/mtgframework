package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;


public class CombatDamageStep implements CombatStep {

  @Override
  public void perform(GameManager gameManager, CombatPhase combatPhase) {
    System.out.printf("\tCombat Damage Step%n");
    combatPhase.setCombatStep(new EndOfCombatStep());
  }
}
