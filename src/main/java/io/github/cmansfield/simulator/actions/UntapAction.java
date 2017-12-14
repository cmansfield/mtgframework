package io.github.cmansfield.simulator.actions;

import io.github.cmansfield.filters.PlayerCardFilter;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.gamemanager.GameManager;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.player.constants.CardState;


public class UntapAction implements Action {

  private GameManager gameManager;

  public UntapAction(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  @Override
  public boolean canRespondTo() {
    return false;
  }

  @Override
  public void execute() {
    Player activePlayer = this.gameManager.getActivePlayer();

    // TODO - Check these counts, the number of cards untapped seems off
    System.out.println(String.format("\t\tCards Untapped: %d",
            PlayerCardFilter.filter(
                    activePlayer.getZone(Zone.BATTLEFIELD),
                    new PlayerCard.PlayerCardBuilder()
                            .cardState(CardState.TAPPED)
                            .build()).size()));

    activePlayer.getZone(Zone.BATTLEFIELD).forEach(card -> {
      card.setCardState(CardState.UNTAPPED);
    });
  }
}
