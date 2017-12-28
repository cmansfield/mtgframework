package io.github.cmansfield.simulator.player;

import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.deck.Deck;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;
import java.util.*;


public class Player {
  private static final String ZONE_NOT_MANAGED_BY_PLAYER_MSG = "Zone '%s' is not managed by the player";
  private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

  private Map<Zone,List<PlayerCard>> zones;
  private String playerName;
  private Integer life;
  private Deck deck;

  public Player(Player player) {
    this.playerName = player.playerName;
    this.life = player.life;
    this.deck = new Deck(player.deck);
    this.zones = player.zones.entrySet().stream()
            .map(zoneEntry ->
              new Pair<>(zoneEntry.getKey(), zoneEntry.getValue().stream()
                .map(PlayerCard::new)
                .collect(Collectors.toList())))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
  }

  public Player(Deck deck) {
    this(deck, "Player1");
  }

  public Player(Deck deck, String playerName) {
    if(deck == null) {
      throw new NullPointerException("Deck must not be null");
    }
    if(StringUtils.isEmpty(playerName)) {
      throw new IllegalArgumentException("PlayerName cannot be empty");
    }

    this.playerName = playerName;
    this.deck = deck;
    this.life = 0;
    resetZones();
  }

  public Deck getDeck() {
    return this.deck;
  }

  public List<PlayerCard> getZone(Zone zone) {
    if(!this.zones.containsKey(zone)) {
      throw new IllegalArgumentException(String.format("Player does not contain zone '%s'", zone.toString()));
    }

    return this.zones.get(zone);
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public Integer getLife() {
    return life;
  }

  public void setPlayerName(String playerName) {
    if(StringUtils.isEmpty(playerName)) {
      throw new IllegalArgumentException("Invalid player name");
    }

    this.playerName = playerName;
  }

  public void setLife(Integer life) {
    if(life == null) {
      throw new IllegalArgumentException("A player's life total must be provided");
    }
    if(life < 1) {
      throw new IllegalArgumentException(String.format("A player's life total cannot startGame at %d", life));
    }

    this.life = life;
  }

  public void dealDamage(int damage) {
    LOGGER.trace("{} took {} damage", playerName, damage);
    this.life -= damage;
  }

  public void shuffle(Zone zone) {
    Collections.shuffle(this.zones.get(zone));
  }

  /**
   * Draws a given number of cards from the player's
   * library to the player's hand
   *
   * @param drawAmount - Number of cards to draw
   */
  public boolean draw(int drawAmount) {
    return moveZone(Zone.LIBRARY, Zone.HAND, drawAmount);
  }

  /**
   * Moves a given number of cards from one zone to another
   *
   * @param from    - The zone to pull from
   * @param to      - The zone to add to
   * @param amount  - The number of cards to move
   */
  public boolean moveZone(Zone from, Zone to, int amount) {
    if(!this.zones.containsKey(from)) {
      throw new IllegalArgumentException(String.format(ZONE_NOT_MANAGED_BY_PLAYER_MSG, from.toString()));
    }
    if(!this.zones.containsKey(to)) {
      throw new IllegalArgumentException(String.format(ZONE_NOT_MANAGED_BY_PLAYER_MSG, to.toString()));
    }

    List fromList = this.zones.get(from);
    List toList = this.zones.get(to);

    for(int x = 0; x < amount; ++x) {
      if(fromList.isEmpty()) {
        return false;
      }

      toList.add(fromList.get(0));
      fromList.remove(0);
    }

    return true;
  }

  /**
   * This will move a card from one player zone to another as long as that card exists in the
   * correct zone, and does not already exist in the destination zone
   *
   * @param from  - Zone to pull the card from
   * @param to    - Zone to add the card to
   * @param card  - The Card to move
   */
  public void moveZone(Zone from, Zone to, PlayerCard card) {
    if(!this.zones.containsKey(from)) {
      throw new IllegalArgumentException(String.format(ZONE_NOT_MANAGED_BY_PLAYER_MSG, from.toString()));
    }
    if(!this.zones.containsKey(to)) {
      throw new IllegalArgumentException(String.format(ZONE_NOT_MANAGED_BY_PLAYER_MSG, to.toString()));
    }
    if(from == to) {
      throw new IllegalArgumentException("The zones provided cannot be the same zone");
    }

    List<PlayerCard> fromList = this.zones.get(from);
    List<PlayerCard> toList = this.zones.get(to);

    if(!fromList.contains(card)) {
      throw new IllegalStateException(String.format("Card %s not in player zone %s", card.getName(), from.toString()));
    }
    if(toList.contains(card)) {
      throw new IllegalStateException(String.format("Card %s already belongs to zone %s", card.getName(), to.toString()));
    }

    fromList.remove(card);
    toList.add(card);
  }

  /**
   * This returns all of the zones back to their default
   * The library will need to be shuffled after each reset
   */
  public void resetZones() {
    this.zones = new EnumMap<>(Zone.class);
    this.zones.put(
            Zone.LIBRARY,
            this.deck.generateFullDeckList()
                    .stream()
                    .map(card -> new PlayerCard(card, playerName))
                    .collect(Collectors.toList()));
    this.zones.put(Zone.HAND, new ArrayList<>());
    this.zones.put(Zone.GRAVEYARD, new ArrayList<>());
    this.zones.put(Zone.EXILE, new ArrayList<>());
    this.zones.put(Zone.BATTLEFIELD, new ArrayList<>());
    this.zones.put(
            Zone.COMMAND,
            this.deck.getFeaturedCards() == null ?
                    new ArrayList<>() :
                    this.deck.getFeaturedCards().stream()
                            .map(card -> new PlayerCard(card, playerName))
                            .collect(Collectors.toList()));
    this.zones.put(Zone.ANTE, new ArrayList<>());
    this.zones.put(Zone.SCRAPYARD, new ArrayList<>());
  }
}
