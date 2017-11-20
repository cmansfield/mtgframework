package io.github.cmansfield.validator;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;
import io.github.cmansfield.card.constants.Formats;
import io.github.cmansfield.io.LoadCardList;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class CardValidatorTest {

  @Test
  public void test_getListOfIncompleteCards() {
    List<String> colors = Collections.singletonList("White");
    String name = "TestCardName";
    Map<String,Object> pojo = new LinkedHashMap<>();
    pojo.put(CardConstants.NAME.toString(), name);
    pojo.put(CardConstants.COLORS.toString(), colors);
    Card goodCard = new Card(pojo);

    Map<String,Object> pojoBad = new LinkedHashMap<>();
    pojoBad.put(CardConstants.NAME.toString(), name);
    pojoBad.put(CardConstants.COLORS.toString(), colors);
    pojoBad.put("ThisIsNotARealKey", "Garbage data");
    Card badCard = new Card(pojoBad);

    List<Card> cards = new ArrayList<>();
    cards.add(goodCard);
    cards.add(badCard);

    List<Card> incompleteCards = CardValidator.getListOfIncompleteCards(cards);

    assertEquals(incompleteCards.size(), 1);
    assertTrue(incompleteCards.contains(badCard));
    assertFalse(incompleteCards.contains(goodCard));
  }

  @Test
  public void test_getListOfLegalities() throws IOException {
    List<Card> cards = LoadCardList.loadCards();

    Set<String> legalities = CardValidator.getListOfLegalities(cards);

    for(Formats format : Formats.values()) {
      assertTrue(legalities.contains(format.toString()));
    }
  }
}
