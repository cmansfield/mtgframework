package io.github.cmansfield.deck.constants;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.constants.Constant;

import java.util.Collections;

public enum Format implements Constant<Format> {
  SCARS_OF_MIRRODIN_BLOCK("Scars of Mirrodin Block", 60, Integer.MAX_VALUE, 4),
  ICE_AGE_BLOCK("Ice Age Block", 60, Integer.MAX_VALUE, 4),
  KHANS_OF_TARKIR_BLOCK("Khans of Tarkir Block", 60, Integer.MAX_VALUE, 4),
  LORWYN_SHADOWMOOR_BLOCK("Lorwyn-Shadowmoor Block", 60, Integer.MAX_VALUE, 4),
  COMMANDER("Commander",
          100,
          100,
          1,
          new Card
                  .CardBuilder()
                  .superTypes(Collections.singletonList("Legendary"))
                  .build()),
  ODYSSEY_BLOCK("Odyssey Block", 60, Integer.MAX_VALUE, 4),
  KALADESH_BLOCK("Kaladesh Block", 60, Integer.MAX_VALUE, 4),
  LEGACY("Legacy"),
  MASQUES_BLOCK("Masques Block", 60, Integer.MAX_VALUE, 4),
  EXTENDED("Extended"),
  IXALAN_BLOCK("Ixalan Block", 60, Integer.MAX_VALUE, 4),
  STANDARD("Standard", 60, Integer.MAX_VALUE, 4),
  UN_SETS("Un-Sets", 60, Integer.MAX_VALUE, 4),
  MODERN("Modern", 60, Integer.MAX_VALUE, 4),
  VINTAGE("Vintage"),
  SHADOWS_OVER_INNISTRAD_BLOCK("Shadows over Innistrad Block", 60, Integer.MAX_VALUE, 4),
  INNISTRAD_BLOCK("Innistrad Block", 60, Integer.MAX_VALUE, 4),
  INVASION_BLOCK("Invasion Block", 60, Integer.MAX_VALUE, 4),
  THEROS_BLOCK("Theros Block", 60, Integer.MAX_VALUE, 4),
  SHARDS_OF_ALARA_BLOCK("Shards of Alara Block", 60, Integer.MAX_VALUE, 4),
  KAMIGAWA_BLOCK("Kamigawa Block", 60, Integer.MAX_VALUE, 4),
  TEMPEST_BLOCK("Tempest Block", 60, Integer.MAX_VALUE, 4),
  RETURN_TO_RAVNICA_BLOCK("Return to Ravnica Block", 60, Integer.MAX_VALUE, 4),
  URZA_BLOCK("Urza Block", 60, Integer.MAX_VALUE, 4),
  MIRAGE_BLOCK("Mirage Block", 60, Integer.MAX_VALUE, 4),
  BATTLE_FOR_ZENDIKAR_BLOCK("Battle for Zendikar Block", 60, Integer.MAX_VALUE, 4),
  CLASSIC("Classic", 60, Integer.MAX_VALUE, 4),
  TIME_SPIRAL_BLOCK("Time Spiral Block", 60, Integer.MAX_VALUE, 4),
  MIRRODIN_BLOCK("Mirrodin Block", 60, Integer.MAX_VALUE, 4),
  ZENDIKAR_BLOCK("Zendikar Block", 60, Integer.MAX_VALUE, 4),
  AMONKHET_BLOCK("Amonkhet Block", 60, Integer.MAX_VALUE, 4),
  ONSLAUGHT_BLOCK("Onslaught Block", 60, Integer.MAX_VALUE, 4),
  RAVNICA_BLOCK("Ravnica Block", 60, Integer.MAX_VALUE, 4);

  private String value;
  private int minDeckSize;
  private int maxDeckSize;
  private int maxCopiesOfCard;
  private Card featureCardFilter;

  Format(String value) {
    this.value = value;
  }

  Format(String value, int minDeckSize, int maxDeckSize, int maxCopiesOfCard) {
    this(value);
    this.minDeckSize = minDeckSize;
    this.maxDeckSize = maxDeckSize;
    this.maxCopiesOfCard = maxCopiesOfCard;
  }

  Format(String value, int minDeckSize, int maxDeckSize, int maxCopiesOfCard, Card featureCardFilter) {
    this(value, minDeckSize, maxDeckSize, maxCopiesOfCard);
    this.featureCardFilter = featureCardFilter;
  }

  public int getMinDeckSize() {
    return this.minDeckSize;
  }

  public int getMaxDeckSize() {
    return this.maxDeckSize;
  }

  public int getMaxCopiesOfCard() {
    return this.maxCopiesOfCard;
  }

  public Card getFeatureCardFilter() {
    return this.featureCardFilter;
  }

  public static boolean contains(final String value) {
    return Constant.contains(Format.class, value);
  }

  public static Format find(String value) {
    return Constant.find(Format.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
