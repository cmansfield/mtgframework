package io.github.cmansfield.simulator.player.combat;

import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.player.Player;


public class Attacker {
  private PlayerCard playerCard;
  private int baseToughness;
  private Player targetPlayer;
  private int basePower;
  private int tougness;

  public Attacker(PlayerCard playerCard, Player targetPlayer) {
    this.playerCard = playerCard;
    this.targetPlayer = targetPlayer;

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

  public PlayerCard getPlayerCard() {
    return playerCard;
  }

  public int getBasePower() {
    return basePower;
  }

  public Player getTargetPlayer() {
    return targetPlayer;
  }

  public void dealDamage(int damage) {
    tougness -= damage;
  }
}
