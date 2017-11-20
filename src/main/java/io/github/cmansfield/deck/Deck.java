package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class Deck {
  private Map<String,Integer> quantity = new HashMap<>();
  private List<Card> cards;

  public Deck(List<Card> cards) {
    this.cards = cards.stream().filter(card -> {
      String key = card.getName();
      if(quantity.containsKey(key)) {
        quantity.put(key, quantity.get(key) + 1);
        return false;
      }
      else {
        quantity.put(key, 1);
        return true;
      }
    }).collect(Collectors.toList());
  }

  public List<Card> getCards() {
    return this.cards;
  }

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
