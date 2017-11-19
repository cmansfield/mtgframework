package io.github.cmansfield.validator;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CardValidator {
  private CardValidator() {}

  public static List<Card> getListOfIncompleteCards(List<Card> cards) {
    List<Card> incompleteCards = new ArrayList<>();

    cards.forEach(card -> {
      if(!isCardClassComplete(card.getCardPojo())) {
        incompleteCards.add(card);
      }
    });

    return incompleteCards;
  }

  private static boolean isCardClassComplete(Map cardPojo) {
    for(Object key : cardPojo.keySet()) {
        if(CardConstants.find((String)key) == null) {
          return false;
        }
    }

    return true;
  }
}
