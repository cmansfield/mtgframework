package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.exceptions.GameException;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.simulator.actions.Action;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.validator.DeckValidator;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.simulator.turn.Phase;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Deque;


public class Game {
  private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

  private Deque<Player> players;
  private Deque<Action> stack;
  private Phase phase;

  // TODO - Revisit this to make sure this is where I want to store this
  private boolean activePlayerPlayedLand;

  private Game() {
    this.phase = new BeginningPhase();
    this.activePlayerPlayedLand = false;
    this.stack = new LinkedList<>();
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public boolean hasActivePlayerPlayedLand() {
    return this.activePlayerPlayedLand;
  }

  public void setActivePlayerPlayedLand(boolean hasPlayed) {
    this.activePlayerPlayedLand = hasPlayed;
  }

  public Player getActivePlayer() {
    return this.players.getFirst();
  }

  public int getPlayerCount() {
    return this.players.size();
  }

  public void removePlayer(Player player) {
    if(player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    if(!this.players.contains(player)) {
      throw new IllegalArgumentException("Player does not exist in the list of players");
    }

    this.players.remove(player);
  }

  public void nextPlayersTurn() {
    this.players.addLast(this.players.pollFirst());
    this.activePlayerPlayedLand = false;
  }

  public void addToStack(Action action) {
    this.stack.add(action);
  }

  public void resolveStack() throws GameException {
    Action action;

    while(!this.stack.isEmpty()) {
      action = stack.pollLast();
      action.execute();
    }
  }

  public void perform() throws GameException {
    this.phase.perform(this);
  }


  // into a valid starting state
  // This class uses the builder pattern to help get the game manager
  public static class GameBuilder {
    private LinkedList<Player> players;
    private Format format;

    public GameBuilder() {
      this.players = new LinkedList<>();
    }

    public Game.GameBuilder player(Player player) {
      if(player == null) {
        throw new IllegalArgumentException("Player object cannot be null");
      }

      if(this.players.isEmpty()) {
        this.format = player.getDeck().getFormat();
      }
      else if(this.format != player.getDeck().getFormat()) {
        throw new IllegalArgumentException("Players entering the game must be playing the same format");
      }

      if(this.format == Format.COMMANDER) {
        player.setLife(GameConstants.STARTING_LIFE_COMMANDER.value());
      }
      else {
        player.setLife(GameConstants.STARTING_LIFE_STANDARD.value());
      }

      player.setPlayerName(String.format("Player%d", this.players.size() + 1));

      this.players.add(player);

      return this;
    }

    public Game build() {
      Game game = new Game();

      if(this.players.size() < 2) {
        throw new IllegalStateException("There must be at least two players to play a game");
      }

      this.players.forEach(player -> {
        try {
          DeckValidator.isFormatCompliant(player.getDeck());
        }
        catch (Exception e) {
          throw new RuntimeException("Player has an illegal deck for this format", e);    // NOSONAR
        }

        player.shuffle(Zone.LIBRARY);
      });

      // Shuffle the players to randomize who goes first
      Collections.shuffle(this.players);

      setUp();
      game.players = this.players;

      return game;
    }

    private void setUp() {
      // Have the players draw their hands
      this.players.forEach(player -> player.draw(GameConstants.STARTING_HAND_SIZE.value()));
    }
  }
}
