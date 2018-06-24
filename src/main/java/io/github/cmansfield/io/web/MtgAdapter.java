package io.github.cmansfield.io.web;

import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.set.SetUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import io.github.cmansfield.card.Card;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.net.URL;


public class MtgAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(MtgAdapter.class);
  private static final String MTG_GET_IMAGE_API = "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=%s&type=card";
  private static final int IMAGE_SAVE_RETRY = 3;
  
  private MtgAdapter() {}

  /**
   * This will save a list of card images
   *
   * @param cards         A list of cards to have their images downloaded and saved
   */
  public static void saveCardImages(List<Card> cards, boolean isOrganized) {
    SetUtils.getMultiVerseId(cards)
            .forEach((k, v) -> saveImage(k, v, isOrganized, IMAGE_SAVE_RETRY));
  }
  
  /**
   * This method will reach out to the mgt website and download the card image for the
   * supplied name and card multiverseId
   * 
   * @param card          The card who's image is being saved
   * @param multiverseId  The mtg multiverseId matching to a specific image
   */
  private static void saveImage(Card card, String multiverseId, boolean isOrganized, int retry) {
    if(retry == 0) {
      return;
    }

    String destFolder = getDestinationFolder(card);

    try {
      new File(IoConstants.MTG_IMAGE_DIR).mkdir();
      new File(destFolder).mkdir();
      URL url = new URL(String.format(MTG_GET_IMAGE_API, multiverseId));
      File file = new File(String.format(
              "%s%s%s%s", 
              destFolder,
              card.getName(),
              '.', 
              IoConstants.IMAGE_EXT));
      FileUtils.copyURLToFile(url, file, IoConstants.TIMEOUT, IoConstants.TIMEOUT);
    }
    catch(MalformedURLException e) {
      LOGGER.warn("Malformed URL exception for card '{}' muliverseId '{}'", card.getName(), multiverseId);
    }
    catch(IOException e) {
      LOGGER.warn("Unable to save image for card '{}' muliverseId '{}'. Trying again.", card.getName(), multiverseId);
      saveImage(card, multiverseId, isOrganized, retry - 1);
    }
  }

  /**
   * This will determine which folder to save the supplied card based on its types
   *
   * @param card    The card to be saved
   * @return        The directory of where it should be saved
   */
  private static String getDestinationFolder(Card card) {
    List<String> types = card.getTypes();
    String folder = IoConstants.MTG_IMAGE_DIR + File.separator;

    if(CollectionUtils.isEmpty(types)) {
      return folder;
    }

    if(types.contains("Creature")) {
      folder += "creature" + File.separator;
    }
    if(types.contains("Land")) {
      folder += "land" + File.separator;
    }
    if(types.contains("Sorcery")) {
      folder += "sorcery" + File.separator;
    }
    if(types.contains("Instant")) {
      folder += "instant" + File.separator;
    }
    if(types.contains("Artifact")) {
      folder += "artifact" + File.separator;
    }
    if(types.contains("Enchantment")) {
      folder += "enchantment" + File.separator;
    }
    if(types.contains("Planeswalker")) {
      folder += "planeswalker" + File.separator;
    }

    return folder;
  }
}
