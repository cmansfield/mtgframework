package io.github.cmansfield.simulator.gamemanager.constants;

public enum GameConstants {
  STARTING_HAND_SIZE(7),
  STARTING_LIFE_STANDARD(20),
  STARTING_LIFE_COMMANDER(40),
  MAX_HAND_SIZE(7);

  private final int value;

  GameConstants(int value) {
    this.value = value;
  }

  public int value() {
    return this.value;
  }
}
