package io.github.cmansfield.card.filter;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;


public class CardFilter {
  private String layout;
  private String name;
  private List<String> names;
  private String manaCost;
  private Double cmc;
  private List<String> colors;
  private String type;
  private List<String> superTypes;
  private List<String> types;
  private List<String> subTypes;
  private String text;
  private String power;
  private String toughness;
  private Integer loyalty;
  private String imageName;
  private List<Map<String,String>> rulings;
  private Integer hand;
  private Integer life;
  private Boolean starter;
  private List<String> printings;
  private String source;
  private List<Map<String,String>> legalities;
  private List<String> colorIdentity;
  private Card card;


  public static class CardBuilder {
    private String _layout;
    private String _name;
    private List<String> _names;
    private String _manaCost;
    private Double _cmc;
    private List<String> _colors;
    private String _type;
    private List<String> _superTypes;
    private List<String> _types;
    private List<String> _subTypes;
    private String _text;
    private String _power;
    private String _toughness;
    private Integer _loyalty;
    private String _imageName;
    private List<Map<String,String>> _rulings;
    private Integer _hand;
    private Integer _life;
    private Boolean _starter;
    private List<String> _printings;
    private String _source;
    private List<Map<String,String>> _legalities;
    private List<String> _colorIdentity;
    private Card _card;

    public CardBuilder() {}
    public CardBuilder(LinkedHashMap cardPojo) {
      this._card = new Card(cardPojo);
    }

    public CardBuilder name(String key) {
      this._name = key;
      return this;
    }

    public CardBuilder layout(String layout) {
      this._layout = layout;
      return this;
    }

    public CardBuilder manaCost(String manaCost) {
      this._manaCost = manaCost;
      return this;
    }

    public CardBuilder cmc(Double cmc) {
      this._cmc = cmc;
      return this;
    }

    public CardBuilder colors(List<String> colors) {
      this._colors = colors;
      return this;
    }

    public CardBuilder type(String type) {
      this._type = type;
      return this;
    }

    public CardBuilder superTypes(List<String> superTypes) {
      this._superTypes = superTypes;
      return this;
    }

    public CardBuilder types(List<String> types) {
      this._types = types;
      return this;
    }

    public CardBuilder subTypes(List<String> subTypes) {
      this._subTypes = subTypes;
      return this;
    }

    public CardBuilder text(String text) {
      this._text = text;
      return this;
    }

    public CardBuilder power(String power) {
      this._power = power;
      return this;
    }

    public CardBuilder toughness(String toughness) {
      this._toughness = toughness;
      return this;
    }

    public CardBuilder loyalty(Integer loyalty) {
      this._loyalty = loyalty;
      return this;
    }

    public CardBuilder imageName(String imageName) {
      this._imageName = imageName;
      return this;
    }

    public CardBuilder rulings(List<Map<String,String>> rulings) {
      this._rulings = rulings;
      return this;
    }

    public CardBuilder hand(Integer hand) {
      this._hand = hand;
      return this;
    }

    public CardBuilder life(Integer life) {
      this._life = life;
      return this;
    }

    public CardBuilder starter(Boolean starter) {
      this._starter = starter;
      return this;
    }

    public CardBuilder printings(List<String> printings) {
      this._printings = printings;
      return this;
    }

    public CardBuilder source(String source) {
      this._source = source;
      return this;
    }

    public CardBuilder legalities(List<Map<String,String>> legalities) {
      this._legalities = legalities;
      return this;
    }

    public CardBuilder colorIdentity(List<String> colorIdentity) {
      this._colorIdentity = colorIdentity;
      return this;
    }

    public CardFilter build() {
      return new CardFilter(this);
    }
  }

