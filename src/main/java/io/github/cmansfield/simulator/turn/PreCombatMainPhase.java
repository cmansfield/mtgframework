package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.actions.CastSpellAction;
import io.github.cmansfield.simulator.actions.PlayLandAction;
import io.github.cmansfield.simulator.gamemanager.GameManager;
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

    // Add MinMax logic here in the future

    gameManager.addToStack(new PlayLandAction(gameManager));
    gameManager.resolveStack();

    while(castSpell(gameManager)) {};

    gameManager.setPhase(new CombatPhase());
  }


  private boolean castSpell(GameManager gameManager) {
    Player activePlayer = gameManager.getActivePlayer();
    List<PlayerCard> availableMana = PlayerUtils.getUntappedMana(activePlayer);
    List<PlayerCard> cardsThatCanBeCast = new ArrayList<>(activePlayer.getZone(Zone.HAND));

    // Get a list of all cards that we have the mana to cast
    cardsThatCanBeCast = cardsThatCanBeCast.stream().filter(card -> {
      Double cardCmc = card.getCmc();
      return cardCmc != null && cardCmc.intValue() <= availableMana.size();
    }).collect(Collectors.toList());

    // If there are no cards we can cast then return
    if(cardsThatCanBeCast.isEmpty()) {
      return false;
    }

    // TODO - Allow all card types to be cast in the future
    List creatureCards = CardFilter.filter(
            (List)cardsThatCanBeCast,
            new Card.CardBuilder()
                    .types(Collections.singletonList("Creature"))
                    .build());

    if(creatureCards.isEmpty()) {
      return false;
    }

    Collections.shuffle(creatureCards);

    gameManager.addToStack(new CastSpellAction(gameManager, (PlayerCard)creatureCards.get(0)));
    gameManager.resolveStack();

    return creatureCards.size() > 1;
  }
}
