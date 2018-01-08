// Generated from C:/Users/christopherjman/Documents/mtgframework/src/main/java/io/github/cmansfield/card/interpreter\TextGrammar.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TextGrammarParser}.
 */
public interface TextGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(TextGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(TextGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextGrammarParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(TextGrammarParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextGrammarParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(TextGrammarParser.MessageContext ctx);
}