package io.github.cmansfield.filters;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.simulator.player.PlayerCard;

import java.util.List;
import java.util.stream.Collectors;


public class PlayerCardFilter extends CardFilter {

  /**
   * This adds filtering for PlayerCards
   *
   * @param cards   - List of PlayerCards to be filtered
   * @param filter  - A PlayerCard object to be used as the filter criteria
   * @return        - A list of filtered PlayerCards
   */
  public static List<PlayerCard> filter(List<PlayerCard> cards, PlayerCard filter) {
    List<PlayerCard> playerCards = cards.stream().filter(card -> {
      if(filter.getOwnerName() != null && !card.getOwnerName().equalsIgnoreCase(filter.getOwnerName())) {
        return false;
      }
      if(filter.getControllerName() != null && !card.getControllerName().equalsIgnoreCase(filter.getControllerName())) {
        return false;
      }

      return filter.getCardState() == null || card.getCardState() == filter.getCardState();
    }).collect(Collectors.toList());

    return filter((List)playerCards, (Card)filter);
  }
}
