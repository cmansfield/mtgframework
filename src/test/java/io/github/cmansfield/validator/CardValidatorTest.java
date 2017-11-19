package io.github.cmansfield.validator;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.CardConstants;
import org.testng.annotations.Test;

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
    pojo.put(CardConstants.NAME.key(), name);
    pojo.put(CardConstants.COLORS.key(), colors);
    Card goodCard = new Card(pojo);

    Map<String,Object> pojoBad = new LinkedHashMap<>();
    pojoBad.put(CardConstants.NAME.key(), name);
    pojoBad.put(CardConstants.COLORS.key(), colors);
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
}
