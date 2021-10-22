grammar minisysy;

@parser::members {

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

}

compUnit: funcDef;catch[RecognitionException e] { throw e; }finally{}
funcDef: funcType  ident  '('  ')'  block;catch[RecognitionException e] { throw e; }finally{}
funcType: 'int';catch[RecognitionException e] { throw e; }finally{}
ident: 'main';catch[RecognitionException e] { throw e; }finally{}
block: '{'  stmt  '}';catch[RecognitionException e] { throw e; }finally{}
stmt: 'return' NUMBER ';';catch[RecognitionException e] { throw e; }finally{}

fragment HEXADECIMALPREFIX : '0x' | '0X';
fragment NONZERODIGIT      : '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
fragment OCTALDIGIT        : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
fragment DIGIT              : '0' | NONZERODIGIT;
fragment HEXADECIMALDIGIT  : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
                      | 'a' | 'b' | 'c' | 'd' | 'e' | 'f'
                      | 'A' | 'B' | 'C' | 'D' | 'E' | 'F';
NUMBER             : DECIMALCONST | OCTALCONST  | HEXADECIMALCONST ;

DECIMALCONST      : NONZERODIGIT DIGIT*;
//NONZERODIGIT | DECIMALCONST DIGIT;

OCTALCONST        : '0' OCTALDIGIT*;
//'0' | OCTALCONST  OCTALDIGIT;
HEXADECIMALCONST  : HEXADECIMALPREFIX  HEXADECIMALDIGIT+;
//HEXADECIMALPREFIX  HEXADECIMALDIGIT | HEXADECIMALCONST  HEXADECIMALDIGIT;
WHITESPACE :[ \r\n\t] -> skip ;

COMMENT
    : '/*' .*? '*/' -> skip
;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
;