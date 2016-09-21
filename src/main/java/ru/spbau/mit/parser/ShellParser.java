// Generated from /home/golovanov/repo/git/Software_design/src/main/java/ru/spbau/mit/parser/Shell.g4 by ANTLR 4.5.3
package ru.spbau.mit.parser;

import java.util.*;
import ru.spbau.mit.Command;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShellParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, ASSIGN=2, PIPE=3, STRONG_QUOTES_STR=4, WEAK_QUOTES_STR=5, REF=6, 
		IDENTIFIER=7, WORD=8;
	public static final int
		RULE_ref = 0, RULE_strong_quotes = 1, RULE_weak_quotes = 2, RULE_word = 3, 
		RULE_assignment = 4, RULE_program = 5, RULE_start = 6;
	public static final String[] ruleNames = {
		"ref", "strong_quotes", "weak_quotes", "word", "assignment", "program", 
		"start"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'='", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "ASSIGN", "PIPE", "STRONG_QUOTES_STR", "WEAK_QUOTES_STR", 
		"REF", "IDENTIFIER", "WORD"
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
	public String getGrammarFileName() { return "Shell.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	private HashMap<String, String> env;

	public void setEnv(HashMap<String, String> env) {
	    this.env = env;
	}

	private String getEnvVar(String name) {
	    String key = name.substring(1, name.length());
	    String value = env.get(key);
	    return value == null ? "" : value;
	}

	private String replaceVar(String str) {
	    String newStr = str.substring(1, str.length() - 1);
	    StringBuilder result = new StringBuilder();
	    String ref= "";
	    boolean isRef = false;

	    for (int i = 0; i < newStr.length(); i++) {
	        char ch = newStr.charAt(i);

	        if (!isRef) {
	            if (ch == '$') isRef = true;
	            else result.append(ch);
	        } else {
	            if (!(ref + ch).matches("[_a-zA-z][_a-zA-z0-9]*")) {
	                String value = env.get(ref);
	                result.append(value == null ? "" : value);
	                i--;
	                ref = "";
	                isRef = false;
	            } else {
	                ref += ch;
	            }
	        }
	    }

	    String value = env.get(ref);
	    result.append(value == null ? "" : value);

	    return result.toString();
	}

	public ShellParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RefContext extends ParserRuleContext {
		public String str;
		public Token REF;
		public TerminalNode REF() { return getToken(ShellParser.REF, 0); }
		public RefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ref; }
	}

	public final RefContext ref() throws RecognitionException {
		RefContext _localctx = new RefContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			((RefContext)_localctx).REF = match(REF);
			((RefContext)_localctx).str = getEnvVar((((RefContext)_localctx).REF!=null?((RefContext)_localctx).REF.getText():null));
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

	public static class Strong_quotesContext extends ParserRuleContext {
		public String str;
		public Token STRONG_QUOTES_STR;
		public TerminalNode STRONG_QUOTES_STR() { return getToken(ShellParser.STRONG_QUOTES_STR, 0); }
		public Strong_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strong_quotes; }
	}

	public final Strong_quotesContext strong_quotes() throws RecognitionException {
		Strong_quotesContext _localctx = new Strong_quotesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_strong_quotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			((Strong_quotesContext)_localctx).STRONG_QUOTES_STR = match(STRONG_QUOTES_STR);
			String newStr=(((Strong_quotesContext)_localctx).STRONG_QUOTES_STR!=null?((Strong_quotesContext)_localctx).STRONG_QUOTES_STR.getText():null);
			                         newStr=newStr.substring(1, newStr.length() - 1);
			                         ((Strong_quotesContext)_localctx).str = newStr;
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

	public static class Weak_quotesContext extends ParserRuleContext {
		public String str;
		public Token WEAK_QUOTES_STR;
		public TerminalNode WEAK_QUOTES_STR() { return getToken(ShellParser.WEAK_QUOTES_STR, 0); }
		public Weak_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weak_quotes; }
	}

	public final Weak_quotesContext weak_quotes() throws RecognitionException {
		Weak_quotesContext _localctx = new Weak_quotesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_weak_quotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((Weak_quotesContext)_localctx).WEAK_QUOTES_STR = match(WEAK_QUOTES_STR);
			((Weak_quotesContext)_localctx).str = replaceVar((((Weak_quotesContext)_localctx).WEAK_QUOTES_STR!=null?((Weak_quotesContext)_localctx).WEAK_QUOTES_STR.getText():null));
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

	public static class WordContext extends ParserRuleContext {
		public String str;
		public Strong_quotesContext strong_quotes;
		public Weak_quotesContext weak_quotes;
		public RefContext ref;
		public Token IDENTIFIER;
		public Token WORD;
		public Strong_quotesContext strong_quotes() {
			return getRuleContext(Strong_quotesContext.class,0);
		}
		public Weak_quotesContext weak_quotes() {
			return getRuleContext(Weak_quotesContext.class,0);
		}
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ShellParser.IDENTIFIER, 0); }
		public TerminalNode WORD() { return getToken(ShellParser.WORD, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_word);
		try {
			setState(36);
			switch (_input.LA(1)) {
			case STRONG_QUOTES_STR:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				((WordContext)_localctx).strong_quotes = strong_quotes();
				((WordContext)_localctx).str = ((WordContext)_localctx).strong_quotes.str;
				}
				break;
			case WEAK_QUOTES_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				((WordContext)_localctx).weak_quotes = weak_quotes();
				((WordContext)_localctx).str = ((WordContext)_localctx).weak_quotes.str;
				}
				break;
			case REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				((WordContext)_localctx).ref = ref();
				((WordContext)_localctx).str = ((WordContext)_localctx).ref.str;
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(32);
				((WordContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((WordContext)_localctx).str = (((WordContext)_localctx).IDENTIFIER!=null?((WordContext)_localctx).IDENTIFIER.getText():null);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 5);
				{
				setState(34);
				((WordContext)_localctx).WORD = match(WORD);
				((WordContext)_localctx).str = (((WordContext)_localctx).WORD!=null?((WordContext)_localctx).WORD.getText():null);
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

	public static class AssignmentContext extends ParserRuleContext {
		public Command cmd;
		public Token IDENTIFIER;
		public WordContext word;
		public TerminalNode IDENTIFIER() { return getToken(ShellParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(ShellParser.ASSIGN, 0); }
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((AssignmentContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(39);
			match(ASSIGN);
			setState(40);
			((AssignmentContext)_localctx).word = word();
			((AssignmentContext)_localctx).cmd = new Command(Command.Type.VAR, (((AssignmentContext)_localctx).IDENTIFIER!=null?((AssignmentContext)_localctx).IDENTIFIER.getText():null), Arrays.asList(((AssignmentContext)_localctx).word.str));
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

	public static class ProgramContext extends ParserRuleContext {
		public Command cmd;
		public List<String> list = new ArrayList<>();
		public WordContext word;
		public List<WordContext> word() {
			return getRuleContexts(WordContext.class);
		}
		public WordContext word(int i) {
			return getRuleContext(WordContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(43);
				((ProgramContext)_localctx).word = word();
				_localctx.list.add(((ProgramContext)_localctx).word.str);
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRONG_QUOTES_STR) | (1L << WEAK_QUOTES_STR) | (1L << REF) | (1L << IDENTIFIER) | (1L << WORD))) != 0) );
			String name=_localctx.list.get(0);
			                                        _localctx.list.remove(0);
			                                        ((ProgramContext)_localctx).cmd = new Command(Command.Type.PROG, name, _localctx.list);
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

	public static class StartContext extends ParserRuleContext {
		public List<Command> list = new ArrayList<>();
		public AssignmentContext assignment;
		public ProgramContext program;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public List<TerminalNode> PIPE() { return getTokens(ShellParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(ShellParser.PIPE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_start);
		int _la;
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((StartContext)_localctx).assignment = assignment();
				((StartContext)_localctx).list = Arrays.asList(((StartContext)_localctx).assignment.cmd);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				((StartContext)_localctx).program = program();
				_localctx.list.add(((StartContext)_localctx).program.cmd);
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PIPE) {
					{
					{
					setState(57);
					match(PIPE);
					setState(58);
					((StartContext)_localctx).program = program();
					_localctx.list.add(((StartContext)_localctx).program.cmd);
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				((StartContext)_localctx).list = null;
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\nH\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\'\n\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\6\7\61\n\7\r\7\16\7\62\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b@\n\b\f\b\16\bC\13\b\3\b\5\bF\n\b\3"+
		"\b\2\2\t\2\4\6\b\n\f\16\2\2H\2\20\3\2\2\2\4\23\3\2\2\2\6\26\3\2\2\2\b"+
		"&\3\2\2\2\n(\3\2\2\2\f\60\3\2\2\2\16E\3\2\2\2\20\21\7\b\2\2\21\22\b\2"+
		"\1\2\22\3\3\2\2\2\23\24\7\6\2\2\24\25\b\3\1\2\25\5\3\2\2\2\26\27\7\7\2"+
		"\2\27\30\b\4\1\2\30\7\3\2\2\2\31\32\5\4\3\2\32\33\b\5\1\2\33\'\3\2\2\2"+
		"\34\35\5\6\4\2\35\36\b\5\1\2\36\'\3\2\2\2\37 \5\2\2\2 !\b\5\1\2!\'\3\2"+
		"\2\2\"#\7\t\2\2#\'\b\5\1\2$%\7\n\2\2%\'\b\5\1\2&\31\3\2\2\2&\34\3\2\2"+
		"\2&\37\3\2\2\2&\"\3\2\2\2&$\3\2\2\2\'\t\3\2\2\2()\7\t\2\2)*\7\4\2\2*+"+
		"\5\b\5\2+,\b\6\1\2,\13\3\2\2\2-.\5\b\5\2./\b\7\1\2/\61\3\2\2\2\60-\3\2"+
		"\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\b\7"+
		"\1\2\65\r\3\2\2\2\66\67\5\n\6\2\678\b\b\1\28F\3\2\2\29:\5\f\7\2:A\b\b"+
		"\1\2;<\7\5\2\2<=\5\f\7\2=>\b\b\1\2>@\3\2\2\2?;\3\2\2\2@C\3\2\2\2A?\3\2"+
		"\2\2AB\3\2\2\2BF\3\2\2\2CA\3\2\2\2DF\b\b\1\2E\66\3\2\2\2E9\3\2\2\2ED\3"+
		"\2\2\2F\17\3\2\2\2\6&\62AE";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}