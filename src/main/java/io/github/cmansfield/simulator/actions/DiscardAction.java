package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import javafx.util.Pair;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DiscardAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DiscardAction.class);

  private Player targetPlayer;
  private int amount;
  private Game game;

  public DiscardAction(DiscardAction discardAction, Game game) {
    this(game, discardAction.targetPlayer, discardAction.amount);
  }

  public DiscardAction(Game game, Player targetPlayer, int amount) {
    this.targetPlayer = targetPlayer;
    this.amount = amount;
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    if(targetPlayer.getZone(Zone.HAND).size() < amount || amount < 1) {
      throw new IllegalArgumentException(String.format("Player does not have %d cards in hand to discard", amount));
    }

    targetPlayer.shuffle(Zone.HAND);
    targetPlayer.moveZone(Zone.HAND, Zone.GRAVEYARD, amount);

    game.getEventHandler().notifyObservers(
            GameEventType.DISCARD_ACTION.toString(),
            new Pair<Integer, Player>(amount, targetPlayer));
    LOGGER.trace("{} discarded {} card from hand", targetPlayer.getPlayerName(), amount);
  }

  @Override
  public Action copy(Game game) {
    return new DiscardAction(this, game);
  }
}
