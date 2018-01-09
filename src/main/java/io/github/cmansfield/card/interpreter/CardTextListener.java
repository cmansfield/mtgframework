package io.github.cmansfield.card.interpreter;

import io.github.cmansfield.card.interpreter.antlr.TextGrammarBaseListener;
import io.github.cmansfield.card.interpreter.antlr.TextGrammarParser;
import io.github.cmansfield.card.ability.CardAbility;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
  public void enterText(TextGrammarParser.TextContext ctx) {

    LOGGER.debug("Enter Text");
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

    List<TextGrammarParser.CountersContext> counters = ctx.counters();
    List<TerminalNode> words = ctx.WORD();
    LOGGER.debug("Enter Unique Ability - {}", ctx.getText().replaceAll("\n", " "));
  }

  @Override
  public void enterCounters(TextGrammarParser.CountersContext ctx) {

    List<TerminalNode> modifiers = ctx.MODIFIER();
    List<TerminalNode> numbers = ctx.NUMBER();
    LOGGER.debug("Enter Counters - {}", ctx.getText());
  }
}
