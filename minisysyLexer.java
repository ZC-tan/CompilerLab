// Generated from minisysy.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class minisysyLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NUMBER=9, 
		DECIMALCONST=10, OCTALCONST=11, HEXADECIMALCONST=12, WHITESPACE=13, COMMENT=14, 
		LINE_COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "HEXADECIMALPREFIX", 
			"NONZERODIGIT", "OCTALDIGIT", "DIGIT", "HEXADECIMALDIGIT", "NUMBER", 
			"DECIMALCONST", "OCTALCONST", "HEXADECIMALCONST", "WHITESPACE", "COMMENT", 
			"LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'main'", "'{'", "'}'", "'return'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "NUMBER", "DECIMALCONST", 
			"OCTALCONST", "HEXADECIMALCONST", "WHITESPACE", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public minisysyLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "minisysy.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u008b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\5\nJ\n\n\3\13\3\13\3\f\3\f\3\r\3\r\5\rR\n\r\3\16\3\16"+
		"\3\17\3\17\3\17\5\17Y\n\17\3\20\3\20\7\20]\n\20\f\20\16\20`\13\20\3\21"+
		"\3\21\7\21d\n\21\f\21\16\21g\13\21\3\22\3\22\6\22k\n\22\r\22\16\22l\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24w\n\24\f\24\16\24z\13\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u0085\n\25\f\25\16\25"+
		"\u0088\13\25\3\25\3\25\3x\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2"+
		"\25\2\27\2\31\2\33\2\35\13\37\f!\r#\16%\17\'\20)\21\3\2\5\5\2\62;CHch"+
		"\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u008e\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\63\3\2\2\2\138\3\2"+
		"\2\2\r:\3\2\2\2\17<\3\2\2\2\21C\3\2\2\2\23I\3\2\2\2\25K\3\2\2\2\27M\3"+
		"\2\2\2\31Q\3\2\2\2\33S\3\2\2\2\35X\3\2\2\2\37Z\3\2\2\2!a\3\2\2\2#h\3\2"+
		"\2\2%n\3\2\2\2\'r\3\2\2\2)\u0080\3\2\2\2+,\7*\2\2,\4\3\2\2\2-.\7+\2\2"+
		".\6\3\2\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7v\2\2\62\b\3\2\2\2\63\64\7"+
		"o\2\2\64\65\7c\2\2\65\66\7k\2\2\66\67\7p\2\2\67\n\3\2\2\289\7}\2\29\f"+
		"\3\2\2\2:;\7\177\2\2;\16\3\2\2\2<=\7t\2\2=>\7g\2\2>?\7v\2\2?@\7w\2\2@"+
		"A\7t\2\2AB\7p\2\2B\20\3\2\2\2CD\7=\2\2D\22\3\2\2\2EF\7\62\2\2FJ\7z\2\2"+
		"GH\7\62\2\2HJ\7Z\2\2IE\3\2\2\2IG\3\2\2\2J\24\3\2\2\2KL\4\63;\2L\26\3\2"+
		"\2\2MN\4\629\2N\30\3\2\2\2OR\7\62\2\2PR\5\25\13\2QO\3\2\2\2QP\3\2\2\2"+
		"R\32\3\2\2\2ST\t\2\2\2T\34\3\2\2\2UY\5\37\20\2VY\5!\21\2WY\5#\22\2XU\3"+
		"\2\2\2XV\3\2\2\2XW\3\2\2\2Y\36\3\2\2\2Z^\5\25\13\2[]\5\31\r\2\\[\3\2\2"+
		"\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_ \3\2\2\2`^\3\2\2\2ae\7\62\2\2bd\5\27"+
		"\f\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\"\3\2\2\2ge\3\2\2\2hj\5"+
		"\23\n\2ik\5\33\16\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2m$\3\2\2\2"+
		"no\t\3\2\2op\3\2\2\2pq\b\23\2\2q&\3\2\2\2rs\7\61\2\2st\7,\2\2tx\3\2\2"+
		"\2uw\13\2\2\2vu\3\2\2\2wz\3\2\2\2xy\3\2\2\2xv\3\2\2\2y{\3\2\2\2zx\3\2"+
		"\2\2{|\7,\2\2|}\7\61\2\2}~\3\2\2\2~\177\b\24\2\2\177(\3\2\2\2\u0080\u0081"+
		"\7\61\2\2\u0081\u0082\7\61\2\2\u0082\u0086\3\2\2\2\u0083\u0085\n\4\2\2"+
		"\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\b\25\2\2"+
		"\u008a*\3\2\2\2\13\2IQX^elx\u0086\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}