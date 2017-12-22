package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;


public final class GameManager implements Observer {
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
    game.subscribeToEvent(GameEventType.PLAYER_LOSS.toString(), this);
    game.subscribeToEvent(GameEventType.PLAYER_DEATH.toString(), this);
//    game.subscribeToEvent(GameEventType.TIE.toString(), this);

    for(int i = 0; i < numberOfTurns; ++i) {
      game.perform();

      if(game.getPlayerCount() < 2) {
        break;
      }
    }

    if(isPrimaryGm) {
      LOGGER.info("{} won!", game.getActivePlayer().getPlayerName());
      LOGGER.info("End of Game");
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    Pair<String,Object> pair = (Pair<String,Object>)arg;
    GameEventType gameEventType = GameEventType.find(pair.getKey());
    if(gameEventType == null) {
      throw new IllegalArgumentException("The GameManager class should only receive notifications from events within GameEventType");
    }

    if(gameEventType == GameEventType.PLAYER_DEATH || gameEventType == GameEventType.PLAYER_LOSS) {
      Player player = (Player)pair.getValue();
      game.removePlayer(player);
      game.setPhase(new BeginningPhase());
      if(isPrimaryGm) {
        LOGGER.info("{} lost the game", player);
      }
    }
    else {
      throw new IllegalStateException("The GameManager is subscribed to the wrong game event");
    }
  }
}
