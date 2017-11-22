package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.Formats;
import io.github.cmansfield.card.constants.Legality;
import io.github.cmansfield.io.LoadCards;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;


public class CardUtilsTest {

  @Test
  public void test_legalitiesBuilder() {
    List<String> legalitiesStr = new ArrayList<>();
    legalitiesStr.add(Formats.COMMANDER.toString());
    legalitiesStr.add(Formats.BATTLE_FOR_ZENDIKAR_BLOCK.toString());
    legalitiesStr.add(Formats.MODERN.toString());
    legalitiesStr.add(Formats.LEGACY.toString());

    List<Map<String,String>> legalities = new CardUtils
            .LegalitiesBuilder()
            .format(Formats.COMMANDER)
            .format(Formats.BATTLE_FOR_ZENDIKAR_BLOCK)
            .format(Formats.MODERN)
            .format(Formats.LEGACY)
            .build();

    assertNotNull(legalities);
    assertEquals(legalities.size(),4);
    legalities.forEach(legality -> {
      assertTrue(legalitiesStr.contains(legality.get(Legality.FORMAT.toString())));
    });
  }

  // TODO - This test fails when there is no pre-existing cardList to load
  @Test(enabled = false)
  public void test_getListOfLegalities() throws IOException {
    List<Card> cards = LoadCards.loadCards();

    Set<String> legalities = CardUtils.getListOfLegalities(cards);

    for(Formats format : Formats.values()) {
      assertTrue(legalities.contains(format.toString()));
    }
  }
}
