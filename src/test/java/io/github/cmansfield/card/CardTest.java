package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.CardConstants;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class CardTest {

  @Test
  public void test_getFromPojo() {
    List<String> colors = Collections.singletonList("White");
    String name = "TestCardName";
    Map<String,Object> pojo = new LinkedHashMap<>();
    pojo.put(CardConstants.NAME.key(), name);
    pojo.put(CardConstants.COLORS.key(), colors);

    Card card = new Card(pojo);
    assertEquals(card.getName(), name);
    assertEquals(card.getColors(), colors);
    assertEquals(card.getCardPojo(), pojo);
  }
}
