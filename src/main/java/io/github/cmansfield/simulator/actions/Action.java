package io.github.cmansfield.simulator.actions;

public interface Action {
  public void execute();
  public boolean canRespondTo();
}
