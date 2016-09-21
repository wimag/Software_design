// Generated from /home/golovanov/repo/git/Software_design/src/main/java/ru/spbau/mit/parser/Shell.g4 by ANTLR 4.5.3
package ru.spbau.mit.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShellLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, ASSIGN=2, PIPE=3, STRONG_QUOTES_STR=4, WEAK_QUOTES_STR=5, REF=6, 
		IDENTIFIER=7, WORD=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "ASSIGN", "PIPE", "STRONG_QUOTES_STR", "WEAK_QUOTES_STR", "REF", 
		"IDENTIFIER", "WORD", "CHAR"
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


	public ShellLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Shell.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\nC\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\7\5 \n\5\f\5\16\5#\13\5\3\5\3\5\3\6\3"+
		"\6\7\6)\n\6\f\6\16\6,\13\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\7\b\65\n\b\f\b"+
		"\16\b8\13\b\3\t\6\t;\n\t\r\t\16\t<\3\n\3\n\3\n\5\nB\n\n\2\2\13\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\2\3\2\7\4\2\13\f\"\"\3\2C|\4\2\62;C|\t"+
		"\2\13\f\"\"$$&&))??~~\7\2$$))^^ppvvF\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\25"+
		"\3\2\2\2\5\31\3\2\2\2\7\33\3\2\2\2\t\35\3\2\2\2\13&\3\2\2\2\r/\3\2\2\2"+
		"\17\62\3\2\2\2\21:\3\2\2\2\23A\3\2\2\2\25\26\t\2\2\2\26\27\3\2\2\2\27"+
		"\30\b\2\2\2\30\4\3\2\2\2\31\32\7?\2\2\32\6\3\2\2\2\33\34\7~\2\2\34\b\3"+
		"\2\2\2\35!\7)\2\2\36 \5\23\n\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\""+
		"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7)\2\2%\n\3\2\2\2&*\7$\2\2\')\5\23\n\2"+
		"(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-.\7$\2\2"+
		".\f\3\2\2\2/\60\7&\2\2\60\61\5\17\b\2\61\16\3\2\2\2\62\66\t\3\2\2\63\65"+
		"\t\4\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\20\3"+
		"\2\2\28\66\3\2\2\29;\n\5\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2="+
		"\22\3\2\2\2>B\4\"\u0081\2?@\7^\2\2@B\t\6\2\2A>\3\2\2\2A?\3\2\2\2B\24\3"+
		"\2\2\2\b\2!*\66<A\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}