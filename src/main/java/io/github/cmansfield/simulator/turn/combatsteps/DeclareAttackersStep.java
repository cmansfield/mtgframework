package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeclareAttackersStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(DeclareAttackersStep.class);

  @Override
  public void perform(GameManager gameManager, CombatPhase combatPhase) {
    LOGGER.trace("Declare Attackers Step");
    combatPhase.setCombatStep(new DeclareBlockersStep());
  }
}
