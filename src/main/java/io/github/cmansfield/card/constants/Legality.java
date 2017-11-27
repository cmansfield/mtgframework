package io.github.cmansfield.card.constants;

import io.github.cmansfield.constants.Constant;

public enum Legality implements Constant<Legality> {
  FORMAT("format"),
  LEGALITY("legality");

  private final String value;

  Legality(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(Legality.class, value);
  }

  public static Legality find(final String value) {
    return Constant.find(Legality.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
