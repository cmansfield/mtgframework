package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.actions.CastSpellAction;
import io.github.cmansfield.simulator.actions.PlayLandAction;
import io.github.cmansfield.simulator.gameManager.GameManager;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.simulator.player.PlayerUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class PreCombatMainPhase implements Phase {

  @Override
  public void perform(GameManager gameManager) {
    System.out.println("This is the Pre-Combat Main phase");

    Player activePlayer = gameManager.getActivePlayer();

    // Add MinMax logic here in the future

    gameManager.addToStack(new PlayLandAction(gameManager));
    gameManager.resolveStack();

    List<PlayerCard> availableMana = PlayerUtils.getUntappedMana(activePlayer);
    List<PlayerCard> cardsThatCanBeCast = new ArrayList<>(activePlayer.getZone(Zone.HAND));

    // Get a list of all cards that we have the mana to cast
    cardsThatCanBeCast = cardsThatCanBeCast.stream().filter(card -> {
      Double cardCmc = card.getCmc();
      if(cardCmc == null) {
        return false;
      }
      return cardCmc.intValue() <= availableMana.size();
    }).collect(Collectors.toList());

    // If there are cards we can cast then cast one
    if(cardsThatCanBeCast.size() > 0) {
      // TODO - Allow all card types to be cast in the future
      List<PlayerCard> creatureCards = CardFilter.filter(
              (List)cardsThatCanBeCast,
              new Card.CardBuilder()
                      .types(Collections.singletonList("Creature"))
                      .build());

      Collections.shuffle(creatureCards);

      gameManager.addToStack(new CastSpellAction(gameManager, creatureCards.get(0)));
      gameManager.resolveStack();
    }

    gameManager.setPhase(new CombatPhase());
  }
}
