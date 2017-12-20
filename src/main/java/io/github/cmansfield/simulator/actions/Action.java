package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.exceptions.GameException;

public interface Action {
  public void execute() throws GameException;
  public boolean canRespondTo();
}
