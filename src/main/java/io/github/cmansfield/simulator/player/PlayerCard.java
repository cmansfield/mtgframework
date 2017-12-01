package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.simulator.player.constants.CardState;

import java.util.Collections;
import java.util.Map;


// This is a wrapper class for the Card class
public class PlayerCard extends Card {
  private CardState cardState;
  private Player controller;
  private Player owner;

  public PlayerCard(Map cardPojo, Player owner) {
    super(cardPojo);
    this.owner = owner;
    this.controller = owner;
    this.cardState = CardState.UNTAPPED;
  }

  public Player getController() {
    return this.controller;
  }

  public Player getOwner() {
    return this.owner;
  }

  public CardState getCardState() {
    return this.cardState;
  }

  public void setCardState(CardState cardState) {
    this.cardState = cardState;
  }

  public void setController(Player player) {
    this.controller = player;
  }

  public static class PlayerCardBuilder {
    private Player controller;
    private Player owner;
    private Map cardPojo;

    public PlayerCardBuilder() {}

    public PlayerCard.PlayerCardBuilder controller(Player player) {
      this.controller = controller;
      return this;
    }

    public PlayerCard.PlayerCardBuilder owner(Player player) {
      this.owner = owner;
      return this;
    }

    public PlayerCard.PlayerCardBuilder card(Map cardPojo) {
      this.cardPojo = cardPojo;
      return this;
    }

    public PlayerCard build() {
      PlayerCard playerCard = new PlayerCard(
              this.cardPojo == null ? Collections.EMPTY_MAP : this.cardPojo,
              this.owner);

      playerCard.setController(this.controller);

      return playerCard;
    }
  }
}
