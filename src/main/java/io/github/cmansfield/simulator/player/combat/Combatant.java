package io.github.cmansfield.simulator.player.combat;

import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.player.Player;

import java.util.List;


public class Combatant {
  private List<Combatant> creaturesToDefend;
  private PlayerCard playerCard;
  private Player targetPlayer;
  private int baseToughness;
  private int basePower;
  private int tougness;

  public Combatant(PlayerCard playerCard) {
    this.playerCard = playerCard;
    // TODO - complete
  }

  public void dealDamage(int damage) {
    tougness -= damage;
  }
}
