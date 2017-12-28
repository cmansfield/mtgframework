package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.player.Player;
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
    Player activePlayer = game.getActivePlayer();
    LOGGER.trace("-- {}'s turn ({} life)--", activePlayer.getPlayerName(), activePlayer.getLife());

    while(this.beginningStep != null) {
      this.beginningStep.perform(game, this);

      if(endPhase) {
        cleanUp();
        return;
      }
    }

    game.setPhase(new PreCombatMainPhase(game));
    cleanUp();
  }
}
