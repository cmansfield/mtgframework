package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;


public class PostCombatMainPhase implements Phase {

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Post-Combat Main phase");
    gameManager.setPhase(new EndingPhase());
  }
}
