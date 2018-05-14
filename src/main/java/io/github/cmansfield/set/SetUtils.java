package io.github.cmansfield.set;

import org.apache.commons.collections4.CollectionUtils;
import io.github.cmansfield.set.constants.Rarity;
import io.github.cmansfield.io.CardSetAdapter;
import io.github.cmansfield.card.Card;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;
import java.util.Map;


public class SetUtils {
  private SetUtils() {}

  /**
   * This method will map the supplied list of cards to the lowest found rarity for each
   * card
   *
   * @param cards   List of cards to map to their lowest found rarity
   * @return        A map of cards mapped to their lowest found rarity
   */
  public static Map<Card, Rarity> getLowestRarity(List<Card> cards) {
    return cards.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(card -> card, SetUtils::getLowestRarity));
  }

  /**
   * This method will search all of the sets the supplied card was printed in and return
   * the lowest rarity found for that card
   *
   * @param card    A card to search for its lowest found rarity
   * @return        A Rarity enum object
   */
  private static Rarity getLowestRarity(Card card) {
    List<Rarity> rarities = card.getPrintings().stream()
            .map(CardSetAdapter::lookUpSet)
            .map(MtgSet::getCards)
            .map(cardList -> cardList.stream()
                    .filter(setCard ->
                                    setCard.containsKey("name")
                                    && card.getName().equals(setCard.get("name")))
                    .findFirst()
                    .orElse(null))
            .filter(Objects::nonNull)
            .filter(setCard -> setCard.containsKey("rarity"))
            .map(setCard -> setCard.get("rarity"))
            .map(value -> (String)value)
            .distinct()
            .map(Rarity::find)
            .collect(Collectors.toList());
    if(CollectionUtils.isEmpty(rarities)) {
      throw new IllegalStateException(String.format(
              "Could not find the rarity for card '%s'",
              card.getName()));
    }

    return rarities.stream()
            .reduce(Rarity.OTHER, (a, b) -> a.getValue() > b.getValue() ? b : a);
  }
}
