package io.github.cmansfield.simulator.exceptions;

import io.github.cmansfield.simulator.constants.GameEvent;
import io.github.cmansfield.simulator.player.Player;


public class GameException extends Exception {

  private Player player;
  private GameEvent gameEvent;

  public GameException(GameEvent gameEvent) {
    super();
    this.gameEvent = gameEvent;
  }

  public GameException(GameEvent gameEvent, Throwable cause) {
    super(cause);
    this.gameEvent = gameEvent;
  }

  public GameException(GameEvent gameEvent, Player player, Throwable cause) {
    super(cause);
    this.gameEvent = gameEvent;
    this.player = player;
  }

  public GameException(String message, GameEvent gameEvent){
    super(message);
    this.gameEvent = gameEvent;
  }

  public GameEvent getGameEvent() {
    return gameEvent;
  }

  public Player getPlayer() {
    return player;
  }
}
