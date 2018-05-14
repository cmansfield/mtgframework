package io.github.cmansfield.set.constants;

public enum SetConstants {
  NAME("name"),
  CODE("code"),
  INFO_CODE("magicCardsInfoCode"),
  RELEASE_DATE("releaseDate"),
  BOARDER("border"),
  TYPE("type"),
  MKM_NAME("mkm_name"),
  MKM_ID("mkm_id"),
  CARDS("cards"),
  BLOCK("block");

  private final String value;

  SetConstants(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
