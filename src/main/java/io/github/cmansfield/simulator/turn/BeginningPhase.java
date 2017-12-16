package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.beginningsteps.BeginningStep;
import io.github.cmansfield.simulator.turn.beginningsteps.UntapStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
  public void perform(GameManager gameManager) {
    LOGGER.trace("-- {}'s turn --", gameManager.getActivePlayer().getPlayerName());

    while(this.beginningStep != null) {
      this.beginningStep.perform(gameManager, this);
    }

    gameManager.setPhase(new PreCombatMainPhase());
  }
}
