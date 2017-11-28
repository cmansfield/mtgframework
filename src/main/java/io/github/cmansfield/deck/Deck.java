package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.Color;
import io.github.cmansfield.deck.constants.Format;

import java.util.*;
import java.util.stream.Collectors;


public final class Deck {
  private Map<String,Integer> quantity;
  private List<Card> originalCards;
  private List<Color> deckColors;
  private Card featuredCard;
  private List<Card> cards;
  private Format format;

  public Deck(List<Card> cards) {
    this(cards, null);
  }

  /**
   * Constructor that will remove duplicates from the card list
   * and keep track of the number of copies of a card in the
   * quantity map
   *
   * @param cards   - List of cards to create the deck with
   * @param format  - The type of game format this deck is meant for
   */
  public Deck(List<Card> cards, Format format) {
    this.deckColors = new ArrayList<>();
    this.quantity = new HashMap<>();
    this.originalCards = new ArrayList<>(cards);

    this.cards = cards.stream().filter(card -> {
      String key = card.getName();
      if(this.quantity.containsKey(key)) {
        this.quantity.put(key, this.quantity.get(key) + 1);
        return false;
      }
      else {
        this.quantity.put(key, 1);
        if(card.getColors() == null) {
          return true;
        }

        card.getColors().forEach(color -> {
          Color colorEnum = Color.find(color);
          if(!this.deckColors.contains(colorEnum)) {
            this.deckColors.add(colorEnum);
          }
        });

        return true;
      }
    }).collect(Collectors.toList());
  }

  public List<Card> getCards() {
    return new ArrayList<>(this.cards);
  }

  public List<Card> getOriginalCards() {
    return new ArrayList<>(this.originalCards);
  }

  public List<Color> getDeckColors() {
    return new ArrayList<>(this.deckColors);
  }

  public Card getFeaturedCard() {
    return this.featuredCard;
  }

  public Format getFormat() {
    return this.format;
  }

  public void setFeaturedCard(Card card) {
    this.featuredCard = card;
  }

  public void setFormat(Format format) {
    this.format = format;
  }

  /**
   * Returns the number of copies of a card stored in the deck
   *
   * @param key - Card name to get quantity for
   * @return    - The number of copies of a card
   */
  public Integer getQuantity(final String key) {
    if(!this.quantity.containsKey(key)) {
      return 0;
    }

    return this.quantity.get(key);
  }

  @Override
  public String toString() {
    return this.cards.stream()
            .map(card -> String.format("%dx %s", this.quantity.get(card.getName()), card.getName()))
            .collect(Collectors.joining("\r\n"));
  }
}


