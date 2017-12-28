package io.github.cmansfield.simulator.player.combat;

import java.util.ArrayList;
import java.util.List;


public class Combat {
  private List<Combatant> attackers;
  private List<Combatant> defenders;

  public Combat() {
    this.attackers = new ArrayList<>();
    this.defenders = new ArrayList<>();
  }

  public void addAttacker(Combatant combatant) {
    // TODO - check to make sure combatant is a valid attacker
    attackers.add(combatant);
  }

  public void addDefender(Combatant combatant) {
    // TODO - check to make sure combatant is a valid defender
    defenders.add(combatant);
  }

  public void resolveCombat() {
    // TODO - complete
  }
}
