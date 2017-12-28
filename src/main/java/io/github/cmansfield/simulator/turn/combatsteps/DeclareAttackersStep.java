package io.github.cmansfield.simulator.turn.combatsteps;

import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.player.combat.Attacker;
import io.github.cmansfield.simulator.player.combat.Combat;
import io.github.cmansfield.simulator.player.constants.CardState;
import io.github.cmansfield.simulator.turn.CombatPhase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class DeclareAttackersStep implements CombatStep {
  private static final Logger LOGGER = LoggerFactory.getLogger(DeclareAttackersStep.class);

  @Override
  public void perform(Game game, CombatPhase combatPhase) {
    Player activePlayer = game.getActivePlayer();

    if(combatPhase.isEndPhase()) {
      return;
    }

    // TODO - Add better logic for picking a player
    List<Player> opponents = game.getPlayers().stream()
            .filter(player -> player != activePlayer)
            .collect(Collectors.toList());
    Player opponent = opponents.get(new Random().nextInt(opponents.size()));

    // Add attackers here
    Combat combat = game.getCombat() == null ? new Combat(game) : game.getCombat();

    List<Attacker> attackers = activePlayer
            .getZone(Zone.BATTLEFIELD).stream()
            .filter(card ->
                    card.getTypes().contains("Creature") && card.getCardState() == CardState.UNTAPPED)
            .map(card -> new Attacker(card, opponent))
            .collect(Collectors.toList());

    if(attackers.size() > 0) {
      attackers.forEach(attacker -> attacker.getPlayerCard().setCardState(CardState.TAPPED));
      combat.setAttackers(attackers);
      game.setCombat(combat);

      LOGGER.trace("{} is attacking {}", activePlayer.getPlayerName(), opponent.getPlayerName());
    }
    else {
      LOGGER.trace("Declare Attackers Step");
    }

    combatPhase.setCombatStep(new DeclareBlockersStep());
  }
}
