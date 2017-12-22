package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class EndOfCombatStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(EndOfCombatStep.class);

  @Override
  public void perform(Game game, CombatPhase combatPhase) {
    LOGGER.trace("End of Combat Step");

    if(combatPhase.isEndPhase()) {
      return;
    }

    combatPhase.setCombatStep(null);
  }
}
