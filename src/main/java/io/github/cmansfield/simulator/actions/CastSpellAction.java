package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.player.PlayerUtils;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CastSpellAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(CastSpellAction.class);

  private Game game;
  private PlayerCard playerCard;

  public CastSpellAction(Game game, PlayerCard playerCard) {
    this.game = game;
    this.playerCard = playerCard;
  }

  @Override
  public boolean canRespondTo() {
    return true;
  }

  @Override
  public void execute() {
    Player activePlayer = game.getActivePlayer();

    LOGGER.trace("Spell Cast: {}", playerCard.getName());

    // Get the land from the battlefield
    List<PlayerCard> availableMana = PlayerUtils.getUntappedMana(activePlayer);
    Double manaToTap = playerCard.getCmc();

    if(manaToTap == null) {
      throw new IllegalArgumentException("Player card passed in must have a cmc value inorder to be cast");
    }

    if(availableMana.size() < manaToTap.intValue()) {
      throw new IllegalStateException("Not enough mana to cast this spell");
    }

    for(int i = 0; i < manaToTap; ++i) {
      availableMana.get(i).setCardState(CardState.TAPPED);
    }

    if(playerCard.getTypes().contains("Creature")) {
      playerCard.setCardState(CardState.SUMMONING_SICKNESS);
    }

    activePlayer.moveZone(Zone.HAND, Zone.BATTLEFIELD, playerCard);
  }
}
