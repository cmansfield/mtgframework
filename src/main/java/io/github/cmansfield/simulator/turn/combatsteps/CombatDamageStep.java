package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CombatDamageStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(CombatDamageStep.class);

  @Override
  public void perform(GameManager gameManager, CombatPhase combatPhase) {
    LOGGER.trace("Combat Damage Step");
    combatPhase.setCombatStep(new EndOfCombatStep());
  }
}
