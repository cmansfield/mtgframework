package io.github.cmansfield.io;

import io.github.cmansfield.card.Card;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class LoadCardsTest {

  @Test
  public void test_lookupCard() throws IOException {
    String cardName = "Dryad Arbor";
    LoadCards.loadCards();
    Card card = LoadCards.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), cardName);
  }
}
