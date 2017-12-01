package io.github.cmansfield.simulator.turn.beginningSteps;

import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;


public class DrawStep implements BeginningStep {

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    System.out.printf("\tDraw Step%n");

    gameManager.getActivePlayer().draw(1);

    beginningPhase.setBeginningStep(null);
  }
}
