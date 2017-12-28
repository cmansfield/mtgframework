package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.LoggerFactory;
import javafx.util.Pair;
import org.slf4j.Logger;


public class UntapAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(UntapAction.class);

  private Player targetPlayer;
  private Game game;

  public UntapAction(UntapAction untapAction, Game game) {
    this(game, untapAction.targetPlayer);
  }

  public UntapAction(Game game, Player targetPlayer) {
    this.targetPlayer = targetPlayer;
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    int cardsUntapped = (int)targetPlayer
            .getZone(Zone.BATTLEFIELD).stream()
            .filter(playerCard -> playerCard.getCardState() != CardState.UNTAPPED)
            .count();

    targetPlayer.getZone(Zone.BATTLEFIELD).forEach(card -> {
      if(card.getCardState() == CardState.SUMMONING_SICKNESS) {   // NOSONAR - Did this for readability
        card.setCardState(CardState.UNTAPPED);
      }
      else if(card.getCardState() == CardState.TAPPED) {
        card.setCardState(CardState.UNTAPPED);
      }
    });

    game.getEventHandler().notifyObservers(
            GameEventType.UNTAP_ACTION.toString(),
            new Pair<>(cardsUntapped, targetPlayer));

    LOGGER.trace("Cards set to Untapped: {}", cardsUntapped);
  }

  @Override
  public Action copy(Game game) {
    return new UntapAction(this, game);
  }
}
