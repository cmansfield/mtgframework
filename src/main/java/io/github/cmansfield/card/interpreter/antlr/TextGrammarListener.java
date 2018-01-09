// Generated from C:/Users/christopherjman/Documents/mtgframework/src/main/java/io/github/cmansfield/card/interpreter\TextGrammar.g4 by ANTLR 4.7
package io.github.cmansfield.card.interpreter.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TextGrammarParser}.
 */
public interface TextGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#cardText}.
	 * @param ctx the parse tree
	 */
	void enterCardText(TextGrammarParser.CardTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#cardText}.
	 * @param ctx the parse tree
	 */
	void exitCardText(TextGrammarParser.CardTextContext ctx);
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
	 * Enter a parse tree produced by {@link TextGrammarParser#textOther}.
	 * @param ctx the parse tree
	 */
	void enterTextOther(TextGrammarParser.TextOtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#textOther}.
	 * @param ctx the parse tree
	 */
	void exitTextOther(TextGrammarParser.TextOtherContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#cost}.
	 * @param ctx the parse tree
	 */
	void enterCost(TextGrammarParser.CostContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#cost}.
	 * @param ctx the parse tree
	 */
	void exitCost(TextGrammarParser.CostContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#costOther}.
	 * @param ctx the parse tree
	 */
	void enterCostOther(TextGrammarParser.CostOtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#costOther}.
	 * @param ctx the parse tree
	 */
	void exitCostOther(TextGrammarParser.CostOtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#costRequirement}.
	 * @param ctx the parse tree
	 */
	void enterCostRequirement(TextGrammarParser.CostRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#costRequirement}.
	 * @param ctx the parse tree
	 */
	void exitCostRequirement(TextGrammarParser.CostRequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#costPrimary}.
	 * @param ctx the parse tree
	 */
	void enterCostPrimary(TextGrammarParser.CostPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#costPrimary}.
	 * @param ctx the parse tree
	 */
	void exitCostPrimary(TextGrammarParser.CostPrimaryContext ctx);
}