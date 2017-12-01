package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;

public interface Phase {

  public void perform(GameManager gameManager);
}
