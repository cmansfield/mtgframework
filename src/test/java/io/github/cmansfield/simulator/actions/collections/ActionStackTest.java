package io.github.cmansfield.simulator.actions.collections;

import io.github.cmansfield.simulator.actions.Action;
import io.github.cmansfield.simulator.gamemanager.Game;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ActionStackTest {

  @Test
  public void test_actionStack() {
    ActionStack actionStack = new ActionStack();
    Action mockAction = mock(Action.class);
    actionStack.add(mockAction);
    actionStack.add(mockAction);
    actionStack.add(mockAction);

    actionStack.resolveStack();

    verify(mockAction, times(3)).execute();
  }

  @Test
  public void test_actionStack_copyConstructor() {
    ActionStack actionStack = new ActionStack();
    Game mockGame = mock(Game.class);
    Action mockAction = mock(Action.class);
    Action newMockAction = mock(Action.class);
    when(mockAction.copy(mockGame)).thenReturn(newMockAction);
    actionStack.add(mockAction);

    ActionStack newStack = new ActionStack(actionStack, mockGame);

    verify(mockAction).copy(mockGame);
    assertTrue(actionStack.contains(mockAction));
    assertFalse(newStack.contains(mockAction));
    assertTrue(newStack.contains(newMockAction));
    assertFalse(actionStack.contains(newMockAction));
  }
}
