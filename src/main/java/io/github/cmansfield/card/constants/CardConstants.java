package io.github.cmansfield.card.constants;

public enum CardConstants implements Constant<CardConstants> {
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
  RULINGS("rulings"),
  HAND("hand"),
  LIFE("life"),
  STARTER("starter"),
  PRINTINGS("printings"),
  SOURCE("source"),
  LEGALITIES("legalities"),
  COLOR_IDENTITY("colorIdentity");

  private final String value;

  CardConstants(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(CardConstants.class, value);
  }

  public static CardConstants find(String cardConstant) {
    for(CardConstants constant : CardConstants.values()) {
      if(constant.value.equalsIgnoreCase(cardConstant)) {
        return constant;
      }
    }

    return null;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
