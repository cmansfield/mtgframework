package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.deck.constants.Format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class DeckUtils {

  /**
   * Gets a count of each card in the list of decks
   *
   * @param decks - A list of deck objects to have their cards counted
   * @return      - A map with a key/value of card name and the quantity of that card
   */
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

  /**
   * Return a list of all of the cards in the deck that are not
   * commander cards
   *
   * @param deck  - Deck to filter through
   * @return      - List of cards that are not commander cards
   */
  public static List<Card> getNonCommanderCards(Deck deck) {
    if(deck.getFormat() != Format.COMMANDER) {
      throw new IllegalArgumentException("Deck is not a commander deck");
    }

    return deck.generateFullDeckList().stream()
            .filter(card -> {
              boolean isMatch = false;
              for(Card commander : deck.getFeaturedCards()) {
                isMatch |= card.getName().equalsIgnoreCase(commander.getName());
              }

              return !isMatch;
            })
            .collect(Collectors.toList());
  }
}
