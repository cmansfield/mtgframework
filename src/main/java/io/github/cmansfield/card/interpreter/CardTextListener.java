package io.github.cmansfield.card.interpreter;

import io.github.cmansfield.card.interpreter.antlr.TextGrammarBaseListener;
import io.github.cmansfield.card.interpreter.antlr.TextGrammarParser;
import io.github.cmansfield.card.ability.CardAbility;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;


public class CardTextListener extends TextGrammarBaseListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(CardTextListener.class);
  private List<CardAbility> cardAbilities;

  public CardTextListener() {
    cardAbilities = new ArrayList<>();
  }

  public List<CardAbility> getCardAbilities() {
    return cardAbilities;
  }

  @Override
  public void enterCardText(TextGrammarParser.CardTextContext ctx) {

    LOGGER.debug("Enter Card Text");
  }

  @Override
  public void enterCommonAbility(TextGrammarParser.CommonAbilityContext ctx) {

    cardAbilities.addAll(ctx.ABILITY_KEYWORD().stream()
            .map(ability -> new CardAbility(ability.getText()))
            .collect(Collectors.toList()));

    LOGGER.debug("Enter Common Ability - {}", ctx.getText().replaceAll("\n", ", "));
  }

  @Override
  public void enterUniqueAbility(TextGrammarParser.UniqueAbilityContext ctx) {

    LOGGER.debug("Enter Unique Ability - {}", ctx.getText().replaceAll("\n", " "));
  }

  @Override
  public void enterCounters(TextGrammarParser.CountersContext ctx) {

    List<TerminalNode> modifiers = ctx.MODIFIER();
    List<TerminalNode> numbers = ctx.NUMBER();
    LOGGER.debug("Enter Counters - {}", ctx.getText());
  }

  @Override
  public void enterCost(TextGrammarParser.CostContext ctx) {

    LOGGER.debug("Enter Cost - {}", ctx.getText());
  }
}
