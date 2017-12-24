package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class PostCombatMainPhase extends Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(PostCombatMainPhase.class);

  public PostCombatMainPhase(Game game) {
    super(game);
  }

  @Override
  public void perform() {
    LOGGER.trace("This is the Post-Combat Main phase");

    cleanUp();

    if(endPhase) {
      return;
    }

    game.setPhase(new EndingPhase(game));
  }
}
