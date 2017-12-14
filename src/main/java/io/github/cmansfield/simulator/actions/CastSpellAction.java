package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.gamemanager.GameManager;
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

    System.out.println(String.format("\t\tSpell Cast: %s", this.playerCard.getName()));

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
