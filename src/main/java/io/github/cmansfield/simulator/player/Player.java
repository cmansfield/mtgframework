package io.github.cmansfield.simulator.player;

import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.deck.Deck;

import java.util.stream.Collectors;
import java.util.*;


public class Player {
  private Map<Zone,List<PlayerCard>> zones;
  private String playerName;
  private Integer life;
  private Deck deck;

  public Player(Deck deck) {
    if(deck == null) {
      throw new NullPointerException("Deck must not be null");
    }

    this.life = 0;
    this.deck = deck;
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

  public void setPlayerName(String playerName) {
    if(playerName == null || playerName.length() < 1) {
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

  public void damagePlayer(int damage) {
    this.life -= damage;

    if(this.life < 1) {
      throw new IllegalStateException("The player has lost the game!");
    }
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
  public void draw(int drawAmount) {
    moveZone(Zone.LIBRARY, Zone.HAND, drawAmount);
  }

  /**
   * Moves a given number of cards from one zone to another
   *
   * @param from    - The zone to pull from
   * @param to      - The zone to add to
   * @param amount  - The number of cards to move
   */
  public void moveZone(Zone from, Zone to, int amount) {
    if(!this.zones.containsKey(from)) {
      throw new IllegalArgumentException(String.format("Zone '%s' is not managed by the player", from.toString()));
    }
    if(!this.zones.containsKey(to)) {
      throw new IllegalArgumentException(String.format("Zone '%s' is not managed by the player", to.toString()));
    }

    List fromList = this.zones.get(from);
    List toList = this.zones.get(to);

    for(int x = 0; x < amount; ++x) {
      if(fromList.isEmpty()) {
        throw new IllegalStateException(String.format("No more cards to pull from %s", from.toString()));
      }

      toList.add(fromList.get(0));
      fromList.remove(0);
    }
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
      throw new IllegalArgumentException(String.format("Zone '%s' is not managed by the player", from.toString()));
    }
    if(!this.zones.containsKey(to)) {
      throw new IllegalArgumentException(String.format("Zone '%s' is not managed by the player", to.toString()));
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
            this.deck.getOriginalCards()
                    .stream()
                    .filter(card -> {
                      if(deck.getFeaturedCard() == null) {
                        return false;
                      }

                      return !deck.getFeaturedCard().getName().equalsIgnoreCase(card.getName());
                    })
                    .map(card -> new PlayerCard(card.getCardPojo(), this))
                    .collect(Collectors.toList()));
    this.zones.put(Zone.HAND, new ArrayList<>());
    this.zones.put(Zone.GRAVEYARD, new ArrayList<>());
    this.zones.put(Zone.EXILE, new ArrayList<>());
    this.zones.put(Zone.BATTLEFIELD, new ArrayList<>());
    this.zones.put(
            Zone.COMMAND,
            this.deck.getFeaturedCard() == null ?
                    new ArrayList<>() :
                    Collections.singletonList(new PlayerCard(this.deck.getFeaturedCard().getCardPojo(), this)));
    this.zones.put(Zone.ANTE, new ArrayList<>());
    this.zones.put(Zone.SCRAPYARD, new ArrayList<>());
  }
}
