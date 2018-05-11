package io.github.cmansfield.simulator.game.events;

import io.github.cmansfield.simulator.player.Player;
import javafx.util.Pair;

import java.util.Observable;

//!IMPORTANT! Memory Leak
//        All observers need to un-subscribe when they are no longer going to be used, or else
//        they are kept in memory because the Observable keeps them in memory.
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
