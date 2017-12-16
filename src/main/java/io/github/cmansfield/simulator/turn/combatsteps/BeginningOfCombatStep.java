package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeginningOfCombatStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(BeginningOfCombatStep.class);

  @Override
  public void perform(GameManager gameManager, CombatPhase combatPhase) {
    LOGGER.trace("Beginning of Combat Step");
    combatPhase.setCombatStep(new DeclareAttackersStep());
  }
}
