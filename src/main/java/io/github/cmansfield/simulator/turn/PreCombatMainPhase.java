package io.github.cmansfield.simulator.turn;

import io.github.cmansfield.simulator.actions.player.actions.CastSpellAction;
import io.github.cmansfield.simulator.actions.game.actions.PlayLandAction;
import io.github.cmansfield.simulator.player.PlayerUtils;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.Card;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


public class PreCombatMainPhase extends Phase {
  private static final Logger LOGGER = LoggerFactory.getLogger(PreCombatMainPhase.class);

  public PreCombatMainPhase(Game game) {
    super(game);
  }

  @Override
  public void perform() {
    LOGGER.trace("This is the Pre-Combat Main phase");

    // Add MinMax logic here in the future

    game.getGameStack().add(new PlayLandAction(game));
    game.getGameStack().resolveStack();

    while(castSpell(game)) {
      if(endPhase) {
        cleanUp();
        return;
      }
    }

    game.setPhase(new CombatPhase(game));
    cleanUp();
  }

  private boolean castSpell(Game game) {
    Player activePlayer = game.getActivePlayer();
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

    game.getPlayerStack().add(new CastSpellAction(game, (PlayerCard)creatureCards.get(0)));
    game.getPlayerStack().resolveStack();

    return creatureCards.size() > 1;
  }
}
