package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DrawAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DrawAction.class);
  private GameManager gameManager;
  private int amount;

  public DrawAction(GameManager gameManager,int amount) {
    this.gameManager = gameManager;
    this.amount = amount;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = gameManager.getActivePlayer();

    this.gameManager.getActivePlayer().draw(this.amount);

    String message = this.amount == 1 ? "drew a card" : String.format("drew %d cards", this.amount);
    LOGGER.trace(
            "{} {}\tLibrary: {} Hand: {}",
            activePlayer.getPlayerName(),
            message,
            activePlayer.getZone(Zone.LIBRARY).size(),
            activePlayer.getZone(Zone.HAND).size());
  }
}
