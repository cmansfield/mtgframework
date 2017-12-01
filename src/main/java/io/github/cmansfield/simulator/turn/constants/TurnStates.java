package io.github.cmansfield.simulator.turn.constants;

// I may not use this enum, undecided
public enum TurnStates {
  BEGINNING_PHASE("beginning"),
  UNTAP_STEP("untap step"),
  UPKEEP_STEP("upkeep"),
  DRAW_STEP("draw step"),
  PRECOMBAT_MAIN_PHASE("precombat main phase"),
  COMBAT_PHASE("combat"),
  BEGINNING_OF_COMBAT_STEP("beginning of combat"),
  DECLARE_ATTACKERS_STEP("declare attackers"),
  DECLARE_BLOCKERS_STEP("declare blockers"),
  COMBAT_DAMAGE_STEP("combat damage"),
  END_OF_COMBAT_STEP("end of combat"),
  POSTCOMBAT_MAIN_PHASE("postcombat main phase"),
  ENDING_PHASE("ending"),
  END_STEP("end step"),
  CLEANUP_STEP("cleanup step");

  private final String value;

  TurnStates(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
