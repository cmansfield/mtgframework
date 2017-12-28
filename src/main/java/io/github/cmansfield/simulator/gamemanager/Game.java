package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.actions.collections.ActionStack;
import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.game.events.GameEventHandler;
import io.github.cmansfield.simulator.player.combat.Combat;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.validator.DeckValidator;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.simulator.turn.Phase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.util.*;


public class Game {
  private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

  private GameEventHandler gameEventHandler;
  private Deque<Player> players;
  private ActionStack playerStack;
  private ActionStack gameStack;
  private Phase phase;

  // TODO - Revisit this to make sure this is where I want to store this
  private boolean activePlayerPlayedLand;
  private Combat combat;

  private Game() {
    this.gameEventHandler = new GameEventHandler();
    this.activePlayerPlayedLand = false;
    this.playerStack = new ActionStack();
    this.gameStack = new ActionStack();
    this.phase = new BeginningPhase(this);
  }

  // TODO - Finish this copy constructor
  public Game(Game game) {
    this.players = game.players.stream()
            .map(Player::new)
            .collect(Collectors.toCollection(LinkedList::new));
    this.playerStack = new ActionStack(game.playerStack, this);
    this.gameStack = new ActionStack(game.gameStack, this);
//    this.phase =
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public GameEventHandler getEventHandler() {
    return gameEventHandler;
  }

  public void setActivePlayerPlayedLand(boolean hasPlayed) {
    this.activePlayerPlayedLand = hasPlayed;
  }

  public boolean hasActivePlayerPlayedLand() {
    return activePlayerPlayedLand;
  }

  public void setCombat(Combat combat) {
    this.combat = combat;
  }

  public Combat getCombat() {
    return combat;
  }

  public ActionStack getGameStack() {
    return gameStack;
  }

  public ActionStack getPlayerStack() {
    return playerStack;
  }

  public Player getActivePlayer() {
    return players.getFirst();
  }

  public int getPlayerCount() {
    return players.size();
  }

  public List<Player> getPlayers() {
    return new ArrayList<>(players);
  }

  public void removePlayer(Player player) {
    if(player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    if(!players.contains(player)) {
      throw new IllegalArgumentException("Player does not exist in the list of players");
    }

    players.remove(player);
  }

  public void nextPlayersTurn() {
    players.addLast(players.pollFirst());
    activePlayerPlayedLand = false;
  }

  public void perform() {
    phase.perform();
  }



  // into a valid starting state
  // This class uses the builder pattern to help get the game manager
  public static class GameBuilder {
    private LinkedList<Player> players;
    private Format format;

    public GameBuilder() {
      players = new LinkedList<>();
    }

    public Game.GameBuilder player(Player player) {
      if(player == null) {
        throw new IllegalArgumentException("Player object cannot be null");
      }

      if(players.isEmpty()) {
        format = player.getDeck().getFormat();
      }
      else if(format != player.getDeck().getFormat()) {
        throw new IllegalArgumentException("Players entering the game must be playing the same format");
      }

      if(format == Format.COMMANDER) {
        player.setLife(GameConstants.STARTING_LIFE_COMMANDER.value());
      }
      else {
        player.setLife(GameConstants.STARTING_LIFE_STANDARD.value());
      }

      players.add(player);

      return this;
    }

    public Game build() {
      Game game = new Game();

      if(players.size() < 2) {
        throw new IllegalStateException("There must be at least two players to play a game");
      }

      players.forEach(player -> {
        try {
          DeckValidator.isFormatCompliant(player.getDeck());
        }
        catch (Exception e) {
          throw new RuntimeException("Player has an illegal deck for this format", e);    // NOSONAR
        }

        player.shuffle(Zone.LIBRARY);
      });

      if(players.size() != players.stream()
              .map(Player::getPlayerName)
              .collect(Collectors.toSet()).size()) {
        throw new IllegalStateException("Players within a game must not have the same name");
      }

      // Shuffle the players to randomize who goes first
      Collections.shuffle(players);

      setUp();
      game.players = players;

      return game;
    }

    private void setUp() {
      // Have the players draw their hands
      players.forEach(player -> player.draw(GameConstants.STARTING_HAND_SIZE.value()));
    }
  }
}
