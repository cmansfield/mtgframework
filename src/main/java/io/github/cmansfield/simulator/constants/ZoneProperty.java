package io.github.cmansfield.simulator.constants;

import io.github.cmansfield.constants.Constant;

public enum ZoneProperty {
  PUBLIC("Public"),
  SHARED("Shared"),
  ORDERED("Ordered");

  private final String value;

  ZoneProperty(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(ZoneProperty.class, value);
  }

  public static ZoneProperty find(String value) {
    return Constant.find(ZoneProperty.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
