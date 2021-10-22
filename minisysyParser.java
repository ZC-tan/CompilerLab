// Generated from minisysy.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class minisysyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NUMBER=9, 
		DECIMALCONST=10, OCTALCONST=11, HEXADECIMALCONST=12, WHITESPACE=13, COMMENT=14, 
		LINE_COMMENT=15;
	public static final int
		RULE_compUnit = 0, RULE_funcDef = 1, RULE_funcType = 2, RULE_ident = 3, 
		RULE_block = 4, RULE_stmt = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"compUnit", "funcDef", "funcType", "ident", "block", "stmt"
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

	@Override
	public String getGrammarFileName() { return "minisysy.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	//  public static void main(String[] args) throws Exception {
	//    String text = args[0];
	//    ANTLRStringStream in = new ANTLRStringStream(text);
	//    TestLexer lexer = new TestLexer(in);
	//    CommonTokenStream tokens = new CommonTokenStream(lexer);
	//    System.out.println(new TestParser(tokens).mainRule());
	//  }
	//
	//  @Override
	//  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
	//    throw new MismatchedTokenException(ttype, input);
	//  }
	//
	//  @Override
	//  public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
	//    throw e;
	//  }
	//
	//}
	//
	//@rulecatch {
	//    catch (RecognitionException e) {
	//        throw e;
	//    }
	//}
	//
	//@lexer::members {
	//    @Override
	//    public void reportError(RecognitionException e) {
	//        throw new RuntimeException(e);
	//    }


	public minisysyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CompUnitContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public CompUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterCompUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitCompUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitCompUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompUnitContext compUnit() throws RecognitionException {
		CompUnitContext _localctx = new CompUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			funcDef();
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public FuncTypeContext funcType() {
			return getRuleContext(FuncTypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			funcType();
			setState(15);
			ident();
			setState(16);
			match(T__0);
			setState(17);
			match(T__1);
			setState(18);
			block();
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncTypeContext extends ParserRuleContext {
		public FuncTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterFuncType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitFuncType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitFuncType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncTypeContext funcType() throws RecognitionException {
		FuncTypeContext _localctx = new FuncTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__2);
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(T__3);
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__4);
			setState(25);
			stmt();
			setState(26);
			match(T__5);
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(minisysyParser.NUMBER, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof minisysyListener ) ((minisysyListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisysyVisitor ) return ((minisysyVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__6);
			setState(29);
			match(NUMBER);
			setState(30);
			match(T__7);
			}
		}
		catch (RecognitionException e) {
			 throw e; 
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2"+
		"\2\34\2\16\3\2\2\2\4\20\3\2\2\2\6\26\3\2\2\2\b\30\3\2\2\2\n\32\3\2\2\2"+
		"\f\36\3\2\2\2\16\17\5\4\3\2\17\3\3\2\2\2\20\21\5\6\4\2\21\22\5\b\5\2\22"+
		"\23\7\3\2\2\23\24\7\4\2\2\24\25\5\n\6\2\25\5\3\2\2\2\26\27\7\5\2\2\27"+
		"\7\3\2\2\2\30\31\7\6\2\2\31\t\3\2\2\2\32\33\7\7\2\2\33\34\5\f\7\2\34\35"+
		"\7\b\2\2\35\13\3\2\2\2\36\37\7\t\2\2\37 \7\13\2\2 !\7\n\2\2!\r\3\2\2\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}