package io.github.cmansfield.card;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.deck.constants.Legality;
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
    legalitiesStr.add(Format.COMMANDER.toString());
    legalitiesStr.add(Format.BATTLE_FOR_ZENDIKAR_BLOCK.toString());
    legalitiesStr.add(Format.MODERN.toString());
    legalitiesStr.add(Format.LEGACY.toString());

    List<Map<String,String>> legalities = new CardUtils
            .LegalitiesBuilder()
            .format(Format.COMMANDER)
            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
            .format(Format.MODERN)
            .format(Format.LEGACY)
            .build();

    assertNotNull(legalities);
    assertEquals(legalities.size(),4);
    legalities.forEach(legality -> {
      assertTrue(legalitiesStr.contains(legality.get(Legality.FORMAT.toString())));
    });
  }

  @Test(enabled = true)
  public void test_getListOfLegalities() throws IOException {
    List<Card> cards = LoadCards.loadCards();

    Set<String> legalities = CardUtils.getListOfLegalities(cards);

    for(Format format : Format.values()) {
      assertTrue(legalities.contains(format.toString()));
    }
  }
}
