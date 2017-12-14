package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.simulator.player.constants.CardState;


// This is a wrapper class for the Card class
public class PlayerCard extends Card {
  private CardState cardState;
  private Player controller;
  private Player owner;

  public PlayerCard(Card card, Player owner) {
    super(card);
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
    private CardState cardState;
    private Player controller;
    private Player owner;
    private Card card;

    public PlayerCardBuilder() {
      // Here for builder pattern
    }

    public PlayerCard.PlayerCardBuilder cardState(CardState cardState) {
      this.cardState = cardState;
      return this;
    }

    public PlayerCard.PlayerCardBuilder controller(Player controller) {
      this.controller = controller;
      return this;
    }

    public PlayerCard.PlayerCardBuilder owner(Player owner) {
      this.owner = owner;
      return this;
    }

    public PlayerCard.PlayerCardBuilder card(Card card) {
      this.card = card;
      return this;
    }

    public PlayerCard build() {
      PlayerCard playerCard = new PlayerCard(
              this.card == null ? new Card() : this.card,
              this.owner);

      playerCard.setController(this.controller);
      playerCard.setCardState(this.cardState);

      return playerCard;
    }
  }
}
