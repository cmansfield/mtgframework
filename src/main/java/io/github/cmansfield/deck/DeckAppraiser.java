package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.filter.CardFilter;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;

import java.util.Collections;
import java.util.List;


public final class DeckAppraiser {
  private DeckAppraiser() {}

  /**
   * Simple hand draw appraiser for a deck, will add more heuristic
   * logic as time goes on. This will return a float with the percentage
   * of good hand draws for the given deck.
   *
   * @param deck  - Deck to appraise
   * @return      - Percentage of good hand draws from the given deck
   */
  public static float appraiseHandDraw(Deck deck) {
    Card filter = new Card.CardBuilder().types(Collections.singletonList("Land")).build();
    Player player = new Player(deck);
    int maxDraws = 100;
    int goodHand = 0;

    for(int i = 0; i < maxDraws; ++i) {
      player.shuffle(Zone.LIBRARY);
      player.draw(7);
      List playerHand = player.getZone(Zone.HAND);
      List<Card> lands = CardFilter.filter(playerHand, filter);
      if(lands.size() > 2 && lands.size() < 6) {
        ++goodHand;
      }
      player.resetZones();
    }

    return (float)goodHand / (float)maxDraws;
  }
}
