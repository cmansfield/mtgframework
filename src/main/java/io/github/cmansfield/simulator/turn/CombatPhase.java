package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.combatSteps.BeginningOfCombatStep;
import io.github.cmansfield.simulator.turn.combatSteps.CombatStep;


public class CombatPhase implements Phase {

  private CombatStep combatStep;

  public CombatPhase() {
    this.combatStep = new BeginningOfCombatStep();
  }

  public void setCombatStep(CombatStep combatStep) {
    this.combatStep = combatStep;
  }

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Combat phase");

    while(this.combatStep != null) {
      this.combatStep.perform(gameManager, this);
    }

    gameManager.setPhase(new PostCombatMainPhase());
  }
}
