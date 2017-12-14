package io.github.cmansfield.simulator.turn.beginningsteps;

import io.github.cmansfield.simulator.actions.UntapAction;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.turn.BeginningPhase;


public class UntapStep implements BeginningStep {

  @Override
  public void perform(GameManager gameManager, BeginningPhase beginningPhase) {
    System.out.printf("\tUntap Step%n");

    gameManager.addToStack(new UntapAction(gameManager));
    gameManager.resolveStack();

    beginningPhase.setBeginningStep(new UpkeepStep());
  }
}
