package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PostCombatMainPhase implements Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(PostCombatMainPhase.class);

  @Override
  public void perform(GameManager gameManager) {
    LOGGER.trace("This is the Post-Combat Main phase");
    gameManager.setPhase(new EndingPhase());
  }
}
