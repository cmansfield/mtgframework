package io.github.cmansfield.simulator.game.events.constants;


import io.github.cmansfield.constants.Constant;

public enum GameEventType {
  PLAYER_DEATH("death"),
  PLAYER_LOSS("loss"),
  TIE("tie"),
  END_TURN("end turn"),
  END_PHASE("end phase");

  private final String value;

  GameEventType(String value) {
    this.value = value;
  }

  public static boolean contains(final String value) {
    return Constant.contains(GameEventType.class, value);
  }

  public static GameEventType find(final String value) {
    return Constant.find(GameEventType.class, value);
  }

  @Override
  public String toString() {
    return this.value;
  }
}
