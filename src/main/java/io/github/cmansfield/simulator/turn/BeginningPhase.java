package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.turn.beginningsteps.BeginningStep;
import io.github.cmansfield.simulator.turn.beginningsteps.UntapStep;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class BeginningPhase extends Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(BeginningPhase.class);
  private BeginningStep beginningStep;

  public BeginningPhase(Game game) {
    super(game);
    this.beginningStep = new UntapStep();
  }

  public void setBeginningStep(BeginningStep beginningStep) {
    this.beginningStep = beginningStep;
  }

  @Override
  public void perform() {
    LOGGER.trace("-- {}'s turn --", game.getActivePlayer().getPlayerName());

    while(this.beginningStep != null) {
      this.beginningStep.perform(game, this);

      if(endPhase) {
        return;
      }
    }

    game.setPhase(new PreCombatMainPhase(game));
  }
}
