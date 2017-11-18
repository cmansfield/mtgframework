package io.github.cmansfield.validater;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class Validater {

  public static List<Card> getListOfIncompleteCards(List<Card> cards) {
    List<Card> incompleteCards = new ArrayList<>();

    cards.forEach(card -> {
      if(!isCardClassComplete(card.getCardPojo())) {
        incompleteCards.add(card);
      }
    });

    return incompleteCards;
  }

  private static boolean isCardClassComplete(LinkedHashMap cardPojo) {
    for(Object key : cardPojo.keySet()) {
        if(CardConstants.getConstant((String)key) == null) {
          return false;
        }
    }

    return true;
  }
}
