package io.github.cmansfield.filters;

import io.github.cmansfield.simulator.player.PlayerCard;

import java.util.List;
import java.util.stream.Collectors;


public class PlayerCardFilter extends CardFilter {

  public static List<PlayerCard> filter(List<PlayerCard> cards, PlayerCard filter) {
    return cards.stream().filter(card -> {
      if(filter.getOwner() != null && card.getOwner() != filter.getOwner()) {
        return false;
      }
      return !(filter.getController() != null && card.getController() != filter.getController());
    }).collect(Collectors.toList());
  }
}
