package io.github.cmansfield.simulator.constants;

import io.github.cmansfield.constants.Constant;

import java.util.EnumMap;
import java.util.Map;

public enum Zone implements Constant<Zone> {
  LIBRARY("Library", false, false, true),
  HAND("Hand", false, false, false),
  BATTLEFIELD("Battlefield", true, true, false),
  GRAVEYARD("Graveyard", true, false, true),
  STACK("Stack", true, true, true),
  EXILE("Exile", true, true, false),
  COMMAND("Command", true, true, false),
  ANTE("Ante", true, true, false),
  SCRAPYARD("Scrapyard", true, false, true);

  private final String value;
  private Map<ZoneProperty,Boolean> properties;

  Zone(String value, boolean isPublic, boolean isShared, boolean isOrdered) {
    this.value = value;
    this.properties = new EnumMap<>(ZoneProperty.class);
    this.properties.put(ZoneProperty.PUBLIC, isPublic);
    this.properties.put(ZoneProperty.SHARED, isShared);
    this.properties.put(ZoneProperty.ORDERED, isOrdered);
  }

  public boolean getPropertyValue(final String prop) {
    ZoneProperty property = ZoneProperty.find(prop);

    if(property == null) {
      throw new NullPointerException(String.format("Zone property %s does not exisit", prop));
    }

    return getPropertyValue(property);
  }

  public boolean getPropertyValue(ZoneProperty property) {
      if(!this.properties.containsKey(property)) {
        throw new IllegalStateException("The Zone.java class is missing a property from the ZoneProperty.java class");
      }

      return this.properties.get(property);
    }

  public static boolean contains(final String value) {
    return Constant.contains(Zone.class, value);
  }

  public static Zone find(String value) {
    return Constant.find(Zone.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
