package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.simulator.constants.Zone;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class Player {
  Map<Zone,List<Card>> zones;
  Deck deck;

  Player(Deck deck) {
    this.deck = deck;

    zones = new EnumMap<Zone, List<Card>>(Zone.class);
    zones.put(Zone.LIBRARY, this.deck.getOriginalCards());
    zones.put(Zone.HAND, new ArrayList<>());
    zones.put(Zone.BATTLEFIELD, new ArrayList<>());
    zones.put(Zone.GRAVEYARD, new ArrayList<>());
    zones.put(Zone.EXILE, new ArrayList<>());
    zones.put(Zone.COMMAND, new ArrayList<>());
    zones.put(Zone.ANTE, new ArrayList<>());
    zones.put(Zone.SCRAPYARD, new ArrayList<>());
  }
}
