package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DeclareAttackersStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(DeclareAttackersStep.class);

  @Override
  public void perform(Game game, CombatPhase combatPhase) {
    LOGGER.trace("Declare Attackers Step");
    combatPhase.setCombatStep(new DeclareBlockersStep());
  }
}
