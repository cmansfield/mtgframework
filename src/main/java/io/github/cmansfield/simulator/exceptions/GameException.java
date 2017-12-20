package io.github.cmansfield.simulator.exceptions;

import io.github.cmansfield.simulator.constants.GameEvent;


public class GameException extends Exception {

  private GameEvent gameEvent;

  public GameException(GameEvent gameEvent) {
    super();
    this.gameEvent = gameEvent;
  }

  public GameException(GameEvent gameEvent, Throwable cause) {
    super(cause);
    this.gameEvent = gameEvent;
  }

  public GameException(String message, GameEvent gameEvent){
    super(message);
    this.gameEvent = gameEvent;
  }

  public GameEvent getGameEvent() {
    return this.gameEvent;
  }
}
