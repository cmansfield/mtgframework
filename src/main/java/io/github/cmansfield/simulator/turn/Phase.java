package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.gamemanager.Game;
import javafx.util.Pair;

import java.util.Observable;
import java.util.Observer;


public abstract class Phase implements Observer {
  boolean endPhase;
  Game game;

  private Phase() {}

  Phase(Game game) {
    this.endPhase = false;
    this.game = game;
    game.getEventHandler().subscribeToEvent(GameEventType.END_TURN.toString(), this);
  }

  public abstract void perform();

  public boolean isEndPhase() {
    return endPhase;
  }

  @Override
  public void update(Observable o, Object arg) {
    Pair<String,Object> pair = (Pair<String,Object>)arg;
    GameEventType gameEventType = GameEventType.find(pair.getKey());

    if(gameEventType == GameEventType.END_TURN) {
      endPhase = true;
    }
  }

  protected void cleanUp() {
    game.getEventHandler().unsubscribeToEvent(GameEventType.END_TURN.toString(), this);
  }
}
