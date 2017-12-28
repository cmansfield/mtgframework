package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import javafx.util.Pair;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class DrawAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(DrawAction.class);

  private Player targetPlayer;
  private int amount;
  private Game game;

  public DrawAction(DrawAction drawAction, Game game) {
    this(game, drawAction.targetPlayer, drawAction.amount);
  }

  public DrawAction(Game game, Player targetPlayer, int amount) {
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
    if(!targetPlayer.draw(this.amount)) {
      LOGGER.trace("No more cards to pull from library");
      game.getEventHandler().notifyObservers(GameEventType.PLAYER_LOSS.toString(), targetPlayer);
      return;
    }

    game.getEventHandler().notifyObservers(
            GameEventType.DRAW_ACTION.toString(),
            new Pair<Integer,Player>(amount, targetPlayer));

    String message = amount == 1 ? "drew a card" : String.format("drew %d cards", amount);
    LOGGER.trace(
            "{} {}\tLibrary: {} Hand: {}",
            targetPlayer.getPlayerName(),
            message,
            targetPlayer.getZone(Zone.LIBRARY).size(),
            targetPlayer.getZone(Zone.HAND).size());
  }

  @Override
  public Action copy(Game game) {
    return new DrawAction(this, game);
  }
}
