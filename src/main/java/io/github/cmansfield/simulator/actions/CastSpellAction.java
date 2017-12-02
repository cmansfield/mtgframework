package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.PlayerUtils;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;

import java.util.List;


public class CastSpellAction implements Action {

  private GameManager gameManager;
  private PlayerCard playerCard;

  public CastSpellAction(GameManager gameManager, PlayerCard playerCard) {
    this.gameManager = gameManager;
    this.playerCard = playerCard;
  }

  @Override
  public boolean canRespondTo() {
    return true;
  }

  @Override
  public void execute() {
    Player activePlayer = gameManager.getActivePlayer();

    // Get the land from the battlefield
    List<PlayerCard> availableMana = PlayerUtils.getUntappedMana(activePlayer);
    Double manaToTap = playerCard.getCmc();

    if(manaToTap == null) {
      return;
    }

    if(availableMana.size() < manaToTap.intValue()) {
      throw new IllegalStateException("Not enough mana to cast this spell");
    }

    for(int i = 0; i < manaToTap; ++i) {
      availableMana.get(i).setCardState(CardState.TAPPED);
    }

    activePlayer.moveZone(Zone.HAND, Zone.BATTLEFIELD, playerCard);
  }
}
