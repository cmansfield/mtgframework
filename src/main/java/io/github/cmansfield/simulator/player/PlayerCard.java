package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;

import java.util.Map;


// This is a wrapper class for the Card class
public class PlayerCard extends Card {
  private Player controller;
  private Player owner;

  public PlayerCard(Map cardPojo, Player owner) {
    super(cardPojo);

    if(owner == null) {
      throw new IllegalArgumentException("An owner must be provided");
    }

    this.owner = owner;
    this.controller = owner;
  }

  public Player getController() {
    return this.controller;
  }

  public Player getOwner() {
    return this.owner;
  }

  public void setController(Player player) {
    this.controller = player;
  }
}
