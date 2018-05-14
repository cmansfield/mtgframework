package io.github.cmansfield.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import io.github.cmansfield.set.MtgSet;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class CardSetAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(CardSetAdapter.class);
  private static Map<String, MtgSet> setMap;

  private CardSetAdapter() {}

  public static Map<String, MtgSet> getSets() throws IOException {
    if(setMap == null) {
      importSets();
    }
    return new HashMap<>(setMap);
  }

  /**
   * This method will import the set information from the saved json file
   */
  private static void importSets() throws IOException {
    ZipFile zip = new ZipFile(IoConstants.MTG_JSON_LISTS_ZIP);
    ObjectMapper mapper = new ObjectMapper();

    try(InputStream inputstream = zip.getInputStream(zip.getEntry(IoConstants.ALL_SETS_FILE_NAME))) {
      setMap = mapper.readValue(inputstream, new TypeReference<Map<String,MtgSet>>(){});
    }
    catch (Exception e) {
      throw new IOException("Unable to load file " + IoConstants.ALL_CARDS_FILE_NAME, e);
    }
    finally {
      zip.close();
    }
  }

  /**
   * This method will return a MtgSet object matching the supplied set code
   *
   * @param setCode   The set code of the MtgSet to look up
   * @return          The MtgSet object if found
   */
  public static MtgSet lookUpSet(String setCode) {
    if(StringUtils.isBlank(setCode)) {
      LOGGER.warn("A blank set code was supplied");
      return null;
    }
    if(setMap == null) {
      try {
        importSets();
      }
      catch (IOException e) {
        LOGGER.error("Unable to import the set list", e);
        return null;
      }
    }
    if(!setMap.containsKey(setCode)) {
      LOGGER.warn("Set code '{}' does not exist", setCode);
      return null;
    }

    return setMap.get(setCode);
  }
}
