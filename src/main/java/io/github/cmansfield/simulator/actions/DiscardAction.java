package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DiscardAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DiscardAction.class);

  private int amount;
  private Game game;

  public DiscardAction(DiscardAction discardAction, Game game) {
    this(game, discardAction.amount);
  }

  public DiscardAction(Game game, int amount) {
    this.amount = amount;
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = game.getActivePlayer();

    if(activePlayer.getZone(Zone.HAND).size() < amount || amount < 1) {
      throw new IllegalArgumentException(String.format("Player does not have %d cards in hand to discard", amount));
    }

    LOGGER.trace("Cards discarded from hand: {}", amount);

    activePlayer.shuffle(Zone.HAND);
    activePlayer.moveZone(Zone.HAND, Zone.GRAVEYARD, amount);
  }

  @Override
  public Action copy(Game game) {
    return new DiscardAction(this, game);
  }
}
