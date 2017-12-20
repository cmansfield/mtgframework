package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.gamemanager.GameManager;

public interface Phase {

  public void perform(GameManager gameManager) throws GameException;
}
