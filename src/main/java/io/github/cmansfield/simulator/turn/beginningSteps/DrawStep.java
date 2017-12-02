package io.github.cmansfield.simulator.turn.beginningSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.actions.DrawAction;


public class DrawStep implements BeginningStep {

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    System.out.printf("\tDraw Step%n");

    gameManager.addToStack(new DrawAction(gameManager, 1));
    gameManager.resolveStack();

    beginningPhase.setBeginningStep(null);
  }
}
