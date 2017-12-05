package io.github.cmansfield.validator;

import io.github.cmansfield.card.constants.CardConstants;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.card.constants.Color;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.LoadDeck;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.File;
import java.util.*;

import static org.testng.Assert.*;


public class DeckValidatorTest {

  @Test
  public void test_getNonCompliantCards_commander() throws IOException {
    final String TEST_DECK_FILE = "NonCompliantCommanderTestDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());
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
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_wrongColors() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    Card commander = deck.getFeaturedCard();

    List<Card> cards = deck.getOriginalCards();
    cards.remove(0);
//    Map cardPojo = CardUtils.generateTemplateCard().getCardPojo();
//    cardPojo.put(CardConstants.COLORS.toString(), Collections.singletonList(Color.RED.toString()));
//    Card badCard = new Card(cardPojo);
//    cards.add(badCard);
//
//    deck = new Deck(cards);
//    deck.setFormat(Format.COMMANDER);
//    deck.setFeaturedCard(commander);
//
//    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_noFeaturedCard() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    deck.setFeaturedCard(null);

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_wrongDeckSize() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    Card commander = deck.getFeaturedCard();

    List<Card> cards = deck.getOriginalCards();
    cards.remove(0);
    deck = new Deck(cards);
    deck.setFeaturedCard(commander);
    deck.setFormat(Format.COMMANDER);

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_nonLegendary() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

//    Card commander = deck.getFeaturedCard();
//    Map cardPojo = commander.getCardPojo();
//    cardPojo.remove(CardConstants.SUPER_TYPES.toString());
//    commander = new Card(cardPojo);
//    deck.setFeaturedCard(commander);
//
//    DeckValidator.isFormatCompliant(deck);
  }
}
