package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.Game;

public interface Action {
  public void execute();
  public boolean canRespondTo();
  // Game cannot be deep copied, it has to be passed into
  // the method or else it will infinitely create objects
  // until the stack fills up
  public Action copy(Game game);
}
