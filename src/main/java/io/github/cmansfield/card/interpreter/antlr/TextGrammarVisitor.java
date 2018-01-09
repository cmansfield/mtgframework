// Generated from C:/Users/christopherjman/Documents/mtgframework/src/main/java/io/github/cmansfield/card/interpreter\TextGrammar.g4 by ANTLR 4.7
package io.github.cmansfield.card.interpreter.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TextGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TextGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#cardText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardText(TextGrammarParser.CardTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#commonAbility}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonAbility(TextGrammarParser.CommonAbilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#uniqueAbility}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueAbility(TextGrammarParser.UniqueAbilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#textOther}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextOther(TextGrammarParser.TextOtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#counters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounters(TextGrammarParser.CountersContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#cost}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCost(TextGrammarParser.CostContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#costOther}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCostOther(TextGrammarParser.CostOtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#costRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCostRequirement(TextGrammarParser.CostRequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TextGrammarParser#costPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCostPrimary(TextGrammarParser.CostPrimaryContext ctx);
}