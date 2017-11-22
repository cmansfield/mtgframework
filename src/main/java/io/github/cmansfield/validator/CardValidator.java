package io.github.cmansfield.validator;

import io.github.cmansfield.card.constants.CardConstants;
import io.github.cmansfield.card.Card;

import java.util.*;


public final class CardValidator {
  private CardValidator() {}

  /**
   * Returns a list of cards that contain additional properties above
   * what is supplied in the CardConstants enum
   *
   * @param cards - A list of cards to verify
   * @return      - A list of cards that reported problems
   */
  public static List<Card> getListOfIncompleteCards(List<Card> cards) {
    List<Card> incompleteCards = new ArrayList<>();

    cards.forEach(card -> {
      if(!isCardClassComplete(card.getCardPojo())) {
        incompleteCards.add(card);
      }
    });

    return incompleteCards;
  }

  /**
   * Checks to see if the card has any additional properties above
   * what is supplied in the CardConstants enum
   *
   * @param cardPojo  - The object created from deserialized json
   * @return          - Returns true if the card has all of the expected properties
   */
  private static boolean isCardClassComplete(Map cardPojo) {
    for(Object key : cardPojo.keySet()) {
        if(CardConstants.find((String)key) == null) {
          return false;
        }
    }

    return true;
  }
}