  private CardFilter(CardBuilder cardBuilder) {
    if(cardBuilder._card != null) {
      this.card = cardBuilder._card;
      return;
    }

    this.layout = cardBuilder._layout;
    this.name = cardBuilder._name;
    this.names = cardBuilder._names;
    this.manaCost = cardBuilder._manaCost;
    this.cmc = cardBuilder._cmc;
    this.colors = cardBuilder._colors;
    this.type = cardBuilder._type;
    this.superTypes = cardBuilder._superTypes;
    this.types = cardBuilder._types;
    this.subTypes = cardBuilder._subTypes;
    this.text = cardBuilder._text;
    this.power = cardBuilder._power;
    this.toughness = cardBuilder._toughness;
    this.loyalty = cardBuilder._loyalty;
    this.imageName = cardBuilder._imageName;
    this.rulings = cardBuilder._rulings;
    this.hand = cardBuilder._hand;
    this.life = cardBuilder._life;
    this.starter = cardBuilder._starter;
    this.printings = cardBuilder._printings;
    this.source = cardBuilder._source;
    this.legalities = cardBuilder._legalities;
    this.colorIdentity = cardBuilder._colorIdentity;
  }

  public String getLayout() {
    return this.card == null ? this.layout : this.card.getLayout();
  }

  public String getName() {
    return this.card == null ? this.name : this.card.getName();
  }

  public List<String> getNames() {
    return this.card == null ? this.names : this.card.getNames();
  }

  public String getManaCost() {
    return this.card == null ? this.manaCost : this.card.getManaCost();
  }

  public Double getCmc() {
    return this.card == null ? this.cmc : this.card.getCmc();
  }

  public List<String> getColors() {
    return this.card == null ? this.colors : this.card.getColors();
  }

  public String getType() {
    return this.card == null ? this.type : this.card.getType();
  }

  public List<String> getSuperTypes() {
    return this.card == null ? this.superTypes : this.card.getSuperTypes();
  }

  public List<String> getTypes() {
    return this.card == null ? this.types : this.card.getTypes();
  }

  public List<String> getSubTypes() {
    return this.card == null ? this.subTypes : this.card.getSubTypes();
  }

  public String getText() {
    return this.card == null ? this.text : this.card.getText();
  }

  public String getPower() {
    return this.card == null ? this.power : this.card.getPower();
  }

  public String getToughness() {
    return this.card == null ? this.toughness : this.card.getToughness();
  }

  public Integer getLoyalty() {
    return this.card == null ? this.loyalty : this.card.getLoyalty();
  }

  public String getImageName() {
    return this.card == null ? this.imageName : this.card.getImageName();
  }

  public List<Map<String,String>> getRulings() {
    return this.card == null ? this.rulings : this.card.getRulings();
  }

  public Integer getHand() {
    return this.card == null ? this.hand : this.card.getHand();
  }

  public Integer getLife() {
    return this.card == null ? this.life : this.card.getLife();
  }

  public Boolean getStarter() {
    return this.card == null ? this.starter : this.card.getStarter();
  }

  public List<String> getPrintings() {
    return this.card == null ? this.printings : this.card.getPrintings();
  }

  public String getSource() {
    return this.card == null ? this.source : this.card.getSource();
  }

  public List<Map<String,String>> getLegalities() {
    return this.card == null ? this.legalities : this.card.getLegalities();
  }

  public List<String> getColorIdentity() {
    return this.card == null ? this.colorIdentity : this.card.getColorIdentity();
  }

  public static List<Card> filter(List<Card> cards, CardFilter filter) {
    final Map<CardConstants, Pair<Supplier,Function>> getterMethodMap = generateFilterMap(filter);
    List<Card> filteredCards = new ArrayList<>();

    cards.forEach(c -> {
      if (getterMethodMap.entrySet().stream()
        .allMatch(e -> {
          if(e.getValue().getKey().get() instanceof List) {
            return allMatchList(e.getValue().getKey().get(), e.getValue().getValue().apply(c));
          }

          // This pulls the Supplier from the entry's key, and the Function from the
          // entry's value, and then compares the values of the two
          return e.getValue().getKey().get().equals(e.getValue().getValue().apply(c));
        })
      ) {
        filteredCards.add(c);
      }
    });

    return filteredCards;
  }

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

  private static Map<CardConstants, Pair<Supplier,Function>> generateFilterMap(CardFilter filter) {
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
