package io.github.cmansfield.Card;

import java.io.Serializable;
import java.util.LinkedHashMap;


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

    if(cardPojo.containsKey(CardConstants.NAME)) {
      return (String)cardPojo.get(CardConstants.NAME);
    }

    return "";
  }

//  public String getLayout() {
//    return this.layout;
//  }
//
//  public String getManaCost() {
//    return this.manaCost;
//  }
//
//  public int getCmc() {
//    return this.cmc;
//  }
//
//  public List<String> getColors() {
//    return this.colors;
//  }
//
//  public String getType() {
//    return this.type;
//  }
//
//  public List<String> getTypes() {
//    return this.types;
//  }
//
//  public List<String> getSubTypes() {
//    return this.subTypes;
//  }
//
//  public String getText() {
//    return this.text;
//  }
//
//  public String getPower() {
//    return this.power;
//  }
//
//  public String getToughness() {
//    return this.toughness;
//  }
//
//  public String getImageName() {
//    return this.imageName;
//  }
//
//  public List<String> getColorIdentity() {
//    return this.colorIdentity;
//  }
}
