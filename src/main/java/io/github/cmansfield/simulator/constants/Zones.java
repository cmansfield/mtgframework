package io.github.cmansfield.simulator.constants;

import io.github.cmansfield.constants.Constant;

import java.util.EnumMap;
import java.util.Map;

public enum Zones implements Constant<Zones> {
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
  private Map<ZoneProperties,Boolean> properties;

  Zones(String value, boolean isPublic, boolean isShared, boolean isOrdered) {
    this.value = value;
    this.properties = new EnumMap<>(ZoneProperties.class);
    this.properties.put(ZoneProperties.PUBLIC, isPublic);
    this.properties.put(ZoneProperties.SHARED, isShared);
    this.properties.put(ZoneProperties.ORDERED, isOrdered);
  }

  public boolean getProperty(final String prop) {
    ZoneProperties property = ZoneProperties.find(prop);

    if(property == null) {
      throw new NullPointerException(String.format("Zone property %s does not exisit", prop));
    }

    if(!this.properties.containsKey(property)) {
      throw new IllegalStateException("The Zone.java class is missing a property from the ZoneProperties.java class");
    }

    return this.properties.get(property);
  }

  public static boolean contains(final String value) {
    return Constant.contains(Zones.class, value);
  }

  public static Zones find(String value) {
    return Constant.find(Zones.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
