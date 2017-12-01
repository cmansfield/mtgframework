package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;


public class CombatPhase implements Phase {

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Combat phase");
    gameManager.setPhase(new PostCombatMainPhase());
  }
}
