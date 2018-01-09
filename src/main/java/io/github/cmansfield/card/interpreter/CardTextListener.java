package io.github.cmansfield.card.interpreter;

import io.github.cmansfield.card.interpreter.antlr.TextGrammarBaseListener;
import io.github.cmansfield.card.interpreter.antlr.TextGrammarParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class CardTextListener extends TextGrammarBaseListener {
  @Override
  public void enterText(TextGrammarParser.TextContext ctx) {
    System.out.printf("Enter Text%n");
  }

  @Override
  public void enterCommonAbility(TextGrammarParser.CommonAbilityContext ctx) {

    TerminalNode word = ctx.WORD();
    System.out.printf("Enter Common Ability%n");
  }

  @Override
  public void enterUniqueAbility(TextGrammarParser.UniqueAbilityContext ctx) {

    List<TextGrammarParser.CountersContext> counters = ctx.counters();
    List<TerminalNode> words = ctx.WORD();
    System.out.printf("Enter Common Ability%n");
  }

  @Override
  public void enterCounters(TextGrammarParser.CountersContext ctx) {

    List<TerminalNode> modifiers = ctx.MODIFIER();
    List<TerminalNode> numbers = ctx.NUMBER();
    System.out.printf("Enter Common Ability%n");
  }
}
