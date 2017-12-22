package io.github.cmansfield.simulator.game.events;

import java.util.Observer;
import java.util.HashMap;
import java.util.Map;


public class GameEventHandler {

  private Map<String, GameEvent> events;

  public GameEventHandler() {
    this.events = new HashMap<>();
  }

  public GameEventHandler(Map<String,GameEvent> events) {
    this.events = events;
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

  /**
   * Let all Observers know that the Observable that matches to the eventKey has changed
   *
   * @param eventKey  - The key that maps to an Observable
   * @param object    - The object to pass out to the Observers
   */
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
