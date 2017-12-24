package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.turn.combatsteps.BeginningOfCombatStep;
import io.github.cmansfield.simulator.turn.combatsteps.CombatStep;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class CombatPhase extends Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(CombatPhase.class);
  private CombatStep combatStep;

  public CombatPhase(Game game) {
    super(game);
    this.combatStep = new BeginningOfCombatStep();
  }

  public void setCombatStep(CombatStep combatStep) {
    this.combatStep = combatStep;
  }

  @Override
  public void perform() {
    LOGGER.trace("This is the Combat phase");

    while(this.combatStep != null) {
      this.combatStep.perform(game, this);

      if(endPhase) {
        cleanUp();
        return;
      }
    }

    game.setPhase(new PostCombatMainPhase(game));
    cleanUp();
  }
}
