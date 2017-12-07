package io.github.cmansfield.filters;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;


public class CardFilter {
  /**
   * Returns a list of cards that do not match anything in the filters supplied
   *
   * @param cards   - List of cards to filters down
   * @param filters - A list of Cards that will be used as the filters criteria
   * @return        - A list of cards that met the filters criteria
   */
  public static List<Card> filterNot(List<Card> cards, List<Card> filters) {
    List<Card> allFilteredNotCards = new ArrayList<>();

    filters.forEach(filter -> {
      allFilteredNotCards.addAll(CardFilter.filter(cards, filter));
    });

    return allFilteredNotCards;
  }

  /**
   * Returns a list of cards that do not match anything in the filters supplied
   *
   * @param cards   - List of cards to filters down
   * @param filter  - A Card that will be used as the filters criteria
   * @return        - A List of cards that met the filters criteria
   */
  public static List<Card> filterNot(List<Card> cards, Card filter) {
    return filterTemplateMethod(cards, filter, true);
  }

  /**
   * Returns a list of cards that meet the filters criteria
   *
   * @param cards   - List of cards to filters down
   * @param filters - A list of Cards that will be used as the filters criteria
   * @return        - A list of cards that met the filters criteria
   */
  public static List<Card> filter(List<Card> cards, List<Card> filters) {
    List<Card> allFilteredCards = new ArrayList<>();

    filters.forEach(filter -> {
      allFilteredCards.addAll(CardFilter.filter(cards, filter));
    });

    return allFilteredCards;
  }

  /**
   * Returns a list of cards that meet the filters criteria
   *
   * @param cards   - List of cards to filters down
   * @param filter  - A Card that will be used as the filters criteria
   * @return        - A List of cards that met the filters criteria
   */
  public static List<Card> filter(List<Card> cards, Card filter) {
    return filterTemplateMethod(cards, filter, false);
  }

  /**
   * This method does most of the filtering. With the isNot boolean the filters can be
   * set to find only cards that match the filters or if set to true, remove cards that
   * match what is in the filters.
   *
   * @param cards   - List of cards to filters down
   * @param filter  - A list of Cards that will be used as the filters criteria
   * @param isNot   - A boolean to filters for matching or non-matching cards
   * @return        - A List of cards that met the filters criteria
   */
  private static List<Card> filterTemplateMethod(List<Card> cards, Card filter, boolean isNot) {
    final Map<Supplier,Function<Card,Object>> getterMethodMap = generateFilterMap(filter);
    List<Card> filteredCards = new ArrayList<>();

    cards.forEach(c -> {
      if (getterMethodMap.entrySet().stream()
              .allMatch(e -> {
                boolean isMatch;
                Object filterField = e.getKey().get();
                Object cardField = e.getValue().apply(c);

                if(cardField == null || filterField == null) {
                  return isNot;
                }

                if(filterField instanceof List) {
                  isMatch = allMatchList(filterField, cardField);
                  return isNot != isMatch;
                }

                if(filterField instanceof String) {
                  String pattern = "[^a-zA-Z0-9]";
                  String filterStr = ((String)filterField).replaceAll(pattern, "");
                  String cardStr = ((String)cardField).replaceAll(pattern, "");

                  isMatch = cardStr.toLowerCase().contains(filterStr.toLowerCase());

                  return isNot != isMatch;
                }

                isMatch = filterField.equals(cardField);
                return isNot != isMatch;
              })
              ) {
        filteredCards.add(c);
      }
    });

    return filteredCards;
  }

  /**
   * Checks to make sure the second param list meets the criteria in
   * the first list
   *
   * @param lhs - List to be used to see if the second list contains
   *            the elements of this list
   * @param rhs - The list of the card to check
   * @return    - Returns true if the second list contains the elements
   *            of the first list
   */
  private static boolean allMatchList(Object lhs, Object rhs) {

    if(lhs == null || rhs == null) {
      return false;
    }

    if(!(lhs instanceof List)) {
      throw new IllegalArgumentException("Expecting List Object");
    }
    if(!(rhs instanceof List)) {
      throw new IllegalArgumentException("Expecting List Object");
    }

    List listFilter = (List)lhs;
    List listCard = (List)rhs;

    return listFilter.stream().allMatch(item -> {
      if(item instanceof Map) {
        if(listCard.contains(item)) {
          return allMatchMap(item, listCard.get(listCard.indexOf(item)));
        }
      }

      if(item instanceof String) {
        return listCard.stream().anyMatch(field -> {
          return ((String)field).contains((String)item);
        });
      }

      return listCard.contains(item);
    });
  }

