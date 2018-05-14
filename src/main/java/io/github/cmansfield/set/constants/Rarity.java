package io.github.cmansfield.set.constants;

public enum Rarity {
  COMMON("Common", 1),
  UNCOMMON("Uncommon", 2),
  RARE("Rare", 3),
  MYTHIC_RARE("Mythic Rare", 4),
  OTHER("", 5);

  private final String name;
  private final int value;

  Rarity(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Rarity find(String value) {
    for(Rarity rarity : Rarity.class.getEnumConstants()) {
      if(rarity.toString().equals(value)) {
        return rarity;
      }
    }
    return Rarity.OTHER;
  }

  @Override
  public String toString() {
    return name;
  }
}
