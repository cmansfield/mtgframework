package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class Deck {
  private Map<String,Integer> quantity;
  private List<Colors> deckColors;
  private List<Card> cards;

  /**
   * Constructor that will remove duplicates from the card list
   * and keep track of the number of copies of a card in the
   * quantity map
   *
   * @param cards - List of cards to create the deck with
   */
  public Deck(List<Card> cards) {
    this.deckColors = new ArrayList<>();
    this.quantity = new HashMap<>();

    this.cards = cards.stream().filter(card -> {
      String key = card.getName();
      if(this.quantity.containsKey(key)) {
        this.quantity.put(key, this.quantity.get(key) + 1);
        return false;
      }
      else {
        this.quantity.put(key, 1);
        if(card.getColors() == null) {
          return true;
        }

        card.getColors().forEach(color -> {
          Colors colorEnum = Colors.find(color);
          if(!this.deckColors.contains(colorEnum)) {
            this.deckColors.add(colorEnum);
          }
        });

        return true;
      }
    }).collect(Collectors.toList());
  }

  public List<Card> getCards() {
    return this.cards;
  }

  public List<Colors> getDeckColors() {
    return new ArrayList<>(this.deckColors);
  }

  /**
   * Returns the number of copies of a card stored in the deck
   *
   * @param key - Card name to get quantity for
   * @return    - The number of copies of a card
   */
  public Integer getQuantity(final String key) {
    if(!this.quantity.containsKey(key)) {
      return 0;
    }

    return this.quantity.get(key);
  }

  @Override
  public String toString() {
    return this.cards.stream()
            .map(card -> String.format("%dx %s", this.quantity.get(card.getName()), card.getName()))
            .collect(Collectors.joining(", "));
  }
}


