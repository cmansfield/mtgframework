package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;

public class UpkeepStep implements BeginningStep {


  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    System.out.printf("\tUpkeep Step%n");
    beginningPhase.setBeginningStep(new DrawStep());
  }
}
