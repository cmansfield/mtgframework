package io.github.cmansfield.simulator.turn.beginningSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;

public class UpkeepStep implements BeginningStep {


  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    System.out.printf("\tUpkeep Step%n");
    beginningPhase.setBeginningStep(new DrawStep());
  }
}
