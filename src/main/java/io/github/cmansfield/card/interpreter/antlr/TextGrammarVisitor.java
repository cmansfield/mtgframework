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
	 * Visit a parse tree produced by {@link TextGrammarParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(TextGrammarParser.TextContext ctx);
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
	 * Visit a parse tree produced by {@link TextGrammarParser#counters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounters(TextGrammarParser.CountersContext ctx);
}