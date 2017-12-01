package io.github.cmansfield.simulator.turn.endingSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.EndingPhase;


public class EndStep implements EndingStep {

  @Override
  public void perform(GameManager gameManager, EndingPhase endingPhase) {
    System.out.printf("\tEnd Step%n");
    endingPhase.setEndingStep(new CleanupStep());
  }
}
