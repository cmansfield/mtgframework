package io.github.cmansfield.simulator.actions.player.actions;

import io.github.cmansfield.simulator.actions.Action;
import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.player.PlayerUtils;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;


public class CastSpellAction implements Action {
  private static final Logger LOGGER = LoggerFactory.getLogger(CastSpellAction.class);

  private PlayerCard playerCard;
  private Game game;

  public CastSpellAction(CastSpellAction castSpellAction, Game game) {
    this.playerCard = new PlayerCard(castSpellAction.playerCard);
    // This cannot be deep copied, it has to be passed into
    // the method or else it will infinitely create objects
    // until the stack fills up
    this.game = game;
  }

  public CastSpellAction(Game game, PlayerCard playerCard) {
    this.playerCard = playerCard;
    this.game = game;
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
    game.getEventHandler().notifyObservers(GameEventType.CAST_SPELL_ACTION.toString(), playerCard);
  }

  @Override
  public Action copy(Game game) {
    return new CastSpellAction(this, game);
  }
}
