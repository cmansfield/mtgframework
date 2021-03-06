package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class CombatDamageStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(CombatDamageStep.class);

  @Override
  public void perform(Game game, CombatPhase combatPhase) {
    LOGGER.trace("Combat Damage Step");

    if(game.getCombat() != null) {
      game.getCombat().resolveCombat();
    }

    if(combatPhase.isEndPhase()) {
      return;
    }

    combatPhase.setCombatStep(new EndOfCombatStep());
  }
}
