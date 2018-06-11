package io.github.cmansfield.io;

import java.io.File;

public final class IoConstants {
  public static final String MTG_JSON_LISTS = "MtgJsonLists" + File.separator;
  public static final String MTG_IMAGE_DIR = "MtgImages" + File.separator;
  public static final String ALL_CARDS_FILE_NAME = "AllCards-x.json";
  public static final String ALL_SETS_FILE_NAME = "AllSets-x.json";
  public static final String FEATURED_KEY = "featured_card";
  public static final String SAVE_DIR = "SavedCardLists";
  public static final String CARDS_ZIP = "cards.zip";
  public static final String SET_ZIP = "sets.zip";
  public static final String CARDS_KEY = "cards";
  public static final String IMAGE_EXT = "jpg";
  public static final int TIMEOUT = 1000;
  static final String JSON_EXT = "json";
  static final String TXT_EXT = "txt";

  // TappedImporter Constants
  public static final String INVENTORY_KEY = "inventory";
  public static final String QUANTITY_KEY = "qty";

  private IoConstants() {}
}
