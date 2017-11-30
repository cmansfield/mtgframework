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
    final Map<CardConstants, Pair<Supplier,Function>> getterMethodMap = generateFilterMap(filter);
    List<Card> filteredCards = new ArrayList<>();

    cards.forEach(c -> {
      if (getterMethodMap.entrySet().stream()
              .allMatch(e -> {
                boolean isMatch;
                if(e.getValue().getKey().get() instanceof List) {
                  isMatch = allMatchList(e.getValue().getKey().get(), e.getValue().getValue().apply(c));
                  return isNot != isMatch;
                }

                // We don't want filters on Text to be a literal match
                // but more of a 'contains' match
                if(e.getKey().toString().equalsIgnoreCase(CardConstants.TEXT.toString())) {

                  String cardText = (String)e.getValue().getValue().apply(c);

                  if(cardText == null) {
                    return isNot;
                  }

                  isMatch = cardText.contains((String)e.getValue().getKey().get());
                  return isNot != isMatch;
                }

                // This pulls the Supplier from the entry's toString, and the Function from the
                // entry's value, and then compares the values of the two
                isMatch = e.getValue().getKey().get().equals(e.getValue().getValue().apply(c));
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
  private static Map<CardConstants, Pair<Supplier,Function>> generateFilterMap(Card filter) {
    final Map<CardConstants, Pair<Supplier,Function>> getterMethodMap = new EnumMap<CardConstants, Pair<Supplier,Function>>(CardConstants.class);

    if(filter.getLayout() != null) getterMethodMap.put(CardConstants.LAYOUT, new Pair<Supplier,Function>(filter::getLayout, c -> ((Card)c).getLayout()));
    if(filter.getName() != null) getterMethodMap.put(CardConstants.NAME, new Pair<Supplier,Function>(filter::getName, c -> ((Card)c).getName()));
    if(filter.getNames() != null) getterMethodMap.put(CardConstants.NAMES, new Pair<Supplier,Function>(filter::getNames, c -> ((Card)c).getNames()));
    if(filter.getManaCost() != null) getterMethodMap.put(CardConstants.MANA_COST, new Pair<Supplier,Function>(filter::getManaCost, c -> ((Card)c).getManaCost()));
    if(filter.getCmc() != null) getterMethodMap.put(CardConstants.CMC, new Pair<Supplier,Function>(filter::getCmc, c -> ((Card)c).getCmc()));
    if(filter.getColors() != null) getterMethodMap.put(CardConstants.COLORS, new Pair<Supplier,Function>(filter::getColors, c -> ((Card)c).getColors()));
    if(filter.getType() != null) getterMethodMap.put(CardConstants.TYPE, new Pair<Supplier,Function>(filter::getType, c -> ((Card)c).getType()));
    if(filter.getSuperTypes() != null) getterMethodMap.put(CardConstants.SUPER_TYPES, new Pair<Supplier,Function>(filter::getSuperTypes, c -> ((Card)c).getSuperTypes()));
    if(filter.getTypes() != null) getterMethodMap.put(CardConstants.TYPES, new Pair<Supplier,Function>(filter::getTypes, c -> ((Card)c).getTypes()));
    if(filter.getSubTypes() != null) getterMethodMap.put(CardConstants.SUB_TYPES, new Pair<Supplier,Function>(filter::getSubTypes, c -> ((Card)c).getSubTypes()));
    if(filter.getText() != null) getterMethodMap.put(CardConstants.TEXT, new Pair<Supplier,Function>(filter::getText, c -> ((Card)c).getText()));
    if(filter.getPower() != null) getterMethodMap.put(CardConstants.POWER, new Pair<Supplier,Function>(filter::getPower, c -> ((Card)c).getPower()));
    if(filter.getToughness() != null) getterMethodMap.put(CardConstants.TOUGHNESS, new Pair<Supplier,Function>(filter::getToughness, c -> ((Card)c).getToughness()));
    if(filter.getLoyalty() != null) getterMethodMap.put(CardConstants.LOYALTY, new Pair<Supplier,Function>(filter::getLoyalty, c -> ((Card)c).getLoyalty()));
    if(filter.getImageName() != null) getterMethodMap.put(CardConstants.IMAGE_NAME, new Pair<Supplier,Function>(filter::getImageName, c -> ((Card)c).getImageName()));
    if(filter.getRulings() != null) getterMethodMap.put(CardConstants.RULINGS, new Pair<Supplier,Function>(filter::getRulings, c -> ((Card)c).getRulings()));
    if(filter.getHand() != null) getterMethodMap.put(CardConstants.HAND, new Pair<Supplier,Function>(filter::getHand, c -> ((Card)c).getHand()));
    if(filter.getLife() != null) getterMethodMap.put(CardConstants.LIFE, new Pair<Supplier,Function>(filter::getLife, c -> ((Card)c).getLife()));
    if(filter.getStarter() != null) getterMethodMap.put(CardConstants.STARTER, new Pair<Supplier,Function>(filter::getStarter, c -> ((Card)c).getStarter()));
    if(filter.getPrintings() != null) getterMethodMap.put(CardConstants.PRINTINGS, new Pair<Supplier,Function>(filter::getPrintings, c -> ((Card)c).getPrintings()));
    if(filter.getSource() != null) getterMethodMap.put(CardConstants.SOURCE, new Pair<Supplier,Function>(filter::getSource, c -> ((Card)c).getSource()));
    if(filter.getLegalities() != null) getterMethodMap.put(CardConstants.LEGALITIES, new Pair<Supplier,Function>(filter::getLegalities, c -> ((Card)c).getLegalities()));
    if(filter.getColorIdentity() != null) getterMethodMap.put(CardConstants.COLOR_IDENTITY, new Pair<Supplier,Function>(filter::getColorIdentity, c -> ((Card)c).getColorIdentity()));

    return getterMethodMap;
  }
}
