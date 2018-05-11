package io.github.cmansfield.validator;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;

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
    Format deckFormat = deck.getFormat();

    if(deckFormat == null) {
      throw new NullPointerException("This deck has no format set");
    }

    checkLegalCardCounts(deck);

    if(deckFormat == Format.COMMANDER) {
      isCommanderCompliant(deck);
    }
    else {
      throw new UnsupportedOperationException("Only commander/EDH decks can be validated at this time");
    }

    int deckCount = deck.generateFullDeckList().size()
            + (deck.getFeaturedCards() == null ? 0 : deck.getFeaturedCards().size());
    int minCount = deckFormat.getMinDeckSize();
    int maxCount = deckFormat.getMaxDeckSize();
    if(deckCount > maxCount || deckCount < minCount) {
      String message = minCount == maxCount ?
              String.format(
                "%s decks must be %d cards, found %d",
                deckFormat.toString(),
                maxCount,
                deckCount)
              : String.format(
                "%s decks must be between %d to %d cards, found %d",
                deckFormat.toString(),
                minCount,
                maxCount,
                deckCount);
      throw new IllegalStateException(message);
    }

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
    return cards.stream().filter(card ->
            !CardUtils
              .getListOfLegalities(Collections.singletonList(card))
              .contains(format.toString())
    ).collect(Collectors.toList());
  }

  /**
   * Checks to make sure there are the correct number of copies of each
   * card within the deck for the deck's format
   *
   * @param deck - Deck to validate card counts
   */
  private static void checkLegalCardCounts(Deck deck) {
    final int maxCopies = deck.getFormat().getMaxCopiesOfCard();

    deck.getCards().forEach(card -> {
      if(card.getTypes() != null && card.getType().contains("Basic Land")) {
        return;
      }
      if(deck.getQuantity(card.getName()) > maxCopies) {
        throw new IllegalStateException(String.format("There are too many copies of card: %s", card.getName()));
      }
    });
  }

  /**
   * Checks to make sure the deck is Commander compliant
   *
   * @param deck - Deck to validate
   */
  private static void isCommanderCompliant(Deck deck) {
    final int maxCommanderCount = 1;
    final int maxPartnerCount = 2;
    List<Card> commanders = deck.getFeaturedCards();

    if(commanders == null) {
      throw new IllegalStateException("Commander decks must have a commander");
    }

    List<Card> filteredCommanders = CardFilter.filter(
            commanders,
            Format.COMMANDER.getFeatureCardFilter());
    if(filteredCommanders.size() == maxPartnerCount) {
      if(!filteredCommanders.stream().allMatch(card ->      // NOSONAR
        card.getText().toLowerCase().contains("partner")
      )) {
        throw new IllegalStateException("If there are two commanders then they both must have partner");
      }
    }
    if(filteredCommanders.size() != maxCommanderCount && filteredCommanders.size() != maxPartnerCount) {
      throw new IllegalStateException("Deck commanders must be of Legendary type");
    }

    List<String> commanderColors = commanders.get(0).getColors();
    deck.getDeckColors().forEach(color -> {
      if(!commanderColors.contains(color.toString())) {
        throw new IllegalStateException("Commander decks must only include cards with colors that match their commander");
      }
    });
  }
}
