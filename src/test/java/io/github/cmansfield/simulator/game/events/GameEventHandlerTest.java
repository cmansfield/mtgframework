package io.github.cmansfield.simulator.game.events;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import javafx.util.Pair;

import java.util.Observer;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.any;


public class GameEventHandlerTest {

  private GameEventHandler gameEventHandler;

  @BeforeMethod
  public void setUp() {
    gameEventHandler = new GameEventHandler();
  }

  @Test
  public void test_subscribeToEvent() {
    Observer mockObserver = Mockito.mock(Observer.class);
    gameEventHandler.subscribeToEvent(GameEventType.PLAYER_LOSS.toString(), mockObserver);

    gameEventHandler.notifyObservers(GameEventType.PLAYER_LOSS.toString());

    verify(mockObserver, times(1)).update(any(GameEvent.class), any(Pair.class));
  }

  @Test
  public void test_subscribeToEvent_notifyWrontEvent() {
    Observer mockObserver = Mockito.mock(Observer.class);
    gameEventHandler.subscribeToEvent(GameEventType.PLAYER_LOSS.toString(), mockObserver);

    gameEventHandler.notifyObservers(GameEventType.PLAYER_DEATH.toString());

    verify(mockObserver, times(0)).update(any(), any());
  }

  @Test
  public void test_subscribeToEvent_notifyWithObject() {
    ArgumentCaptor<Pair> argCaptor = ArgumentCaptor.forClass(Pair.class);
    String message = "Message to be sent";
    Observer mockObserver = Mockito.mock(Observer.class);
    gameEventHandler.subscribeToEvent(GameEventType.PLAYER_LOSS.toString(), mockObserver);

    gameEventHandler.notifyObservers(GameEventType.PLAYER_LOSS.toString(), message);

    verify(mockObserver, times(1)).update(any(GameEvent.class), argCaptor.capture());
    Pair arg = argCaptor.getValue();
    assertEquals(arg.getKey(), GameEventType.PLAYER_LOSS.toString());
    assertEquals(arg.getValue(), message);
  }

  @Test
  public void test_unsubscribe() {
    Observer mockObserver = Mockito.mock(Observer.class);
    Map<String, GameEvent> events = new HashMap<>();
    gameEventHandler = new GameEventHandler(events);
    gameEventHandler.subscribeToEvent(GameEventType.PLAYER_LOSS.toString(), mockObserver);

    assertEquals(events.size(), 1);
    assertEquals(events.get(GameEventType.PLAYER_LOSS.toString()).countObservers(), 1);

    gameEventHandler.unsubscribeToEvent(GameEventType.PLAYER_LOSS.toString(), mockObserver);

    assertEquals(events.size(), 1);
    assertEquals(events.get(GameEventType.PLAYER_LOSS.toString()).countObservers(), 0);
  }
}
