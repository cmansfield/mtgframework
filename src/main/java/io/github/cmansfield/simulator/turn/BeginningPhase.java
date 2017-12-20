package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.turn.beginningsteps.BeginningStep;
import io.github.cmansfield.simulator.turn.beginningsteps.UntapStep;
import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class BeginningPhase implements Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(BeginningPhase.class);

  private BeginningStep beginningStep;

  public BeginningPhase() {
    this.beginningStep = new UntapStep();
  }

  public void setBeginningStep(BeginningStep beginningStep) {
    this.beginningStep = beginningStep;
  }

  @Override
  public void perform(Game game) throws GameException {
    LOGGER.trace("-- {}'s turn --", game.getActivePlayer().getPlayerName());

    while(this.beginningStep != null) {
      this.beginningStep.perform(game, this);
    }

    game.setPhase(new PreCombatMainPhase());
  }
}
