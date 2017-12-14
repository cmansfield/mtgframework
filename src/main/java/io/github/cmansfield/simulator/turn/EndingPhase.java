package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.endingsteps.EndStep;
import io.github.cmansfield.simulator.turn.endingsteps.EndingStep;


public class EndingPhase implements Phase {

  private EndingStep endingStep;

  public EndingPhase() {
    this.endingStep = new EndStep();
  }

  public void setEndingStep(EndingStep endingStep) {
    this.endingStep = endingStep;
  }

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the End phase");

    while(this.endingStep != null) {
      this.endingStep.perform(gameManager, this);
    }

    gameManager.setPhase(new BeginningPhase());
  }
}