  /**
   * Checks to make sure the second param map meets the criteria in
   * the first map
   *
   * @param lhs - List to be used to see if the second map contains
   *            the elements of this map
   * @param rhs - The map of the card to check
   * @return    - Returns true if the second map contains the elements
   *            of the first map
   */
  private static boolean allMatchMap(Object lhs, Object rhs) {

    if(lhs == null || rhs == null) {
      return false;
    }

    if(!(lhs instanceof Map)) {
      throw new IllegalArgumentException("Expecting Map Object");
    }
    if(!(rhs instanceof Map)) {
      throw new IllegalArgumentException("Expecting Map Object");
    }

    Map<Object, Object> mapFilter = (Map)lhs;
    Map<Object, Object> mapCard = (Map)rhs;

    return mapFilter.entrySet().stream().allMatch(e -> {
      if(e.getValue() instanceof List) {
        if(((List)mapCard.get(e.getKey())).contains(e.getValue())) {
          return allMatchList(e.getValue(), (List)mapCard.get(e.getKey()));
        }
      }

      return mapCard.get(e.getKey()).equals(e.getValue());
    });
  }

  /**
   * Returns a Map of all of the function references that were found in the
   * filters object
   *
   * @param filter  - Card object that contains the filters criteria
   * @return        - Returns a map of functions that were found in the filters object
   */
  private static Map<Supplier,Function<Card,Object>> generateFilterMap(Card filter) {
    final Map<Supplier,Function<Card,Object>> getterMethodMap = new HashMap<>();

    if(filter.getLayout() != null) getterMethodMap.put(filter::getLayout, Card::getLayout);
    if(filter.getName() != null) getterMethodMap.put(filter::getName, Card::getName);
    if(filter.getNames() != null) getterMethodMap.put(filter::getNames, Card::getNames);
    if(filter.getManaCost() != null) getterMethodMap.put(filter::getManaCost, Card::getManaCost);
    if(filter.getCmc() != null) getterMethodMap.put(filter::getCmc, Card::getCmc);
    if(filter.getColors() != null) getterMethodMap.put(filter::getColors, Card::getColors);
    if(filter.getType() != null) getterMethodMap.put(filter::getType, Card::getType);
    if(filter.getSupertypes() != null) getterMethodMap.put(filter::getSupertypes, Card::getSupertypes);
    if(filter.getTypes() != null) getterMethodMap.put(filter::getTypes, Card::getTypes);
    if(filter.getSubtypes() != null) getterMethodMap.put(filter::getSubtypes, Card::getSubtypes);
    if(filter.getText() != null) getterMethodMap.put(filter::getText, Card::getText);
    if(filter.getPower() != null) getterMethodMap.put(filter::getPower, Card::getPower);
    if(filter.getToughness() != null) getterMethodMap.put(filter::getToughness, Card::getToughness);
    if(filter.getLoyalty() != null) getterMethodMap.put(filter::getLoyalty, Card::getLoyalty);
    if(filter.getImageName() != null) getterMethodMap.put(filter::getImageName, Card::getImageName);
    if(filter.getRulings() != null) getterMethodMap.put(filter::getRulings, Card::getRulings);
    if(filter.getHand() != null) getterMethodMap.put(filter::getHand, Card::getHand);
    if(filter.getLife() != null) getterMethodMap.put(filter::getLife, Card::getLife);
    if(filter.getStarter() != null) getterMethodMap.put(filter::getStarter, Card::getStarter);
    if(filter.getPrintings() != null) getterMethodMap.put(filter::getPrintings, Card::getPrintings);
    if(filter.getSource() != null) getterMethodMap.put(filter::getSource, Card::getSource);
    if(filter.getLegalities() != null) getterMethodMap.put(filter::getLegalities, Card::getLegalities);
    if(filter.getColorIdentity() != null) getterMethodMap.put(filter::getColorIdentity, Card::getColorIdentity);

    return getterMethodMap;
  }
}
