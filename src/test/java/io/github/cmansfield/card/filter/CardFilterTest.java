package io.github.cmansfield.card.filter;

import io.github.cmansfield.card.Card;
import io.github.cmansfield.card.constants.Colors;
import io.github.cmansfield.io.LoadCardList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class CardFilterTest {
  private final String TEST_CARD_LIST_NAME = "TestFilterCardList.json";
  private List<Card> cards;

  @BeforeMethod
  public void setUp() throws IOException {
    File file = new File(getClass().getClassLoader().getResource(TEST_CARD_LIST_NAME).getFile());
    cards = LoadCardList.loadCards(file.getAbsolutePath());
  }

  @Test
  public void test_cardFilterTest1() throws IOException {
    List<String> validCardNames = new ArrayList<>();
    validCardNames.add("Cromat");
    validCardNames.add("Fervent Charge");
    validCardNames.add("Flowstone Charger");
    validCardNames.add("Martyrs' Tomb");

    assertNotNull(cards);
    CardFilter cardFilter = new CardFilter
            .CardBuilder()
            .colors(Collections.singletonList(Colors.WHITE.toString()))
            .build();

    List<Card> filteredCards = CardFilter.filter(cards, cardFilter);

    assertEquals(filteredCards.size(), 4);
    filteredCards.forEach(card -> {
      assertTrue(validCardNames.contains(card.getName()));
    });
  }
}
