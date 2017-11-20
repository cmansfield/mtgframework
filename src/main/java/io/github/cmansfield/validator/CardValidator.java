package io.github.cmansfield.validator;

import io.github.cmansfield.card.constants.CardConstants;
import io.github.cmansfield.card.Card;

import java.util.*;


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

  public static Set<String> getListOfLegalities(List<Card> cards) {
    Set<String> legalities = new HashSet<>();

    if(cards == null) {
      return null;
    }

    cards.forEach(card -> {
      List<Map<String,String>> listOfLegalities = card.getLegalities();

      if(listOfLegalities == null) {
        return;
      }

      listOfLegalities.forEach(legalMap -> {
        String format = legalMap.get("format");
        if(!legalities.contains(format)) {
          legalities.add(format);
        }
      });
    });

    return legalities;
  }
}
