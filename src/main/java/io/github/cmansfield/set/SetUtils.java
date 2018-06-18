package io.github.cmansfield.set;

import io.github.cmansfield.set.constants.SetCardConstants;
import org.apache.commons.collections4.CollectionUtils;
import io.github.cmansfield.set.constants.Rarity;
import io.github.cmansfield.io.CardSetReader;
import io.github.cmansfield.card.Card;

import java.util.stream.Collectors;
import java.util.AbstractMap;
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
            .map(SetUtils::getLowestRarity)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  /**
   * This will find the lowest rarity for the card supplied
   * 
   * @param card    The card to find its lowest rarity
   * @return        An entry value with the card as the key and its rarity as the value
   */
  public static Map.Entry<Card, Rarity> getLowestRarity(Card card) {
    return new AbstractMap.SimpleEntry<>(card, 
            getSetCardProperty(card, SetCardConstants.RARITY.toString()).stream()
                    .map(Rarity::find)
                    .reduce(Rarity.OTHER, (a, b) -> a.getValue() > b.getValue() ? b : a));
  }
  
  /**
   * This method will search all of the sets the supplied card was printed in and return
   * the lowest rarity found for that card
   *
   * @param card    A card to search for its lowest found rarity
   * @return        A Rarity enum object
   */
  private static List<String> getSetCardProperty(Card card, String key) {
    List<String> values = card.getPrintings().stream()
            .map(CardSetReader::lookUpSet)
            .map(MtgSet::getCards)
            .map(cardList -> cardList.stream()
                    .filter(setCard ->
                                    setCard.containsKey(SetCardConstants.NAME.toString())
                                    && card.getName().equals(setCard.get(SetCardConstants.NAME.toString())))
                    .findFirst()
                    .orElse(null))
            .filter(Objects::nonNull)
            .filter(setCard -> setCard.containsKey(key))
            .map(setCard -> setCard.get(key))
            .map(Object::toString)
            .distinct()
            .collect(Collectors.toList());
    if(CollectionUtils.isEmpty(values)) {
      throw new IllegalStateException(String.format(
              "Could not find the property '%s' for card '%s'",
              key,
              card.getName()));
    }

    return values;
  }

  /**
   * This method will map each card to one of their print image multiverse IDs
   * 
   * @param cards   Card to look up
   * @return        A map of cards mapped to their multiverseId
   */
  public static Map<Card, String> getMultiVerseId(List<Card> cards) {
    return cards.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(card -> card, 
                    card -> getSetCardProperty(card, SetCardConstants.MULTIVERSE_ID.toString()).stream()
                      .findFirst()
                      .orElse("")));
  }
}
