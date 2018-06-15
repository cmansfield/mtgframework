package io.github.cmansfield.io.web;

import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.set.SetUtils;
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
  
  
  private MtgAdapter() {}

  public static void saveCardImages(List<Card> cards) {
    SetUtils.getMultiVerseId(cards)
            .forEach((k, v) -> saveImage(k.getName(), v));
  }
  
  /**
   * This method will reach out to the mgt website and download the card image for the
   * supplied name and card multiverseId
   * 
   * @param name          The name of the card who's image is being saved
   * @param multiverseId  The mtg multiverseId matching to a specific image
   */
  public static void saveImage(String name, String multiverseId) {
    File saveDir = new File(IoConstants.MTG_IMAGE_DIR);
    saveDir.mkdir();
    
    try {
      URL url = new URL(String.format(MTG_GET_IMAGE_API, multiverseId));
      File file = new File(String.format(
              "%s%s%s%s", 
              IoConstants.MTG_IMAGE_DIR,
              name, 
              '.', 
              IoConstants.IMAGE_EXT));
      FileUtils.copyURLToFile(url, file, IoConstants.TIMEOUT, IoConstants.TIMEOUT);
    }
    catch(MalformedURLException e) {
      LOGGER.warn("Malformed URL exception for card '{}' muliverseId '{}'", name, multiverseId);
    }
    catch(IOException e) {
      LOGGER.warn("Unable to save image for card '{}' muliverseId '{}'", name, multiverseId);
    }
  }
}
