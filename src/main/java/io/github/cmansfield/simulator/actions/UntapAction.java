package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.filters.PlayerCardFilter;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.player.constants.CardState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UntapAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(UntapAction.class);
  private Game game;

  public UntapAction(Game game) {
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = game.getActivePlayer();

    LOGGER.trace(
            "Cards Untapped: {}",
            PlayerCardFilter.filter(
                    activePlayer.getZone(Zone.BATTLEFIELD),
                    new PlayerCard.PlayerCardBuilder()
                            .cardState(CardState.TAPPED)
                            .build()).size());

    activePlayer.getZone(Zone.BATTLEFIELD).forEach(card -> {
      if(card.getCardState() == CardState.TAPPED) {
        card.setCardState(CardState.UNTAPPED);
      }
    });
  }

  @Override
  public Action copy(Game game) {
    return new UntapAction(game);
  }
}
