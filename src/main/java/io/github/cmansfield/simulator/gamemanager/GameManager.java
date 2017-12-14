package io.github.cmansfield.simulator.gamemanager;

import io.github.cmansfield.simulator.actions.Action;
import io.github.cmansfield.simulator.constants.Zone;
import io.github.cmansfield.simulator.gamemanager.constants.GameConstants;
import io.github.cmansfield.simulator.turn.BeginningPhase;
import io.github.cmansfield.validator.DeckValidator;
import io.github.cmansfield.simulator.player.Player;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.simulator.turn.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public final class GameManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);

  private Deque<Player> players;
  private Deque<Action> stack;
  private Phase phase;

  // TODO - Revisit this to make sure this is where I want to store this
  private boolean activePlayerPlayedLand;

  private GameManager() {
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

  public void startGame() {
    final int maxNumberOfTurns = 10000;
    for(int i = 0; i < maxNumberOfTurns; ++i) {
      try {
        perform();
      }
      catch(Exception e) {
        // TODO - improve this logic so not all exceptions are a game loss
        LOGGER.info("{} lost the game", this.getActivePlayer().getPlayerName());
        LOGGER.info(e.getMessage());

        this.players.remove(getActivePlayer());

        if(this.players.size() < 2) {
          break;
        }
      }
    }

    LOGGER.info("%n{} won!", getActivePlayer().getPlayerName());
    LOGGER.info("End of Game");
  }

  public void nextPlayersTurn() {
    this.players.addLast(this.players.pollFirst());
    this.activePlayerPlayedLand = false;
  }

  public void addToStack(Action action) {
    this.stack.add(action);
  }

  public void resolveStack() {
    Action action;

    while(!this.stack.isEmpty()) {
      action = stack.pollLast();
      action.execute();
    }
  }

  private void perform() {
    this.phase.perform(this);
  }


  // into a valid starting state
  // This class uses the builder pattern to help get the game manager
  public static class GameManagerBuilder {


    private LinkedList<Player> players;
    private Format format;

    public GameManagerBuilder() {
      this.players = new LinkedList<>();
    }

    public GameManagerBuilder player(Player player) {
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

    public GameManager build() {
      GameManager gm = new GameManager();

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
      gm.players = this.players;

      return gm;
    }

    private void setUp() {
      // Have the players draw their hands
      this.players.forEach(player -> player.draw(GameConstants.STARTING_HAND_SIZE.value()));
    }
  }
}
