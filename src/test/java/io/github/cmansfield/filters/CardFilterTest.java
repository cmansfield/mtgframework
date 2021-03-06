package io.github.cmansfield.filters;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.CardUtils;
import io.github.cmansfield.constants.Color;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.deck.constants.Legality;
import io.github.cmansfield.io.CardReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class CardFilterTest {
  private final String TEST_CARD_LIST_NAME = "TestFilterCardList.json";
  private List<Card> cards;

  @BeforeClass
  public void setUp() throws IOException {
    File file = new File(getClass().getClassLoader().getResource(TEST_CARD_LIST_NAME).getFile());
    cards = CardReader.loadCards(file.getAbsolutePath());
  }

  @Test
  public void test_cardFilter_colorFilter() throws IOException {
    List<String> validCardNames = new ArrayList<>();
    validCardNames.add("Cromat");
    validCardNames.add("Fervent Charge");
    validCardNames.add("Flowstone Charger");
    validCardNames.add("Martyrs' Tomb");


    assertNotNull(cards);
    Card cardFilter = new Card
            .CardBuilder()
            .colors(Collections.singletonList(Color.WHITE.toString()))
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);

    assertEquals(filteredCards.size(), 4);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_cardFilter_legendaryFilter() throws IOException {
    List<String> validCardNames = Collections.singletonList("Cromat");

    assertNotNull(cards);
    Card cardFilter = new Card
            .CardBuilder()
            .superTypes(Collections.singletonList("Legendary"))
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);

    assertEquals(filteredCards.size(), 1);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_cardFilter_complex() throws IOException {
    List<String> validCardNames = new ArrayList<>();
    validCardNames.add("Cromat");
    validCardNames.add("Llanowar Dead");

    Map<String,String> legal = new HashMap<>();
    legal.put(Legality.FORMAT.toString(), "Commander");
    legal.put(Legality.LEGALITY.toString(), "Legal");

    assertNotNull(cards);
    Card cardFilter = new Card
            .CardBuilder()
            .colors(Collections.singletonList(Color.BLACK.toString()))
            .legalities(Collections.singletonList(legal))
            .types(Collections.singletonList("Creature"))
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);

    assertEquals(filteredCards.size(), 2);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_multipleFilters() {
    List<String> validCardNames = new ArrayList<>();
    validCardNames.add("Cromat");
    validCardNames.add("Flowstone Charger");

    Card cardFilter1 = new Card
            .CardBuilder()
            .subTypes(Collections.singletonList("Illusion"))
            .build();

    Card cardFilter2 = new Card
            .CardBuilder()
            .subTypes(Collections.singletonList("Beast"))
            .build();

    List<Card> filters = new ArrayList<>();
    filters.add(cardFilter1);
    filters.add(cardFilter2);

    List<Card> filteredCards = CardFilter.filter(cards, filters);

    assertEquals(filteredCards.size(), 2);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_filterNot() {
    List<String> validCardNames = new ArrayList<>();
    validCardNames.add("Fervent Charge");
    validCardNames.add("Flowstone Charger");
    validCardNames.add("Martyrs' Tomb");
    validCardNames.add("Minotaur Illusionist");

    Card cardFilterNot = new Card
            .CardBuilder()
            .colors(Collections.singletonList(Color.GREEN.toString()))
            .build();

    List<Card> filteredCards = CardFilter.filterNot(cards, cardFilterNot);

    assertNotNull(filteredCards);
    assertEquals(filteredCards.size(), 4);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }

  @Test
  public void test_filterNot_complex() {
    List<Map<String,String>> legalities = new CardUtils
            .LegalitiesBuilder()
            .format(Format.COMMANDER)
            .build();

    Card cardFilterNot = new Card
            .CardBuilder()
            .legalities(legalities)
            .types(Collections.singletonList("Sorcery"))
            .build();

    List<Card> filteredCards = CardFilter.filterNot(cards, cardFilterNot);

    assertNotNull(filteredCards);
    assertEquals(filteredCards.size(), 1);
    assertEquals(filteredCards.get(0).getName(), "Ebony Treefolk");
  }

  @Test
  public void test_filter_multiLegalities() throws IOException {
    List<Card> cards = CardReader.loadCards();
    List<Card> zendikarBlock = CardFilter.filter(
            cards,
            new Card.CardBuilder()
                    .legalities(new CardUtils.LegalitiesBuilder()
                            .format(Format.ZENDIKAR_BLOCK)
                            .format(Format.COMMANDER)
                            .build())
                    .build());
    List<Card> foundCards = CardFilter.filter(
            zendikarBlock,
            new Card.CardBuilder()
                    .name("Emrakul, the Aeons Torn")
                    .build());

    assertEquals(foundCards.size(), 0);
  }

  @Test
  public void test_filter_searchForUnicode() throws IOException {
    String unicodeName = "Junún Efreet";
    List<Card> cardToFind = Collections.singletonList(CardReader.lookupCard(unicodeName));
    List<Card> foundCard = CardFilter.filter(cardToFind, new Card.CardBuilder().name("Junun Efreet").build());

    assertNotNull(foundCard);
    assertEquals(foundCard.size(), 1);
    assertEquals(foundCard.get(0).getName(), unicodeName);
  }

  @Test
  public void test_filter_searchWithUnicode() throws IOException {
    String unicodeName = "Junún Efreet";
    List<Card> cardToFind = Collections.singletonList(CardReader.lookupCard(unicodeName));
    List<Card> foundCard = CardFilter.filter(cardToFind, new Card.CardBuilder().name(unicodeName).build());

    assertNotNull(foundCard);
    assertEquals(foundCard.size(), 1);
    assertEquals(foundCard.get(0).getName(), unicodeName);
  }
}
