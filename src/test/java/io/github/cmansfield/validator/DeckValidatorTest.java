package io.github.cmansfield.validator;

import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.testng.Assert.*;


public class DeckValidatorTest {

  @Test
  public void test_getNonCompliantCards_commander() throws IOException {
    final String TEST_DECK_FILE = "NonCompliantCommanderTestDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());
    deck.setFormat(Format.COMMANDER);

    List<Card> nonCompliantCards = DeckValidator.getNonCompliantCards(deck);

    List<String> nonCompliantCardNames = new ArrayList<>();
    nonCompliantCardNames.add("AWOL");
    nonCompliantCardNames.add("Rebirth");
    nonCompliantCardNames.add("Biorhythm");

    assertNotNull(nonCompliantCards);
    assertEquals(deck.getOriginalCards().size(), 100);
    assertEquals(nonCompliantCards.size(), 3);
    assertEquals(nonCompliantCards.size(), nonCompliantCardNames.size());
    nonCompliantCards.forEach(card -> {
      assertTrue(nonCompliantCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_isFormatCompliant_commander() throws IOException {
    final String TEST_DECK_FILE = "NonCompliantCommanderTestDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadCards.loadDeck(file.getAbsolutePath());
    deck.setFormat(Format.COMMANDER);

    assertFalse(DeckValidator.isFormatCompliant(deck));
  }
}
