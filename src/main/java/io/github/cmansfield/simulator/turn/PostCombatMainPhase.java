package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.Game;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class PostCombatMainPhase implements Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(PostCombatMainPhase.class);

  @Override
  public void perform(Game game) {
    LOGGER.trace("This is the Post-Combat Main phase");
    game.setPhase(new EndingPhase());
  }
}
