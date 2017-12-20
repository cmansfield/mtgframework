package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.simulator.player.constants.CardState;


// This is a wrapper class for the Card class
public class PlayerCard extends Card {
  private CardState cardState;
  private String controllerName;
  private String ownerName;


  public PlayerCard(PlayerCard playerCard) {
    this.cardState = playerCard.cardState;
    this.controllerName = playerCard.controllerName;
    this.ownerName = playerCard.ownerName;
  }

  public PlayerCard(Card card, String ownerName) {
    super(card);
    this.ownerName = ownerName;
    this.controllerName = ownerName;
    this.cardState = CardState.UNTAPPED;
  }

  public String getControllerName() {
    return this.controllerName;
  }

  public String getOwnerName() {
    return this.ownerName;
  }

  public CardState getCardState() {
    return this.cardState;
  }

  public void setCardState(CardState cardState) {
    this.cardState = cardState;
  }

  public void setControllerName(String controllerName) {
    this.controllerName = controllerName;
  }

  public static class PlayerCardBuilder {
    private String controllerName;
    private CardState cardState;
    private String ownerName;
    private Card card;

    public PlayerCardBuilder() {
      // Here for builder pattern
    }

    public PlayerCard.PlayerCardBuilder cardState(CardState cardState) {
      this.cardState = cardState;
      return this;
    }

    public PlayerCard.PlayerCardBuilder controllerName(String controllerName) {
      this.controllerName = controllerName;
      return this;
    }

    public PlayerCard.PlayerCardBuilder ownerName(String ownerName) {
      this.ownerName = ownerName;
      return this;
    }

    public PlayerCard.PlayerCardBuilder card(Card card) {
      this.card = card;
      return this;
    }

    public PlayerCard build() {
      PlayerCard playerCard = new PlayerCard(
              this.card == null ? new Card() : this.card,
              this.ownerName);

      playerCard.setControllerName(this.controllerName);
      playerCard.setCardState(this.cardState);

      return playerCard;
    }
  }
}
