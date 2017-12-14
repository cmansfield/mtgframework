package io.github.cmansfield.constants;

public enum Color implements Constant<Color> {
  WHITE("White"),
  BLACK("Black"),
  RED("Red"),
  BLUE("Blue"),
  GREEN("Green"),
  COLORLESS("Colorless");

  private final String value;

  Color(String value) {
    this.value = value;
  }

  private static Color shortHand(final String val) {

    switch (val.toUpperCase()) {
      case "W":
        return Color.WHITE;
      case "R":
        return Color.RED;
      case "B":
        return Color.BLACK;
      case "U":
        return Color.BLUE;
      case "G":
        return Color.GREEN;
      default:
        try {
          Integer.parseInt(val);    // NOSONAR
        } catch (NumberFormatException e) {
          return null;
        }
    }

    return Color.COLORLESS;
  }

  public static boolean contains(final String value) {
    return Constant.contains(Color.class, value)
            || Color.shortHand(value) != null;
  }

  public static Color find(final String value) {

    Color color = Constant.find(Color.class, value);
    if(color == null) color = Color.shortHand(value);

    return color;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
