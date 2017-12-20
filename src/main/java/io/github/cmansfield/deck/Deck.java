package io.github.cmansfield.deck;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.card.Card;

import java.util.stream.Collectors;
import java.util.*;


public final class Deck {
  private Map<String,Integer> quantity;
  private List<Color> deckColors;
  private List<Card> featuredCards;
  private List<Card> cards;
  private Format format;

  public Deck() {}

  public Deck(Deck deck) {
    this.quantity = new HashMap<>(deck.quantity);
    this.deckColors = new ArrayList<>(deck.deckColors);
    this.featuredCards = new ArrayList<>(deck.featuredCards);
    this.cards = new ArrayList<>(deck.cards);
    this.format = deck.format;
  }

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
    this.format = format;

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

  public List<Color> getDeckColors() {
    return new ArrayList<>(this.deckColors);
  }

  public List<Card> getFeaturedCards() {
    return this.featuredCards;
  }

  public Format getFormat() {
    return this.format;
  }

  public Map<String,Integer> getQuantity() {
    return this.quantity;
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

  public void setFeaturedCards(List<Card> cards) {
    this.featuredCards = cards;
    removeFeaturedCards();
  }

  public void setFormat(Format format) {
    this.format = format;
  }

  public void addFeaturedCard(Card card) {
    if(this.featuredCards == null) {
      this.featuredCards = new ArrayList<>();
    }
    this.featuredCards.add(card);
    removeFeaturedCards();
  }

  /**
   * This method removes featured cards from the deck's list of cards
   */
  private void removeFeaturedCards() {
    if(this.cards == null || this.featuredCards == null) {
      return;
    }

    this.featuredCards.forEach(card -> {
      this.quantity.remove(card.getName());
      this.cards.removeIf(c ->
        c.getName().equals(card.getName())
      );
    });
  }

  /**
   * This method generates a full list of cards with duplicate cards in the list
   *
   * @return  - A list of all cards in the deck
   */
  public List<Card> generateFullDeckList() {
    List<Card> cardList = new ArrayList<>();

    this.cards.forEach(card -> {
      for(int i = 0; i < getQuantity(card.getName()); ++i) {
        cardList.add(card);
      }
    });

    return cardList;
  }

  @Override
  public String toString() {
    return String.format("%s%s%s",
            this.format == null ? "" : String.format("Format: %s%n", this.format.toString()),
            this.featuredCards == null ? "" : String.format(
                    "Featured Cards: %s%n",
                    this.featuredCards.stream()
                            .map(Card::getName)
                            .collect(Collectors.joining("; "))),
            this.cards.stream()
            .map(card -> String.format("%dx %s", this.quantity.get(card.getName()), card.getName()))
            .collect(Collectors.joining("\r\n")));
  }
}


