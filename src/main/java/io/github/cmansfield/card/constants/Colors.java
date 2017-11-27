package io.github.cmansfield.card.constants;

import io.github.cmansfield.constants.Constant;

public enum Colors implements Constant<Colors> {
  WHITE("White"),
  BLACK("Black"),
  RED("Red"),
  BLUE("Blue"),
  GREEN("Green"),
  COLORLESS("Colorless");

  private final String value;

  Colors(String value) {
    this.value = value;
  }

  private static Colors shortHand(final String val) {

    switch (val.toUpperCase()) {
      case "W":
        return Colors.WHITE;
      case "R":
        return Colors.RED;
      case "B":
        return Colors.BLACK;
      case "U":
        return Colors.BLUE;
      case "G":
        return Colors.GREEN;
      default:
        try {
          Integer.parseInt(val);
        } catch (NumberFormatException e) {
          return null;
        }
    }

    return Colors.COLORLESS;
  }

  public static boolean contains(final String value) {
    return Constant.contains(Colors.class, value)
            || Colors.shortHand(value) != null;
  }

  public static Colors find(final String value) {

    Colors color = Constant.find(Colors.class, value);
    if(color == null) color = Colors.shortHand(value);

    return color;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
