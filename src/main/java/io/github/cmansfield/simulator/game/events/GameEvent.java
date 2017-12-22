package io.github.cmansfield.simulator.game.events;

import io.github.cmansfield.simulator.player.Player;
import javafx.util.Pair;

import java.util.Observable;


public class GameEvent extends Observable {
  public void notifyOfGameEvent(String gameEvent) {
    notifyOfGameEvent(gameEvent, null);
  }

  public void notifyOfGameEvent(String gameEvent, Player player) {
    setChanged();
    notifyObservers(new Pair<>(gameEvent, player));
  }

  public void notifyOfGameEvent(String gameEvent, Object object) {
    setChanged();
    notifyObservers(new Pair<>(gameEvent, object));
  }
}
