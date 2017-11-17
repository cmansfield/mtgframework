package io.github.cmansfield.card;

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

  public LinkedHashMap getCardPojo() {
    return cardPojo;
  }

  public String getName() {
    return getFromPojo(CardConstants.NAME.key());
  }

  public List<String> getNames() {
    return getFromPojo(CardConstants.NAMES.key());
  }

  public String getLayout() {
    return getFromPojo(CardConstants.LAYOUT.key());
  }

  public String getManaCost() {
    return getFromPojo(CardConstants.MANA_COST.key());
  }

  public Integer getCmc() {
    return getFromPojo(CardConstants.CMC.key());
  }

  public List<String> getColors() {
    return getFromPojo(CardConstants.COLORS.key());
  }

  public String getType() {
    return getFromPojo(CardConstants.TYPE.key());
  }

  public List<String> getSuperTypes() {
    return getFromPojo(CardConstants.SUPER_TYPES.key());
  }

  public List<String> getTypes() {
    return getFromPojo(CardConstants.TYPES.key());
  }

  public List<String> getSubTypes() {
    return getFromPojo(CardConstants.SUB_TYPES.key());
  }

  public String getText() {
    return getFromPojo(CardConstants.TEXT.key());
  }

  public String getPower() {
    return getFromPojo(CardConstants.POWER.key());
  }

  public String getToughness() {
    return getFromPojo(CardConstants.TOUGHNESS.key());
  }

  public Integer getLoyalty() {
    return getFromPojo(CardConstants.LOYALTY.key());
  }

  public String getImageName() {
    return getFromPojo(CardConstants.IMAGE_NAME.key());
  }

  public Integer getHand() {
    return getFromPojo(CardConstants.HAND.key());
  }

  public Integer getLife() {
    return getFromPojo(CardConstants.LIFE.key());
  }

  public Boolean getStarter() {
    return getFromPojo(CardConstants.STARTER.key());
  }

  public List<String> getColorIdentity() {
    return getFromPojo(CardConstants.COLOR_IDENTITY.key());
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
