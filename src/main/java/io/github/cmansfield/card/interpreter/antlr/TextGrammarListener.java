// Generated from C:/Users/christopherjman/Documents/mtgframework/src/main/java/io/github/cmansfield/card/interpreter\TextGrammar.g4 by ANTLR 4.7
package io.github.cmansfield.card.interpreter.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TextGrammarParser}.
 */
public interface TextGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(TextGrammarParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(TextGrammarParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#commonAbility}.
	 * @param ctx the parse tree
	 */
	void enterCommonAbility(TextGrammarParser.CommonAbilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#commonAbility}.
	 * @param ctx the parse tree
	 */
	void exitCommonAbility(TextGrammarParser.CommonAbilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#uniqueAbility}.
	 * @param ctx the parse tree
	 */
	void enterUniqueAbility(TextGrammarParser.UniqueAbilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#uniqueAbility}.
	 * @param ctx the parse tree
	 */
	void exitUniqueAbility(TextGrammarParser.UniqueAbilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#counters}.
	 * @param ctx the parse tree
	 */
	void enterCounters(TextGrammarParser.CountersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#counters}.
	 * @param ctx the parse tree
	 */
	void exitCounters(TextGrammarParser.CountersContext ctx);
}