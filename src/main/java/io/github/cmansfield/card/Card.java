package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.CardConstants;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Card implements Serializable {
  private Map cardPojo;

  // TODO - Make deep copy of cardPojo
  public Card(Object cardPojo) {
    if(cardPojo instanceof LinkedHashMap) {
      this.cardPojo = (LinkedHashMap) cardPojo;
    }
    else {
      throw new IllegalArgumentException("Object must be a LinkedHashMap");
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
}
