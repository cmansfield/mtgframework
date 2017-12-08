package io.github.cmansfield.io;

import io.github.cmansfield.card.Card;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


public class LoadCardsTest {

  @Test
  public void test_lookupCard() throws IOException {
    String cardName = "Dryad Arbor";
    LoadCards.loadCards();

    Card card = LoadCards.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), cardName);
  }

  @Test
  public void test_lookupCard_lowercase() throws IOException {
    String cardName = "diabolic edict";
    LoadCards.loadCards();

    Card card = LoadCards.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Diabolic Edict");
  }

  @Test
  public void test_lookupCard_uppercase() throws IOException {
    String cardName = "DIABOLIC EDICT";
    LoadCards.loadCards();

    Card card = LoadCards.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Diabolic Edict");
  }

  @Test
  public void test_lookupCard_specialChars() throws IOException {
    String cardName = "Man-o`-War";
    LoadCards.loadCards();

    Card card = LoadCards.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Man-o'-War");
  }

  @Test
  public void test_lookupCard_notFound() throws IOException {
    String cardName = "LJFLJSDFLJEFJLDHfjsdklfj";
    LoadCards.loadCards();

    Card card = LoadCards.lookupCard(cardName);

    assertNull(card);
  }
}
