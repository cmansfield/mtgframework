package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.combatsteps.BeginningOfCombatStep;
import io.github.cmansfield.simulator.turn.combatsteps.CombatStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CombatPhase implements Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(CombatPhase.class);
  private CombatStep combatStep;

  public CombatPhase() {
    this.combatStep = new BeginningOfCombatStep();
  }

  public void setCombatStep(CombatStep combatStep) {
    this.combatStep = combatStep;
  }

  @Override
  public void perform(GameManager gameManager) {
    LOGGER.trace("This is the Combat phase");

    while(this.combatStep != null) {
      this.combatStep.perform(gameManager, this);
    }

    gameManager.setPhase(new PostCombatMainPhase());
  }
}
