package io.github.cmansfield.simulator.player.combat;

import io.github.cmansfield.simulator.game.events.constants.GameEventType;
import io.github.cmansfield.simulator.gamemanager.Game;

import java.util.ArrayList;
import java.util.List;


public class Combat {
  private List<Attacker> attackers;
  private List<Defender> defenders;
  private Game game;

  public Combat(Game game) {
    this.attackers = new ArrayList<>();
    this.defenders = new ArrayList<>();
    this.game = game;
  }

  public void setAttackers(List<Attacker> attackers) {
    this.attackers = attackers;
  }

  public void addAttacker(Attacker combatant) {
    attackers.add(combatant);
  }

  public void addDefender(Defender combatant) {
    defenders.add(combatant);
  }

  public void resolveCombat() {
    // TODO - complete
    attackers.forEach(attacker ->
            attacker.getTargetPlayer().dealDamage(attacker.getBasePower()));

    game.getPlayers().forEach(player -> {
      if(player.getLife() <= 0) {
        game.getEventHandler().notifyObservers(GameEventType.PLAYER_DEATH.toString(), player);
      }
    });
  }
}
