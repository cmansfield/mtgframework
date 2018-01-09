// Generated from C:/Users/christopherjman/Documents/mtgframework/src/main/java/io/github/cmansfield/card/interpreter\TextGrammar.g4 by ANTLR 4.7
package io.github.cmansfield.card.interpreter.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TextGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, MODIFIER=3, NUMBER=4, WORD=5, NEWLINE=6, COMMA=7, WHITESPCE=8;
	public static final int
		RULE_text = 0, RULE_commonAbility = 1, RULE_uniqueAbility = 2, RULE_counters = 3;
	public static final String[] ruleNames = {
		"text", "commonAbility", "uniqueAbility", "counters"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'/'", null, null, null, null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "MODIFIER", "NUMBER", "WORD", "NEWLINE", "COMMA", "WHITESPCE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TextGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TextGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TextContext extends ParserRuleContext {
		public List<CommonAbilityContext> commonAbility() {
			return getRuleContexts(CommonAbilityContext.class);
		}
		public CommonAbilityContext commonAbility(int i) {
			return getRuleContext(CommonAbilityContext.class,i);
		}
		public List<UniqueAbilityContext> uniqueAbility() {
			return getRuleContexts(UniqueAbilityContext.class);
		}
		public UniqueAbilityContext uniqueAbility(int i) {
			return getRuleContext(UniqueAbilityContext.class,i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(10);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(8);
					commonAbility();
					}
					break;
				case 2:
					{
					setState(9);
					uniqueAbility();
					}
					break;
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODIFIER) | (1L << WORD) | (1L << COMMA) | (1L << WHITESPCE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommonAbilityContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(TextGrammarParser.WORD, 0); }
		public TerminalNode NEWLINE() { return getToken(TextGrammarParser.NEWLINE, 0); }
		public CommonAbilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonAbility; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCommonAbility(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCommonAbility(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCommonAbility(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommonAbilityContext commonAbility() throws RecognitionException {
		CommonAbilityContext _localctx = new CommonAbilityContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_commonAbility);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(WORD);
			setState(15);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniqueAbilityContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(TextGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(TextGrammarParser.WORD, i);
		}
		public List<CountersContext> counters() {
			return getRuleContexts(CountersContext.class);
		}
		public CountersContext counters(int i) {
			return getRuleContext(CountersContext.class,i);
		}
		public List<TerminalNode> WHITESPCE() { return getTokens(TextGrammarParser.WHITESPCE); }
		public TerminalNode WHITESPCE(int i) {
			return getToken(TextGrammarParser.WHITESPCE, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TextGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TextGrammarParser.COMMA, i);
		}
		public UniqueAbilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uniqueAbility; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterUniqueAbility(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitUniqueAbility(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitUniqueAbility(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UniqueAbilityContext uniqueAbility() throws RecognitionException {
		UniqueAbilityContext _localctx = new UniqueAbilityContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_uniqueAbility);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(21);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(17);
					match(WORD);
					}
					break;
				case MODIFIER:
					{
					setState(18);
					counters();
					}
					break;
				case WHITESPCE:
					{
					setState(19);
					match(WHITESPCE);
					}
					break;
				case COMMA:
					{
					setState(20);
					match(COMMA);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODIFIER) | (1L << WORD) | (1L << COMMA) | (1L << WHITESPCE))) != 0) );
			setState(25);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CountersContext extends ParserRuleContext {
		public List<TerminalNode> MODIFIER() { return getTokens(TextGrammarParser.MODIFIER); }
		public TerminalNode MODIFIER(int i) {
			return getToken(TextGrammarParser.MODIFIER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(TextGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(TextGrammarParser.NUMBER, i);
		}
		public CountersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_counters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCounters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCounters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCounters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CountersContext counters() throws RecognitionException {
		CountersContext _localctx = new CountersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_counters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(MODIFIER);
			setState(28);
			match(NUMBER);
			setState(29);
			match(T__1);
			setState(30);
			match(MODIFIER);
			setState(31);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n$\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\6\4\30\n\4\r\4\16\4\31\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\2"+
		"\6\2\4\6\b\2\2\2%\2\f\3\2\2\2\4\20\3\2\2\2\6\27\3\2\2\2\b\35\3\2\2\2\n"+
		"\r\5\4\3\2\13\r\5\6\4\2\f\n\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2"+
		"\2\2\16\17\3\2\2\2\17\3\3\2\2\2\20\21\7\7\2\2\21\22\7\b\2\2\22\5\3\2\2"+
		"\2\23\30\7\7\2\2\24\30\5\b\5\2\25\30\7\n\2\2\26\30\7\t\2\2\27\23\3\2\2"+
		"\2\27\24\3\2\2\2\27\25\3\2\2\2\27\26\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2"+
		"\2\31\32\3\2\2\2\32\33\3\2\2\2\33\34\7\3\2\2\34\7\3\2\2\2\35\36\7\5\2"+
		"\2\36\37\7\6\2\2\37 \7\4\2\2 !\7\5\2\2!\"\7\6\2\2\"\t\3\2\2\2\6\f\16\27"+
		"\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}