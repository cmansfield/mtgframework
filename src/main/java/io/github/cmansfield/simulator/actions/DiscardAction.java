package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiscardAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DiscardAction.class);

  private GameManager gameManager;
  private int amount;

  public DiscardAction(GameManager gameManager, int amount) {
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

    if(activePlayer.getZone(Zone.HAND).size() < this.amount || this.amount < 1) {
      throw new IllegalArgumentException(String.format("Player does not have %d cards in hand to discard", this.amount));
    }

    LOGGER.trace("Cards discarded from hand: {}", this.amount);

    activePlayer.shuffle(Zone.HAND);
    activePlayer.moveZone(Zone.HAND, Zone.GRAVEYARD, this.amount);
  }
}
