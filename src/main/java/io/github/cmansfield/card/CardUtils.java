package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.Color;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.deck.constants.Legality;
import org.apache.commons.lang.ObjectUtils;

import java.util.*;


public final class CardUtils {

  public static class LegalitiesBuilder {
    private List<Format> formats = new ArrayList<>();

    public LegalitiesBuilder() {}

    /**
     * @param format  - The game format that a card is legal to play in
     * @return        - A builder object to allow method chaining
     */
    public LegalitiesBuilder format(Format format) {
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
      throw new NullPointerException("Card list passed in was null");
    }

    cards.forEach(card -> {
      List<Map<String,String>> listOfLegalities = card.getLegalities();

      if(listOfLegalities == null) {
        return;
      }

      listOfLegalities.forEach(legalMap -> {
        String format = legalMap.get(Legality.FORMAT.toString());
        String legality = legalMap.get(Legality.LEGALITY.toString());
        if(legality.equalsIgnoreCase("Legal") && !legalities.contains(format)) {
          legalities.add(format);
        }
      });
    });

    return legalities;
  }

  /**
   * This generates a simple template card that contains all of the card formats
   *
   * @return - Returns a template card
   */
  public static Card generateTemplateCard() {
    List<String> names = new ArrayList<>();
    names.add("Card Name");
    names.add("AKA Card Name");

    List<String> colors = new ArrayList<>();
    colors.add(Color.WHITE.toString());
    colors.add(Color.BLUE.toString());
    colors.add(Color.BLACK.toString());
    colors.add(Color.RED.toString());
    colors.add(Color.GREEN.toString());

    List<Map<String,String>> rulings = new ArrayList<>();
    Map<String,String> rule = new HashMap<>();
    rule.put("date", "2004-10-04");
    rule.put("text", "This is a rule. It must be followed");
    rulings.add(rule);

    List<String> colorIdentity = new ArrayList<>();
    colorIdentity.add("W");
    colorIdentity.add("U");
    colorIdentity.add("B");
    colorIdentity.add("R");
    colorIdentity.add("G");

    return new Card
            .CardBuilder()
            .name("Card Name")
            .layout("normal")
            .names(names)
            .manaCost("{3}{W}{U}{B}{R}{G}")
            .cmc(8.0)
            .colors(colors)
            .type("Artifact — Creature")
            .superTypes(Collections.singletonList("Legendary"))
            .types(Collections.singletonList("Artifact"))
            .subTypes(Collections.singletonList("Creature"))
            .text("Indestructible\nThis is a card template")
            .power("25")
            .toughness("7.5")
            .loyalty(4)
            .imageName("Image Name")
            .rulings(rulings)
            .hand(5)
            .life(8)
            .starter(false)
            .printings(Collections.singletonList("TBA"))
            .source("Template Theme Deck")
            .legalities(new CardUtils.LegalitiesBuilder().format(Format.COMMANDER).build())
            .colorIdentity(colorIdentity)
            .build();
  }
}