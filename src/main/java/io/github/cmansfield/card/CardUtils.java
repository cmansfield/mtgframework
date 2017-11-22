package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.Formats;
import io.github.cmansfield.card.constants.Legality;

import java.util.*;


public final class CardUtils {

  public static class LegalitiesBuilder {
    private List<Formats> formats = new ArrayList<>();

    public LegalitiesBuilder() {}

    /**
     * @param format  - The game format that a card is legal to play in
     * @return        - A builder object to allow method chaining
     */
    public LegalitiesBuilder format(Formats format) {
      this.formats.add(format);
      return this;
    }

    /**
     * This method builds the collection with all of the legal formats
     *
     * @return - Collection formatted with all of the legal formats
     */
    public List<Map<String,String>> build() {
      List<Map<String,String>> legalities = new ArrayList<>();

      this.formats.forEach(format -> {
        Map<String,String> formatMap = new HashMap<>();
        formatMap.put(Legality.FORMAT.toString(), format.toString());
        formatMap.put(Legality.LEGALITY.toString(), "Legal");
        legalities.add(formatMap);
      });

      return legalities;
    }
  }

  /**
   * Returns a Set of all of all of the found legalities found in all cards supplied
   *
   * @param cards - A list of cards that will be iterated over
   * @return      - Returns a Set of all of the found legalities
   */
  public static Set<String> getListOfLegalities(List<Card> cards) {
    Set<String> legalities = new HashSet<>();

    if(cards == null) {
      return null;
    }

    cards.forEach(card -> {
      List<Map<String,String>> listOfLegalities = card.getLegalities();

      if(listOfLegalities == null) {
        return;
      }

      listOfLegalities.forEach(legalMap -> {
        String format = legalMap.get("format");
        if(!legalities.contains(format)) {
          legalities.add(format);
        }
      });
    });

    return legalities;
  }
}