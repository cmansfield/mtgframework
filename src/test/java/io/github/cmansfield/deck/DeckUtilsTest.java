package io.github.cmansfield.deck;

import io.github.cmansfield.card.Card;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DeckUtilsTest {

  @Test
  public void test_getCardCount() {
    String[] cardNames = {"I Win the Game Card", "Defuse Card", "Forest", "Uno"};
    List<Card> cards = new ArrayList<>();
    cards.add(new Card.CardBuilder().name(cardNames[0]).build());
    cards.add(new Card.CardBuilder().name(cardNames[0]).build());
    cards.add(new Card.CardBuilder().name(cardNames[0]).build());
    cards.add(new Card.CardBuilder().name(cardNames[1]).build());
    cards.add(new Card.CardBuilder().name(cardNames[2]).build());
    cards.add(new Card.CardBuilder().name(cardNames[2]).build());
    cards.add(new Card.CardBuilder().name(cardNames[3]).build());

    List<Deck> decks = new ArrayList<>();
    decks.add(new Deck(cards));
    decks.add(new Deck(cards));
    decks.add(new Deck(cards));

    Map<String,Integer> cardCounts = DeckUtils.getCardCount(decks);

    assertNotNull(cardCounts);
    assertEquals(cardCounts.size(), cardNames.length);
    for(String cardName : cardNames) {
      assertTrue(cardCounts.containsKey(cardName));
    }
    assertEquals(cardCounts.get(cardNames[0]), (Integer)9);
    assertEquals(cardCounts.get(cardNames[1]), (Integer)3);
    assertEquals(cardCounts.get(cardNames[2]), (Integer)6);
    assertEquals(cardCounts.get(cardNames[3]), (Integer)3);
  }
}
