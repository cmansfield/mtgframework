package io.github.cmansfield.validator;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;


public final class DeckValidator {

  /**
   * Returns true is the deck is format compliant
   *
   * @param deck  - The deck to check
   * @return      - Boolean true if the deck is format legal
   */
  // TODO - Check deck sizes for each of the different formats
  public static boolean isFormatCompliant(Deck deck) {
    return getNonCompliantCards(deck).size() == 0;
  }

  /**
   * Returns a list of Cards that are not legal for the listed
   * deck format
   *
   * @param deck  - Deck to check for non-compliant cards
   * @return      - A list of non-compliant cards
   */
  public static List<Card> getNonCompliantCards(Deck deck) {
    if(deck == null) {
      throw new NullPointerException("No deck supplied");
    }

    Format format = deck.getFormat();

    if(format == null) {
      throw new NullPointerException("This deck has no format set");
    }

    List<Card> cards = deck.getCards();
    return cards.stream().filter(card -> {
      return !CardUtils
              .getListOfLegalities(Collections.singletonList(card))
              .contains(format.toString());
    }).collect(Collectors.toList());
  }
}
