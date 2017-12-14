package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;


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
    Player activePlayer = gameManager.getActivePlayer();

    this.gameManager.getActivePlayer().draw(this.amount);

    System.out.println(String.format(
            "\t\t%s drew card\tLibrary: %d Hand: %d",
            activePlayer.getPlayerName(),
            activePlayer.getZone(Zone.LIBRARY).size(),
            activePlayer.getZone(Zone.HAND).size()));
  }
}
