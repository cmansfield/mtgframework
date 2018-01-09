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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, OTHER_IDENTIFIER=7, COLOR_IDENTIFIER=8, 
		ACTION_KEYWORD=9, ABILITY_KEYWORD=10, MODIFIER=11, NUMBER=12, WORD=13, 
		NEWLINE=14, WHITESPCE=15;
	public static final int
		RULE_cardText = 0, RULE_commonAbility = 1, RULE_uniqueAbility = 2, RULE_textOther = 3, 
		RULE_counters = 4, RULE_cost = 5, RULE_costOther = 6, RULE_costRequirement = 7, 
		RULE_costPrimary = 8;
	public static final String[] ruleNames = {
		"cardText", "commonAbility", "uniqueAbility", "textOther", "counters", 
		"cost", "costOther", "costRequirement", "costPrimary"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'.'", "'/'", "':'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "OTHER_IDENTIFIER", "COLOR_IDENTIFIER", 
		"ACTION_KEYWORD", "ABILITY_KEYWORD", "MODIFIER", "NUMBER", "WORD", "NEWLINE", 
		"WHITESPCE"
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
	public static class CardTextContext extends ParserRuleContext {
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
		public CardTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCardText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCardText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCardText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CardTextContext cardText() throws RecognitionException {
		CardTextContext _localctx = new CardTextContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cardText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(20);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(18);
					commonAbility();
					}
					break;
				case 2:
					{
					setState(19);
					uniqueAbility();
					}
					break;
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << ACTION_KEYWORD) | (1L << ABILITY_KEYWORD) | (1L << MODIFIER) | (1L << WORD) | (1L << NEWLINE) | (1L << WHITESPCE))) != 0) );
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
			setState(25); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(24);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABILITY_KEYWORD) | (1L << NEWLINE) | (1L << WHITESPCE))) != 0)) ) {
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
				setState(27); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(29);
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
		public TextOtherContext textOther() {
			return getRuleContext(TextOtherContext.class,0);
		}
		public CostContext cost() {
			return getRuleContext(CostContext.class,0);
		}
		public TerminalNode WHITESPCE() { return getToken(TextGrammarParser.WHITESPCE, 0); }
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
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case ACTION_KEYWORD:
			case ABILITY_KEYWORD:
			case MODIFIER:
			case WORD:
			case NEWLINE:
			case WHITESPCE:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				textOther();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				cost();
				setState(34);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(33);
					match(WHITESPCE);
					}
					break;
				}
				setState(36);
				textOther();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TextOtherContext extends ParserRuleContext {
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
		public List<TerminalNode> NEWLINE() { return getTokens(TextGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TextGrammarParser.NEWLINE, i);
		}
		public TextOtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textOther; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterTextOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitTextOther(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitTextOther(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextOtherContext textOther() throws RecognitionException {
		TextOtherContext _localctx = new TextOtherContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_textOther);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(47);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ACTION_KEYWORD:
					{
					setState(40);
					match(ACTION_KEYWORD);
					}
					break;
				case ABILITY_KEYWORD:
					{
					setState(41);
					match(ABILITY_KEYWORD);
					}
					break;
				case WORD:
					{
					setState(42);
					match(WORD);
					}
					break;
				case MODIFIER:
					{
					setState(43);
					counters();
					}
					break;
				case WHITESPCE:
					{
					setState(44);
					match(WHITESPCE);
					}
					break;
				case T__0:
					{
					setState(45);
					match(T__0);
					}
					break;
				case NEWLINE:
					{
					setState(46);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ACTION_KEYWORD) | (1L << ABILITY_KEYWORD) | (1L << MODIFIER) | (1L << WORD) | (1L << NEWLINE) | (1L << WHITESPCE))) != 0) );
			setState(51);
			match(T__1);
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(52);
				match(NEWLINE);
				}
				break;
			}
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
		enterRule(_localctx, 8, RULE_counters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(MODIFIER);
			setState(56);
			match(NUMBER);
			setState(57);
			match(T__2);
			setState(58);
			match(MODIFIER);
			setState(59);
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

	public static class CostContext extends ParserRuleContext {
		public List<CostRequirementContext> costRequirement() {
			return getRuleContexts(CostRequirementContext.class);
		}
		public CostRequirementContext costRequirement(int i) {
			return getRuleContext(CostRequirementContext.class,i);
		}
		public CostOtherContext costOther() {
			return getRuleContext(CostOtherContext.class,0);
		}
		public CostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cost; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CostContext cost() throws RecognitionException {
		CostContext _localctx = new CostContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cost);
		int _la;
		try {
			int _alt;
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(61);
						costRequirement();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(64); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(66);
					costOther();
					}
				}

				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(69);
					match(T__3);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				costOther();
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(73);
					match(T__3);
					}
				}

				}
				break;
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

	public static class CostOtherContext extends ParserRuleContext {
		public List<CostRequirementContext> costRequirement() {
			return getRuleContexts(CostRequirementContext.class);
		}
		public CostRequirementContext costRequirement(int i) {
			return getRuleContext(CostRequirementContext.class,i);
		}
		public List<TerminalNode> WHITESPCE() { return getTokens(TextGrammarParser.WHITESPCE); }
		public TerminalNode WHITESPCE(int i) {
			return getToken(TextGrammarParser.WHITESPCE, i);
		}
		public CostOtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_costOther; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCostOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCostOther(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCostOther(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CostOtherContext costOther() throws RecognitionException {
		CostOtherContext _localctx = new CostOtherContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_costOther);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			costRequirement();
			setState(84); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(79);
					match(T__0);
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WHITESPCE) {
						{
						setState(80);
						match(WHITESPCE);
						}
					}

					setState(83);
					costRequirement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(86); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class CostRequirementContext extends ParserRuleContext {
		public List<CostPrimaryContext> costPrimary() {
			return getRuleContexts(CostPrimaryContext.class);
		}
		public CostPrimaryContext costPrimary(int i) {
			return getRuleContext(CostPrimaryContext.class,i);
		}
		public TerminalNode OTHER_IDENTIFIER() { return getToken(TextGrammarParser.OTHER_IDENTIFIER, 0); }
		public CostRequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_costRequirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCostRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCostRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCostRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CostRequirementContext costRequirement() throws RecognitionException {
		CostRequirementContext _localctx = new CostRequirementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_costRequirement);
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(T__4);
				setState(91);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case COLOR_IDENTIFIER:
				case NUMBER:
					{
					setState(89);
					costPrimary();
					}
					break;
				case OTHER_IDENTIFIER:
					{
					setState(90);
					match(OTHER_IDENTIFIER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(93);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(T__4);
				setState(95);
				costPrimary();
				setState(96);
				match(T__2);
				setState(97);
				costPrimary();
				setState(98);
				match(T__5);
				}
				break;
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

	public static class CostPrimaryContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(TextGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(TextGrammarParser.NUMBER, i);
		}
		public TerminalNode COLOR_IDENTIFIER() { return getToken(TextGrammarParser.COLOR_IDENTIFIER, 0); }
		public CostPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_costPrimary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).enterCostPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TextGrammarListener ) ((TextGrammarListener)listener).exitCostPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TextGrammarVisitor ) return ((TextGrammarVisitor<? extends T>)visitor).visitCostPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CostPrimaryContext costPrimary() throws RecognitionException {
		CostPrimaryContext _localctx = new CostPrimaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_costPrimary);
		int _la;
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(102);
					match(NUMBER);
					}
					}
					setState(105); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NUMBER );
				}
				break;
			case COLOR_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(COLOR_IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\6\2"+
		"\27\n\2\r\2\16\2\30\3\3\6\3\34\n\3\r\3\16\3\35\3\3\3\3\3\4\3\4\3\4\5\4"+
		"%\n\4\3\4\3\4\5\4)\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5\62\n\5\r\5\16\5"+
		"\63\3\5\3\5\5\58\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\6\7A\n\7\r\7\16\7B\3"+
		"\7\5\7F\n\7\3\7\5\7I\n\7\3\7\3\7\5\7M\n\7\5\7O\n\7\3\b\3\b\3\b\5\bT\n"+
		"\b\3\b\6\bW\n\b\r\b\16\bX\3\t\3\t\3\t\5\t^\n\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\tg\n\t\3\n\6\nj\n\n\r\n\16\nk\3\n\5\no\n\n\3\n\2\2\13\2\4\6\b\n"+
		"\f\16\20\22\2\3\5\2\3\3\f\f\20\21\2\177\2\26\3\2\2\2\4\33\3\2\2\2\6(\3"+
		"\2\2\2\b\61\3\2\2\2\n9\3\2\2\2\fN\3\2\2\2\16P\3\2\2\2\20f\3\2\2\2\22n"+
		"\3\2\2\2\24\27\5\4\3\2\25\27\5\6\4\2\26\24\3\2\2\2\26\25\3\2\2\2\27\30"+
		"\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\3\3\2\2\2\32\34\t\2\2\2\33\32"+
		"\3\2\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36\37\3\2\2\2\37 \7"+
		"\20\2\2 \5\3\2\2\2!)\5\b\5\2\"$\5\f\7\2#%\7\21\2\2$#\3\2\2\2$%\3\2\2\2"+
		"%&\3\2\2\2&\'\5\b\5\2\')\3\2\2\2(!\3\2\2\2(\"\3\2\2\2)\7\3\2\2\2*\62\7"+
		"\13\2\2+\62\7\f\2\2,\62\7\17\2\2-\62\5\n\6\2.\62\7\21\2\2/\62\7\3\2\2"+
		"\60\62\7\20\2\2\61*\3\2\2\2\61+\3\2\2\2\61,\3\2\2\2\61-\3\2\2\2\61.\3"+
		"\2\2\2\61/\3\2\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2"+
		"\2\2\64\65\3\2\2\2\65\67\7\4\2\2\668\7\20\2\2\67\66\3\2\2\2\678\3\2\2"+
		"\28\t\3\2\2\29:\7\r\2\2:;\7\16\2\2;<\7\5\2\2<=\7\r\2\2=>\7\16\2\2>\13"+
		"\3\2\2\2?A\5\20\t\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2"+
		"DF\5\16\b\2ED\3\2\2\2EF\3\2\2\2FH\3\2\2\2GI\7\6\2\2HG\3\2\2\2HI\3\2\2"+
		"\2IO\3\2\2\2JL\5\16\b\2KM\7\6\2\2LK\3\2\2\2LM\3\2\2\2MO\3\2\2\2N@\3\2"+
		"\2\2NJ\3\2\2\2O\r\3\2\2\2PV\5\20\t\2QS\7\3\2\2RT\7\21\2\2SR\3\2\2\2ST"+
		"\3\2\2\2TU\3\2\2\2UW\5\20\t\2VQ\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2"+
		"Y\17\3\2\2\2Z]\7\7\2\2[^\5\22\n\2\\^\7\t\2\2][\3\2\2\2]\\\3\2\2\2^_\3"+
		"\2\2\2_g\7\b\2\2`a\7\7\2\2ab\5\22\n\2bc\7\5\2\2cd\5\22\n\2de\7\b\2\2e"+
		"g\3\2\2\2fZ\3\2\2\2f`\3\2\2\2g\21\3\2\2\2hj\7\16\2\2ih\3\2\2\2jk\3\2\2"+
		"\2ki\3\2\2\2kl\3\2\2\2lo\3\2\2\2mo\7\n\2\2ni\3\2\2\2nm\3\2\2\2o\23\3\2"+
		"\2\2\25\26\30\35$(\61\63\67BEHLNSX]fkn";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}