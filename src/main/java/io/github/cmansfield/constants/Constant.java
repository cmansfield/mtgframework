package io.github.cmansfield.constants;

public class Constant {
  private Constant() {}

  public static <E extends Enum<E>> boolean contains(Class<E> enumClass, String val) {

    for (Enum<E> enumVal : enumClass.getEnumConstants()) {
      if(val.equalsIgnoreCase(enumVal.toString()))
        return true;
    }

    return false;
  }

  public static <E extends Enum<E>> E find(Class<E> enumClass, String val) {

    for (Enum<E> enumVal : enumClass.getEnumConstants()) {
      if(val.equalsIgnoreCase(enumVal.toString()))
        return E.valueOf(enumClass, enumVal.name());
    }

    return null;
  }
}
