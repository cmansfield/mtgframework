package io.github.cmansfield.io;

import io.github.cmansfield.card.Card;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


public class CardReaderTest {

  @Test
  public void test_lookupCard() throws IOException {
    String cardName = "Dryad Arbor";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), cardName);
  }

  @Test
  public void test_lookupCard_lowercase() throws IOException {
    String cardName = "diabolic edict";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Diabolic Edict");
  }

  @Test
  public void test_lookupCard_uppercase() throws IOException {
    String cardName = "DIABOLIC EDICT";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Diabolic Edict");
  }

  @Test
  public void test_lookupCard_specialChars() throws IOException {
    String cardName = "Man-o`-War";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), "Man-o'-War");
  }

  @Test
  public void test_lookupCard_notFound() throws IOException {
    String cardName = "LJFLJSDFLJEFJLDHfjsdklfj";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNull(card);
  }

  @Test
  public void test_lookupCard_partialName() throws IOException {
    String fullName = "Doran, the Siege Tower";
    String cardName = "Doran, the Si";
    CardReader.loadCards();
    Card doran = CardReader.lookupCard(fullName);

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertNotNull(doran);
    assertEquals(card, doran);
    assertEquals(card.getName(), fullName);
  }

  @Test
  public void test_lookupCard_unicode() throws IOException {
    String cardName = "Junún Efreet";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(cardName);

    assertNotNull(card);
    assertEquals(card.getName(), cardName);
  }

  @Test
  public void test_lookupCard_findWithUnicode() throws IOException {
    String cardName = "Junún Efreet";
    String wrongName = "Junun Efreet";
    CardReader.loadCards();

    Card card = CardReader.lookupCard(wrongName);

    assertNotNull(card);
    assertEquals(card.getName(), cardName);
  }
}
