package io.github.cmansfield.simulator.exceptions;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.player.Player;


public class GameException extends Exception {

  private Player player;
  private GameEventType gameEvent;

  public GameException(GameEventType gameEvent) {
    super();
    this.gameEvent = gameEvent;
  }

  public GameException(GameEventType gameEvent, Throwable cause) {
    super(cause);
    this.gameEvent = gameEvent;
  }

  public GameException(GameEventType gameEvent, Player player, Throwable cause) {
    super(cause);
    this.gameEvent = gameEvent;
    this.player = player;
  }

  public GameException(String message, GameEventType gameEvent){
    super(message);
    this.gameEvent = gameEvent;
  }

  public GameEventType getGameEvent() {
    return gameEvent;
  }

  public Player getPlayer() {
    return player;
  }
}
