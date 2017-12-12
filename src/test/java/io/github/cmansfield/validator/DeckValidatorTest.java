package io.github.cmansfield.validator;

import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.filters.CardFilter;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import io.github.cmansfield.io.LoadDeck;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.File;
import java.text.Normalizer;
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
    assertEquals(deck.generateFullDeckList().size(), Format.COMMANDER.getMaxDeckSize() - 1);
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

    Card commander = deck.getFeaturedCards().get(0);

    List<Card> cards = deck.generateFullDeckList();
    cards.remove(0);
    Card badCard = new Card.CardBuilder()
            .name("I don't have the right colors")
            .colors(Collections.singletonList(Color.RED.toString()))
            .build();
    cards.add(badCard);

    deck = new Deck(cards);
    deck.setFormat(Format.COMMANDER);
    deck.setFeaturedCards(Collections.singletonList(commander));

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_noFeaturedCard() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    deck.setFeaturedCards(null);

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_wrongDeckSize() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    Card commander = deck.getFeaturedCards().get(0);

    List<Card> cards = deck.generateFullDeckList();
    cards.remove(0);
    deck = new Deck(cards);
    deck.setFeaturedCards(Collections.singletonList(commander));
    deck.setFormat(Format.COMMANDER);

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_nonLegendary() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());

    Card commander = LoadCards.lookupCard("Soul Collector");
    deck.setFeaturedCards(Collections.singletonList(commander));

    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_commander_tooManyCardCopies() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck deck = LoadDeck.loadDeck(file.getAbsolutePath());
    List<Card> cards = deck.generateFullDeckList();
    Card duplicatedCard = cards.get(0);
    cards.remove(duplicatedCard);
    cards.remove(0);
    cards.add(duplicatedCard);
    cards.add(duplicatedCard);
    Deck badDeck = new Deck(cards, Format.COMMANDER);
    badDeck.setFeaturedCards(deck.getFeaturedCards());

    DeckValidator.isFormatCompliant(badDeck);
  }

  @Test
  public void test_isFormatCompliant_block() throws IOException {
    List<Card> cards = LoadCards.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
                            .build())
                    .build());

    Deck deck = new Deck(zendikarBlock, Format.BATTLE_FOR_ZENDIKAR_BLOCK);
    DeckValidator.isFormatCompliant(deck);
  }

  @Test
  public void test_isFormatCompliant_block_goodCounts() throws IOException {
    List<Card> cards = LoadCards.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
                            .build())
                    .build());
    zendikarBlock = CardFilter.filterNot(
            zendikarBlock,
            new Card.CardBuilder()
                    .type("Basic Land")
                    .build());
    Card muliCard = zendikarBlock.get(0);
    zendikarBlock.add(muliCard);
    zendikarBlock.add(muliCard);
    zendikarBlock.add(muliCard);    // 4th copy

    Deck deck = new Deck(zendikarBlock, Format.BATTLE_FOR_ZENDIKAR_BLOCK);
    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_block_badCounts() throws IOException {
    List<Card> cards = LoadCards.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
                            .build())
                    .build());
    zendikarBlock = CardFilter.filterNot(
          zendikarBlock,
          new Card.CardBuilder()
                  .type("Basic Land")
                  .build());
    Card muliCard = zendikarBlock.get(0);
    zendikarBlock.add(muliCard);
    zendikarBlock.add(muliCard);
    zendikarBlock.add(muliCard);
    zendikarBlock.add(muliCard);    // 5th copy

    Deck deck = new Deck(zendikarBlock, Format.BATTLE_FOR_ZENDIKAR_BLOCK);
    DeckValidator.isFormatCompliant(deck);
  }

  @Test
  public void test_isFormatCompliant_block_goodDeckSize() throws IOException {
    List<Card> cards = LoadCards.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
                            .build())
                    .build());
    zendikarBlock = zendikarBlock.subList(0, Format.BATTLE_FOR_ZENDIKAR_BLOCK.getMinDeckSize());

    Deck deck = new Deck(zendikarBlock, Format.BATTLE_FOR_ZENDIKAR_BLOCK);
    DeckValidator.isFormatCompliant(deck);
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_isFormatCompliant_block_badDeckSize() throws IOException {
    List<Card> cards = LoadCards.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.BATTLE_FOR_ZENDIKAR_BLOCK)
                            .build())
                    .build());
    zendikarBlock = zendikarBlock.subList(0, Format.BATTLE_FOR_ZENDIKAR_BLOCK.getMinDeckSize() - 1);

    Deck deck = new Deck(zendikarBlock, Format.BATTLE_FOR_ZENDIKAR_BLOCK);
    DeckValidator.isFormatCompliant(deck);
  }
}
