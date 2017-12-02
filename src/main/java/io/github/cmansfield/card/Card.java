package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.CardConstants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Card {
  private Map cardPojo;

  // TODO - Make deep copy of cardPojo
  public Card(Object cardPojo) {
    if(cardPojo instanceof Map) {
      this.cardPojo = (Map)cardPojo;
    }
    else {
      throw new IllegalArgumentException("Object must be a Map");
    }
  }

  public Map getCardPojo() {
    return cardPojo;
  }

  public String getName() {
    return getFromPojo(CardConstants.NAME.toString());
  }

  public List<String> getNames() {
    return getFromPojo(CardConstants.NAMES.toString());
  }

  public String getLayout() {
    return getFromPojo(CardConstants.LAYOUT.toString());
  }

  public String getManaCost() {
    return getFromPojo(CardConstants.MANA_COST.toString());
  }

  public Double getCmc() {
    if(getFromPojo(CardConstants.CMC.toString()) instanceof Integer) {
      return (double)((int)getFromPojo(CardConstants.CMC.toString()));
    }

    return getFromPojo(CardConstants.CMC.toString());
  }

  public List<String> getColors() {
    return getFromPojo(CardConstants.COLORS.toString());
  }

  public String getType() {
    return getFromPojo(CardConstants.TYPE.toString());
  }

  public List<String> getSuperTypes() {
    return getFromPojo(CardConstants.SUPER_TYPES.toString());
  }

  public List<String> getTypes() {
    return getFromPojo(CardConstants.TYPES.toString());
  }

  public List<String> getSubTypes() {
    return getFromPojo(CardConstants.SUB_TYPES.toString());
  }

  public String getText() {
    return getFromPojo(CardConstants.TEXT.toString());
  }

  public String getPower() {
    return getFromPojo(CardConstants.POWER.toString());
  }

  public String getToughness() {
    return getFromPojo(CardConstants.TOUGHNESS.toString());
  }

  public Integer getLoyalty() {
    return getFromPojo(CardConstants.LOYALTY.toString());
  }

  public String getImageName() {
    return getFromPojo(CardConstants.IMAGE_NAME.toString());
  }

  public List<Map<String,String>> getRulings() {
    return getFromPojo(CardConstants.RULINGS.toString());
  }

  public Integer getHand() {
    return getFromPojo(CardConstants.HAND.toString());
  }

  public Integer getLife() {
    return getFromPojo(CardConstants.LIFE.toString());
  }

  public Boolean getStarter() {
    return getFromPojo(CardConstants.STARTER.toString());
  }

  public List<String> getPrintings() {
    return getFromPojo(CardConstants.PRINTINGS.toString());
  }

  public String getSource() {
    return getFromPojo(CardConstants.SOURCE.toString());
  }

  public List<Map<String,String>> getLegalities() {
    return getFromPojo(CardConstants.LEGALITIES.toString());
  }

  public List<String> getColorIdentity() {
    return getFromPojo(CardConstants.COLOR_IDENTITY.toString());
  }

  private <T> T getFromPojo(String key) {
    if(cardPojo.containsKey(key)) {
      return (T)cardPojo.get(key);
    }

    return null;
  }

  @Override
  public String toString() {
    return cardPojo.toString();
  }

  public static class CardBuilder {
    private Map<String,Object> _cardPojo;

    public CardBuilder() {
      this._cardPojo = new LinkedHashMap<>();
    }

    public Card.CardBuilder name(String key) {
      this._cardPojo.put(CardConstants.NAME.toString(), key);
      return this;
    }

    public Card.CardBuilder layout(String layout) {
      this._cardPojo.put(CardConstants.LAYOUT.toString(), layout);
      return this;
    }

    public Card.CardBuilder names(List<String> names) {
      this._cardPojo.put(CardConstants.NAMES.toString(), names);
      return this;
    }

    public Card.CardBuilder manaCost(String manaCost) {
      this._cardPojo.put(CardConstants.MANA_COST.toString(), manaCost);
      return this;
    }

    public Card.CardBuilder cmc(Double cmc) {
      this._cardPojo.put(CardConstants.CMC.toString(), cmc);
      return this;
    }

    public Card.CardBuilder colors(List<String> colors) {
      this._cardPojo.put(CardConstants.COLORS.toString(), colors);
      return this;
    }

    public Card.CardBuilder type(String type) {
      this._cardPojo.put(CardConstants.TYPE.toString(), type);
      return this;
    }

    public Card.CardBuilder superTypes(List<String> superTypes) {
      this._cardPojo.put(CardConstants.SUPER_TYPES.toString(), superTypes);
      return this;
    }

    public Card.CardBuilder types(List<String > types) {
      this._cardPojo.put(CardConstants.TYPES.toString(), types);
      return this;
    }

    public Card.CardBuilder subTypes(List<String> subTypes) {
      this._cardPojo.put(CardConstants.SUB_TYPES.toString(), subTypes);
      return this;
    }

    public Card.CardBuilder text(String text) {
      this._cardPojo.put(CardConstants.TEXT.toString(), text);
      return this;
    }

    public Card.CardBuilder power(String power) {
      this._cardPojo.put(CardConstants.POWER.toString(), power);
      return this;
    }

    public Card.CardBuilder toughness(String toughness) {
      this._cardPojo.put(CardConstants.TOUGHNESS.toString(), toughness);
      return this;
    }

    public Card.CardBuilder loyalty(Integer loyalty) {
      this._cardPojo.put(CardConstants.LOYALTY.toString(), loyalty);
      return this;
    }

    public Card.CardBuilder imageName(String imageName) {
      this._cardPojo.put(CardConstants.IMAGE_NAME.toString(), imageName);
      return this;
    }

    public Card.CardBuilder rulings(List<Map<String,String>> rulings) {
      this._cardPojo.put(CardConstants.RULINGS.toString(), rulings);
      return this;
    }

    public Card.CardBuilder hand(Integer hand) {
      this._cardPojo.put(CardConstants.HAND.toString(), hand);
      return this;
    }

    public Card.CardBuilder life(Integer life) {
      this._cardPojo.put(CardConstants.LIFE.toString(), life);
      return this;
    }

    public Card.CardBuilder starter(Boolean starter) {
      this._cardPojo.put(CardConstants.STARTER.toString(), starter);
      return this;
    }

    public Card.CardBuilder printings(List<String> printings) {
      this._cardPojo.put(CardConstants.PRINTINGS.toString(), printings);
      return this;
    }

    public Card.CardBuilder source(String source) {
      this._cardPojo.put(CardConstants.SOURCE.toString(), source);
      return this;
    }

    public Card.CardBuilder legalities(List<Map<String,String>> legalities) {
      this._cardPojo.put(CardConstants.LEGALITIES.toString(), legalities);
      return this;
    }

    public Card.CardBuilder colorIdentity(List<String> colorIdentity) {
      this._cardPojo.put(CardConstants.COLOR_IDENTITY.toString(), colorIdentity);
      return this;
    }

    public Card build() {
      return new Card(this._cardPojo);
    }
  }
}
