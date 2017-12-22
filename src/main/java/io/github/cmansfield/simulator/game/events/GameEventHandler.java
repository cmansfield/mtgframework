package io.github.cmansfield.simulator.game.events;

import java.util.Observer;
import java.util.HashMap;
import java.util.Map;


public final class GameEventHandler {

  private Map<String, GameEvent> events;

  public GameEventHandler() {
    this.events = new HashMap<>();
  }

  /**
   * This method is used to link observers with the subjects they are
   * interested in listening to
   *
   * @param eventKey  - The event key we want to listen for
   * @param observer  - The object that will listen for the specified observable
   */
  public void subscribeToEvent(String eventKey, Observer observer) {
    if(!events.containsKey(eventKey)) {
      events.put(eventKey, new GameEvent());
    }
    events.get(eventKey).addObserver(observer);
  }

  public void notifyObservers(String eventKey) {
    notifyObservers(eventKey, null);
  }

  public void notifyObservers(String eventKey, Object object) {
    if(!events.containsKey(eventKey)) {
      return;
    }
    events.get(eventKey).notifyOfGameEvent(eventKey, object);
  }

  /**
   * This method removes a listener from the specified observable
   *
   * @param eventKey  - The event key we want to unsubscribe from
   * @param observer  - The observer to remove
   */
  public void unsubscribeToEvent(String eventKey, Observer observer) {
    if(!events.containsKey(eventKey)) {
      return;
    }
    events.get(eventKey).deleteObserver(observer);
  }
}