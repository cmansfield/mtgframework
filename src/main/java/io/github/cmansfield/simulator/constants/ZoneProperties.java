package io.github.cmansfield.simulator.constants;

import io.github.cmansfield.constants.Constant;

public enum ZoneProperties implements Constant<ZoneProperties> {
  PUBLIC("Public"),
  SHARED("Shared"),
  ORDERED("Ordered");

  private final String value;

  ZoneProperties(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(ZoneProperties.class, value);
  }

  public static ZoneProperties find(String value) {
    return Constant.find(ZoneProperties.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
