package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.beginningSteps.BeginningStep;
import io.github.cmansfield.simulator.turn.beginningSteps.UntapStep;


public class BeginningPhase implements Phase {

  private BeginningStep beginningStep;

  public BeginningPhase() {
    this.beginningStep = new UntapStep();
  }

  public void setBeginningStep(BeginningStep beginningStep) {
    this.beginningStep = beginningStep;
  }

  @Override
  public void perform(GameManager gameManager) {
    System.out.printf("%n-- %s's turn --%n", gameManager.getActivePlayer().getPlayerName());
    System.out.println("This is the beginning phase");

    while(this.beginningStep != null) {
      this.beginningStep.perform(gameManager, this);
    }

    gameManager.setPhase(new PreCombatMainPhase());
  }
}
