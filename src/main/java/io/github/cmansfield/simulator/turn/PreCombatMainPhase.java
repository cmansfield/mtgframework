package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.gameManager.GameManager;


public class PreCombatMainPhase implements Phase {

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Pre-Combat Main phase");
    gameManager.setPhase(new CombatPhase());
  }
}
