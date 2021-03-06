package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.actions.game.actions.UntapAction;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class UntapStep implements BeginningStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(UntapStep.class);

  @Override
  public void perform(Game game, BeginningPhase beginningPhase) {
    LOGGER.trace("Untap Step");

    game.getGameStack().add(new UntapAction(game, game.getActivePlayer()));

    if(beginningPhase.isEndPhase()) {
      return;
    }

    game.getGameStack().resolveStack();

    beginningPhase.setBeginningStep(new UpkeepStep());
  }
}
