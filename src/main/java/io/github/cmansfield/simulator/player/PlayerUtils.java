package io.github.cmansfield.simulator.player;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.constants.CardState;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class PlayerUtils {

  public static List<PlayerCard> getUntappedMana(Player player) {
    // Get the land from the battlefield
    List<PlayerCard> availableMana = CardFilter.filter(
            (List)player.getZone(Zone.BATTLEFIELD),
            new Card.CardBuilder()
                    .types(Collections.singletonList("Land"))
                    .build()
    );

    // Remove tapped mana
    return availableMana.stream().filter(card -> {
      return card.getCardState() == CardState.UNTAPPED;
    }).collect(Collectors.toList());
  }
}
