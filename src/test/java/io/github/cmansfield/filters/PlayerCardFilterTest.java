package io.github.cmansfield.filters;

import io.github.cmansfield.io.LoadDeck;
import io.github.cmansfield.simulator.player.PlayerCard;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;
import org.testng.annotations.Test;

import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class PlayerCardFilterTest {

  @Test
  public void test_filter() throws IOException {
    final String TEST_DECK_FILE = "CompleteCommanderDeck.json";
    File file = new File(getClass().getClassLoader().getResource(TEST_DECK_FILE).getFile());
    Deck doranDeck = LoadDeck.loadDeck(file.getAbsolutePath());

    List<String> uniqueCards = new ArrayList<>();
    uniqueCards.add("Swamp");
    uniqueCards.add("Forest");
    uniqueCards.add("Plains");
    uniqueCards.add("Vivid Grove");
    uniqueCards.add("Vivid Marsh");
    uniqueCards.add("Vivid Meadow");
    uniqueCards.add("Jungle Hollow");
    uniqueCards.add("Khalni Garden");
    uniqueCards.add("Command Tower");
    uniqueCards.add("Elfhame Palace");
    uniqueCards.add("Evolving Wilds");
    uniqueCards.add("Reliquary Tower");
    uniqueCards.add("Golgari Rot Farm");
    uniqueCards.add("Thawing Glaciers");
    uniqueCards.add("Blossoming Sands");
    uniqueCards.add("Stirring Wildwood");
    uniqueCards.add("Sandsteppe Citadel");
    uniqueCards.add("Terramorphic Expanse");
    uniqueCards.add("Temple of the False God");

    Player player1 = new Player(doranDeck);

    List<PlayerCard> player1Cards = player1.getZone(Zone.LIBRARY);
    PlayerCard playerCardFilter = new PlayerCard
            .PlayerCardBuilder()
            .owner(player1)
            .card(new Card
                    .CardBuilder()
                    .types(Collections.singletonList("Land"))
                    .build())
            .build();
    List filteredCards = PlayerCardFilter.filter(player1Cards, playerCardFilter);

    assertNotNull(filteredCards);
    assertEquals(filteredCards.size(), 32);

    filteredCards.forEach(card -> {
      assertTrue(uniqueCards.contains(((Card)card).getName()));
    });
  }
}
