package io.github.cmansfield.validator;

import io.github.cmansfield.deck.DeckUtils;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.io.LoadCards;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;


public final class DeckValidator {

  private DeckValidator() {}

  /**
   * Returns true is the deck is format compliant
   *
   * @param deck  - The deck to check
   */
  public static void isFormatCompliant(Deck deck) {
    if(deck.getFormat() != Format.COMMANDER) {
      throw new UnsupportedOperationException("This feature currently only checks to see if decks are commander legal");
    }

    final int MAX_COMMANDER_COUNT = 1;
    final int MAX_PARTNER_COUNT = 2;
    List<Card> commanders = deck.getFeaturedCards();

    if(commanders == null) {
      throw new IllegalStateException("Commander decks must have a commander");
    }

    checkLegalCardCounts(deck);

    // This should return all cards that are not a commander card
    List<Card> nonCommanderCards = deck.generateFullDeckList();

    if(nonCommanderCards.size() + commanders.size() != Format.COMMANDER.getMaxDeckSize()) {
      throw new IllegalStateException(String.format("Commander decks can only contain %d cards", Format.COMMANDER.getMaxDeckSize()));
    }

    List<Card> filteredCommanders = CardFilter.filter(
            commanders,
            Format.COMMANDER.getFeatureCardFilter());
    if(filteredCommanders.size() == MAX_PARTNER_COUNT) {
      if(!filteredCommanders.stream().allMatch(card -> {
        return card.getText().toLowerCase().contains("partner");
      })) {
        throw new IllegalStateException("If there are two commanders then they both must have partner");
      }
    }
    if(filteredCommanders.size() != MAX_COMMANDER_COUNT && filteredCommanders.size() != MAX_PARTNER_COUNT) {
      throw new IllegalStateException("Deck commanders must be of Legendary type");
    }

    List<String> commanderColors = commanders.get(0).getColors();
    deck.getDeckColors().forEach(color -> {
      if(!commanderColors.contains(color.toString())) {
        throw new IllegalStateException("Commander decks must only include cards with colors that match their commander");
      }
    });

    List<Card> nonCompliantCards = getNonCompliantCards(deck);
    if(!nonCompliantCards.isEmpty()) {
      throw new IllegalStateException(
              String.format(
                      "There are cards that are not %s legal%n%s",
                      deck.getFormat().toString(),
                      nonCompliantCards.toString() ));
    }
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

  /**
   * Checks to make sure there are the correct number of copies of each
   * card within the deck for the deck's format
   *
   * @param deck - Deck to validate card counts
   */
  private static void checkLegalCardCounts(Deck deck) {

    deck.getCards().forEach(card -> {
      if(card.getTypes() != null && card.getType().contains("Basic Land")) {
        return;
      }
      if(deck.getQuantity(card.getName()) > deck.getFormat().getMaxCopiesOfCard()) {
        throw new IllegalStateException(String.format("There are too many copies of card: %s", card.getName()));
      }
    });
  }
}
