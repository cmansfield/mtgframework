package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gameManager.GameManager;

public class DrawAction implements Action {

  private GameManager gameManager;
  private int amount;

  public DrawAction(GameManager gameManager,int amount) {
    this.gameManager = gameManager;
    this.amount = amount;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    this.gameManager.getActivePlayer().draw(this.amount);
  }
}
