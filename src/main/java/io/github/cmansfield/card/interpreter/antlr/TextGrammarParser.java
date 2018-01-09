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
		T__0=1, T__1=2, ACTION_KEYWORD=3, ABILITY_KEYWORD=4, MODIFIER=5, NUMBER=6, 
		WORD=7, NEWLINE=8, COMMA=9, WHITESPCE=10;
	public static final int
		RULE_text = 0, RULE_commonAbility = 1, RULE_uniqueAbility = 2, RULE_counters = 3;
	public static final String[] ruleNames = {
		"text", "commonAbility", "uniqueAbility", "counters"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'/'", null, null, null, null, null, null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "ACTION_KEYWORD", "ABILITY_KEYWORD", "MODIFIER", "NUMBER", 
		"WORD", "NEWLINE", "COMMA", "WHITESPCE"
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ACTION_KEYWORD) | (1L << ABILITY_KEYWORD) | (1L << MODIFIER) | (1L << WORD) | (1L << NEWLINE) | (1L << COMMA) | (1L << WHITESPCE))) != 0) );
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
		public List<TerminalNode> NEWLINE() { return getTokens(TextGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TextGrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> ABILITY_KEYWORD() { return getTokens(TextGrammarParser.ABILITY_KEYWORD); }
		public TerminalNode ABILITY_KEYWORD(int i) {
			return getToken(TextGrammarParser.ABILITY_KEYWORD, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TextGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TextGrammarParser.COMMA, i);
		}
		public List<TerminalNode> WHITESPCE() { return getTokens(TextGrammarParser.WHITESPCE); }
		public TerminalNode WHITESPCE(int i) {
			return getToken(TextGrammarParser.WHITESPCE, i);
		}
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(14);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABILITY_KEYWORD) | (1L << NEWLINE) | (1L << COMMA) | (1L << WHITESPCE))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(17); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(19);
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
		public List<TerminalNode> ACTION_KEYWORD() { return getTokens(TextGrammarParser.ACTION_KEYWORD); }
		public TerminalNode ACTION_KEYWORD(int i) {
			return getToken(TextGrammarParser.ACTION_KEYWORD, i);
		}
		public List<TerminalNode> ABILITY_KEYWORD() { return getTokens(TextGrammarParser.ABILITY_KEYWORD); }
		public TerminalNode ABILITY_KEYWORD(int i) {
			return getToken(TextGrammarParser.ABILITY_KEYWORD, i);
		}
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
		public List<TerminalNode> NEWLINE() { return getTokens(TextGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TextGrammarParser.NEWLINE, i);
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
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(28);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ACTION_KEYWORD:
					{
					setState(21);
					match(ACTION_KEYWORD);
					}
					break;
				case ABILITY_KEYWORD:
					{
					setState(22);
					match(ABILITY_KEYWORD);
					}
					break;
				case WORD:
					{
					setState(23);
					match(WORD);
					}
					break;
				case MODIFIER:
					{
					setState(24);
					counters();
					}
					break;
				case WHITESPCE:
					{
					setState(25);
					match(WHITESPCE);
					}
					break;
				case COMMA:
					{
					setState(26);
					match(COMMA);
					}
					break;
				case NEWLINE:
					{
					setState(27);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ACTION_KEYWORD) | (1L << ABILITY_KEYWORD) | (1L << MODIFIER) | (1L << WORD) | (1L << NEWLINE) | (1L << COMMA) | (1L << WHITESPCE))) != 0) );
			setState(32);
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
			setState(34);
			match(MODIFIER);
			setState(35);
			match(NUMBER);
			setState(36);
			match(T__1);
			setState(37);
			match(MODIFIER);
			setState(38);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f+\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\3\6\3\22\n\3\r\3\16"+
		"\3\23\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4\37\n\4\r\4\16\4 \3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\2\6\2\4\6\b\2\3\4\2\6\6\n\f\2\60\2\f\3"+
		"\2\2\2\4\21\3\2\2\2\6\36\3\2\2\2\b$\3\2\2\2\n\r\5\4\3\2\13\r\5\6\4\2\f"+
		"\n\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\3\3"+
		"\2\2\2\20\22\t\2\2\2\21\20\3\2\2\2\22\23\3\2\2\2\23\21\3\2\2\2\23\24\3"+
		"\2\2\2\24\25\3\2\2\2\25\26\7\n\2\2\26\5\3\2\2\2\27\37\7\5\2\2\30\37\7"+
		"\6\2\2\31\37\7\t\2\2\32\37\5\b\5\2\33\37\7\f\2\2\34\37\7\13\2\2\35\37"+
		"\7\n\2\2\36\27\3\2\2\2\36\30\3\2\2\2\36\31\3\2\2\2\36\32\3\2\2\2\36\33"+
		"\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2"+
		"!\"\3\2\2\2\"#\7\3\2\2#\7\3\2\2\2$%\7\7\2\2%&\7\b\2\2&\'\7\4\2\2\'(\7"+
		"\7\2\2()\7\b\2\2)\t\3\2\2\2\7\f\16\23\36 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}