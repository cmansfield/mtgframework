package io.github.cmansfield.deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DeckUtils {

  private DeckUtils() {}

  /**
   * Gets a count of each card in the list of decks
   *
   * @param decks - A list of deck objects to have their cards counted
   * @return      - A map with a key/value of card name and the quantity of that card
   */
  public static Map<String,Integer> getCardCount(List<Deck> decks) {
    Map<String,Integer> cardCount = new HashMap<>();

    decks.forEach(deck ->
      deck.getCards().forEach(card -> {
        String cardName = card.getName();
        if(cardCount.containsKey(cardName)) {
          cardCount.put(cardName, cardCount.get(cardName) + deck.getQuantity(cardName));
        }
        else {
          cardCount.put(cardName, deck.getQuantity(cardName));
        }
      })
    );

    return cardCount;
  }
}
