package io.github.cmansfield.simulator.player.combat;

import io.github.cmansfield.simulator.player.PlayerCard;

import java.util.List;


public class Defender {
  private List<Attacker> creaturesToDefend;
  private PlayerCard playerCard;
  private int baseToughness;
  private int basePower;
  private int tougness;

  public Defender(PlayerCard playerCard, List<Attacker> creaturesToDefend) {
    this.playerCard = playerCard;
    this.creaturesToDefend = creaturesToDefend;

    // TODO - Update this to include more accurate power and toughness
    try {
      basePower = Integer.parseInt(playerCard.getPower());
    }
    catch(NumberFormatException e) {
      basePower = 0;
    }

    try {
      baseToughness = Integer.parseInt(playerCard.getToughness());
    }
    catch(NumberFormatException e) {
      baseToughness = 0;
    }

    tougness = baseToughness;
  }

  public int getBasePower() {
    return basePower;
  }

  public int getTougness() {
    return tougness;
  }

  public void dealDamage(int damage) {
    tougness -= damage;
  }
}
