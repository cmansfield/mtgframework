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
    if(deck.getFormat() != Format.COMMANDER) {
      throw new UnsupportedOperationException("This feature currently only checks to see if decks are commander legal");
    }

    checkLegalCardCounts(deck);
    Format deckFormat = deck.getFormat();

    switch (deckFormat) {
      case SCARS_OF_MIRRODIN_BLOCK:
      case ICE_AGE_BLOCK:
      case KHANS_OF_TARKIR_BLOCK:
      case LORWYN_SHADOWMOOR_BLOCK:
      case ODYSSEY_BLOCK:
      case KALADESH_BLOCK:
      case MASQUES_BLOCK:
      case SHADOWS_OVER_INNISTRAD_BLOCK:
      case IXALAN_BLOCK:
      case INNISTRAD_BLOCK:
      case INVASION_BLOCK:
      case THEROS_BLOCK:
      case SHARDS_OF_ALARA_BLOCK:
      case KAMIGAWA_BLOCK:
      case TEMPEST_BLOCK:
      case RETURN_TO_RAVNICA_BLOCK:
      case URZA_BLOCK:
      case MIRAGE_BLOCK:
      case BATTLE_FOR_ZENDIKAR_BLOCK:
      case TIME_SPIRAL_BLOCK:
      case MIRRODIN_BLOCK:
      case ZENDIKAR_BLOCK:
      case AMONKHET_BLOCK:
      case ONSLAUGHT_BLOCK:
      case RAVNICA_BLOCK:
        break;
      case COMMANDER:
        isCommanderCompliant(deck);
        break;
      case LEGACY:
        break;
      case EXTENDED:
        break;
      case STANDARD:
        break;
      case UN_SETS:
        break;
      case MODERN:
        break;
      case VINTAGE:
        break;
      case CLASSIC:
        break;
    }

    int deckCount = deck.generateFullDeckList().size() + deck.getFeaturedCards().size();
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

    deck.getCards().forEach(card -> {
      if(card.getTypes() != null && card.getType().contains("Basic Land")) {
        return;
      }
      if(deck.getQuantity(card.getName()) > deck.getFormat().getMaxCopiesOfCard()) {
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
    final int MAX_COMMANDER_COUNT = 1;
    final int MAX_PARTNER_COUNT = 2;
    List<Card> commanders = deck.getFeaturedCards();

    if(commanders == null) {
      throw new IllegalStateException("Commander decks must have a commander");
    }

    List<Card> filteredCommanders = CardFilter.filter(
            commanders,
            Format.COMMANDER.getFeatureCardFilter());
    if(filteredCommanders.size() == MAX_PARTNER_COUNT) {
      if(!filteredCommanders.stream().allMatch(card ->
        card.getText().toLowerCase().contains("partner")
      )) {
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
  }
}
