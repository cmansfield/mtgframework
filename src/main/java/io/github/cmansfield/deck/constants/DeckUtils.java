package io.github.cmansfield.deck.constants;

import io.github.cmansfield.deck.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DeckUtils {

  public static Map<String,Integer> getCardCount(List<Deck> decks) {
    Map<String,Integer> cardCount = new HashMap<>();

    decks.forEach(deck -> {
      deck.getCards().forEach(card -> {
        String cardName = card.getName();
        if(cardCount.containsKey(cardName)) {
          cardCount.put(cardName, cardCount.get(cardName) + deck.getQuantity(cardName));
        }
        else {
          cardCount.put(cardName, deck.getQuantity(cardName));
        }
      });
    });

    return cardCount;
  }
}
