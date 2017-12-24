package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.turn.endingsteps.EndingStep;
import io.github.cmansfield.simulator.turn.endingsteps.EndStep;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class EndingPhase extends Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(EndingPhase.class);

  private EndingStep endingStep;

  public EndingPhase(Game game) {
    super(game);
    this.endingStep = new EndStep();
  }

  public void setEndingStep(EndingStep endingStep) {
    this.endingStep = endingStep;
  }

  @Override
  public void perform() {
    LOGGER.trace("This is the End phase");

    while(this.endingStep != null) {
      this.endingStep.perform(game, this);

      if(endPhase) {
        cleanUp();
        return;
      }
    }

    game.setPhase(new BeginningPhase(game));
    cleanUp();
  }
}
