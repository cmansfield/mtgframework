package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.simulator.constants.Zone;

import java.util.*;


public class Player {
  private Map<Zone,List<Card>> zones;
  private Deck deck;

  Player(Deck deck) {
    this.deck = deck;

    this.zones = new EnumMap<Zone, List<Card>>(Zone.class);
    this.zones.put(Zone.LIBRARY, this.deck.getOriginalCards());
    this.zones.put(Zone.HAND, new ArrayList<>());
    this.zones.put(Zone.BATTLEFIELD, new ArrayList<>());
    this.zones.put(Zone.GRAVEYARD, new ArrayList<>());
    this.zones.put(Zone.EXILE, new ArrayList<>());
    this.zones.put(Zone.COMMAND, new ArrayList<>());
    this.zones.put(Zone.ANTE, new ArrayList<>());
    this.zones.put(Zone.SCRAPYARD, new ArrayList<>());
  }

  public List<Card> getZone(Zone zone) {
    if(!this.zones.containsKey(zone)) {
      throw new IllegalArgumentException(String.format("Player dones not contain zone '%s'", zone.toString()));
    }

    return this.zones.get(zone);
  }

  public void shuffle(Zone zone) {
    Collections.shuffle(this.zones.get(zone));
  }

  public void draw(int drawAmount) {
    List<Card> hand = this.zones.get(Zone.HAND);
    List<Card> library = this.zones.get(Zone.LIBRARY);

    for(int x = 0; x < drawAmount; ++x) {
      if(library.isEmpty()) {
        throw new IllegalStateException("No more cards to draw from library");
      }

      hand.add(library.get(0));
      library.remove(0);
    }
  }
}
