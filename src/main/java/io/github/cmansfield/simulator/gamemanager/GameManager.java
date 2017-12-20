package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.exceptions.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class GameManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
  private boolean isPrimaryGm = true;
  private int numberOfTurns = 10_000;
  private Game game;

  public GameManager(Game game) {
    this.game = game;
  }

  public GameManager(Game game, boolean isPrimaryGm) {
    this.game = game;
    this.isPrimaryGm = isPrimaryGm;
  }

  public GameManager(GameManager gameManager, int numberOfTurns) {
    this.game = new Game(gameManager.game);
    this.numberOfTurns = numberOfTurns;
  }

  public void setPrimaryGm(boolean isPrimaryGM) {
    this.isPrimaryGm = isPrimaryGM;
  }

  public void startGame() {
    for(int i = 0; i < numberOfTurns; ++i) {
      try {
        game.perform();
      }
      catch(GameException e) {
        if(isPrimaryGm) {
          LOGGER.info("{} lost the game", game.getActivePlayer().getPlayerName());
          LOGGER.info(e.getMessage());
        }

        game.removePlayer(e.getPlayer() == null ? game.getActivePlayer() : e.getPlayer());

        if(game.getPlayerCount() < 2) {
          break;
        }
      }
    }

    if(isPrimaryGm) {
      LOGGER.info("{} won!", game.getActivePlayer().getPlayerName());
      LOGGER.info("End of Game");
    }
  }
}
