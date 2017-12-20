package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class BeginningOfCombatStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(BeginningOfCombatStep.class);

  @Override
  public void perform(Game game, CombatPhase combatPhase) {
    LOGGER.trace("Beginning of Combat Step");
    combatPhase.setCombatStep(new DeclareAttackersStep());
  }
}
