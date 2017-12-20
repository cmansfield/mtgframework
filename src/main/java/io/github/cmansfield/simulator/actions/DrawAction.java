package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.constants.GameEvent;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DrawAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DrawAction.class);
  private int amount;
  private Game game;

  public DrawAction(Game game, int amount) {
    this.amount = amount;
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() throws GameException {
    Player activePlayer = game.getActivePlayer();

    try {
      game.getActivePlayer().draw(this.amount);
    }
    catch(IllegalStateException e) {
      throw new GameException(GameEvent.PLAYER_LOSS, game.getActivePlayer(), e);
    }

    String message = amount == 1 ? "drew a card" : String.format("drew %d cards", amount);
    LOGGER.trace(
            "{} {}\tLibrary: {} Hand: {}",
            activePlayer.getPlayerName(),
            message,
            activePlayer.getZone(Zone.LIBRARY).size(),
            activePlayer.getZone(Zone.HAND).size());
  }
}
