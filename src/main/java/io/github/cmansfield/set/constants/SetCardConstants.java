package io.github.cmansfield.set.constants;

public enum SetCardConstants {
  NAME("name"),
  MULTIVERSE_ID("multiverseid"),
  RARITY("rarity");
  
  private final String value;
  
  SetCardConstants(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
