package io.github.cmansfield.Card;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;


public class Card implements Serializable {
  private LinkedHashMap cardPojo;

  public Card(Object cardPojo) {
    if(cardPojo instanceof LinkedHashMap) {
      this.cardPojo = (LinkedHashMap) cardPojo;
    }
    else {
      throw new IllegalArgumentException("Object must be a LinkedHashMap");
    }
  }

  public String getName() {
    return getFromPojo(CardConstants.NAME);
  }

  public String getLayout() {
    return getFromPojo(CardConstants.LAYOUT);
  }

  public String getManaCost() {
    return getFromPojo(CardConstants.MANA_COST);
  }

  public Integer getCmc() {
    return getFromPojo(CardConstants.CMC);
  }

  public List<String> getColors() {
    return getFromPojo(CardConstants.COLORS);
  }

  public String getType() {
    return getFromPojo(CardConstants.TYPE);
  }

  public List<String> getTypes() {
    return getFromPojo(CardConstants.TYPES);
  }

  public List<String> getSubTypes() {
    return getFromPojo(CardConstants.SUB_TYPES);
  }

  public String getText() {
    return getFromPojo(CardConstants.TEXT);
  }

  public String getPower() {
    return getFromPojo(CardConstants.POWER);
  }

  public String getToughness() {
    return getFromPojo(CardConstants.TOUGHNESS);
  }

  public String getImageName() {
    return getFromPojo(CardConstants.IMAGE_NAME);
  }

  public List<String> getColorIdentity() {
    return getFromPojo(CardConstants.COLOR_IDENTITY);
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
}
