package io.github.cmansfield.card;

public enum CardConstants {
  LAYOUT("layout"),
  NAME("name"),
  NAMES("names"),
  MANA_COST("manaCost"),
  CMC("cmc"),
  COLORS("colors"),
  TYPE("type"),
  SUPER_TYPES("supertypes"),
  TYPES("types"),
  SUB_TYPES("subtypes"),
  TEXT("text"),
  POWER("power"),
  TOUGHNESS("toughness"),
  LOYALTY("loyalty"),
  IMAGE_NAME("imageName"),
  HAND("hand"),
  LIFE("life"),
  STARTER("starter"),
  COLOR_IDENTITY("colorIdentity");

  private final String key;

  private CardConstants(String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }

  public static CardConstants getConstant(String cardConstant) {
    for(CardConstants constant : CardConstants.values()) {
      if(constant.key.equalsIgnoreCase(cardConstant)) {
        return constant;
      }
    }

    return null;
  }
}
