package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.io.LoadCards;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class DeckTest {

  @Test
  public void test_createDeck() throws IOException {
    final String TEST_DECK_FILE = "TestDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    List<Card> cards = LoadCards.loadCards(file.getAbsolutePath());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());

    assertNotNull(deck);
    assertEquals(cards.size(), 15);
    assertEquals(deck.getCards().size(), 10);
    assertEquals((int)deck.getQuantity("Behind the Scenes"), 5);
    assertEquals((int)deck.getQuantity("Chromatic Lantern"), 2);
  }
}
