package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.Card;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;


public class PlayLandAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayLandAction.class);
  private Game game;

  public PlayLandAction(Game game) {
    this.game = game;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = game.getActivePlayer();

    // If the player hasn't played a land yet, and has one to play
    // this will randomly select one to add to the battlefield
    if(!game.hasActivePlayerPlayedLand()) {
      List land = CardFilter.filter(
              (List)activePlayer.getZone(Zone.HAND),
              new Card.CardBuilder()
                      .types(Collections.singletonList("Land"))
                      .build());

      if(!land.isEmpty()) {
        Collections.shuffle(land);
        activePlayer.moveZone(Zone.HAND, Zone.BATTLEFIELD, (PlayerCard)land.get(0));
        game.setActivePlayerPlayedLand(true);

        LOGGER.trace("Land Played: {}", ((PlayerCard)land.get(0)).getName());
      }
    }
  }
}
