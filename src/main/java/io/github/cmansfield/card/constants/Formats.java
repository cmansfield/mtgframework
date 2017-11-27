package io.github.cmansfield.card.constants;

import io.github.cmansfield.constants.Constant;

public enum Formats implements Constant<Formats> {
  SCARS_OF_MIRRODIN_BLOCK("Scars of Mirrodin Block"),
  ICE_AGE_BLOCK("Ice Age Block"),
  KHANS_OF_TARKIR_BLOCK("Khans of Tarkir Block"),
  LORWYN_SHADOWMOOR_BLOCK("Lorwyn-Shadowmoor Block"),
  COMMANDER("Commander"),
  ODYSSEY_BLOCK("Odyssey Block"),
  KALADESH_BLOCK("Kaladesh Block"),
  LEGACY("Legacy"),
  MASQUES_BLOCK("Masques Block"),
  EXTENDED("Extended"),
  IXALAN_BLOCK("Ixalan Block"),
  STANDARD("Standard"),
  UN_SETS("Un-Sets"),
  MODERN("Modern"),
  VINTAGE("Vintage"),
  SHADOWS_OVER_INNISTRAD_BLOCK("Shadows over Innistrad Block"),
  INNISTRAD_BLOCK("Innistrad Block"),
  INVASION_BLOCK("Invasion Block"),
  THEROS_BLOCK("Theros Block"),
  SHARDS_OF_ALARA_BLOCK("Shards of Alara Block"),
  KAMIGAWA_BLOCK("Kamigawa Block"),
  TEMPEST_BLOCK("Tempest Block"),
  RETURN_TO_RAVNICA_BLOCK("Return to Ravnica Block"),
  URZA_BLOCK("Urza Block"),
  MIRAGE_BLOCK("Mirage Block"),
  BATTLE_FOR_ZENDIKAR_BLOCK("Battle for Zendikar Block"),
  CLASSIC("Classic"),
  TIME_SPIRAL_BLOCK("Time Spiral Block"),
  MIRRODIN_BLOCK("Mirrodin Block"),
  ZENDIKAR_BLOCK("Zendikar Block"),
  AMONKHET_BLOCK("Amonkhet Block"),
  ONSLAUGHT_BLOCK("Onslaught Block"),
  RAVNICA_BLOCK("Ravnica Block");

  private final String value;

  Formats(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(Formats.class, value);
  }

  public static Formats find(String value) {
    return Constant.find(Formats.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
