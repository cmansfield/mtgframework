package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.game.events.GameEventHandler;
import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.actions.Action;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.validator.DeckValidator;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.simulator.turn.Phase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class Game {
  private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

  private GameEventHandler gameEventHandler;
  private Deque<Player> players;
  private Deque<Action> stack;
  private Phase phase;

  // TODO - Revisit this to make sure this is where I want to store this
  private boolean activePlayerPlayedLand;

  private Game() {
    this.phase = new BeginningPhase();
    this.activePlayerPlayedLand = false;
    this.stack = new LinkedList<>();
    this.gameEventHandler = new GameEventHandler();
  }

  // TODO - Finish this copy constructor
  public Game(Game game) {
    this();
    this.players = game.players.stream()
            .map(Player::new)
            .collect(Collectors.toCollection(LinkedList::new));
    this.stack = game.stack.stream()
            .map(action -> action.copy(this))
            .collect(Collectors.toCollection(LinkedList::new));
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

  public Player getActivePlayer() {
    return players.getFirst();
  }

  public int getPlayerCount() {
    return players.size();
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

  public void addToStack(Action action) {
    stack.add(action);
  }

  public void resolveStack() {
    Action action;

    while(!stack.isEmpty()) {
      action = stack.pollLast();
      action.execute();
    }
  }

  public void perform() {
    phase.perform(this);
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
