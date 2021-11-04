grammar minisysy;

compUnit: funcDef;//catch[RecognitionException e] { throw e; }finally{}
funcDef: funcType  ident  '('  ')'  block;//catch[RecognitionException e] { throw e; }finally{}
funcType: 'int';//catch[RecognitionException e] { throw e; }finally{}
ident: 'main';//catch[RecognitionException e] { throw e; }finally{}
block: '{'  stmt  '}';//catch[RecognitionException e] { throw e; }finally{}
stmt: 'return' exp ';';//catch[RecognitionException e] { throw e; }finally{}
exp: addExp;
//addExp: mulExp | addExp ('+' | '−') mulExp;
addExp: mulExp (('+' | '-') mulExp)*;
//mulExp: unaryExp | mulExp ('*' | '/' | '%') unaryExp;
mulExp: unaryExp (('*' | '/' | '%') unaryExp)*;
//unaryExp: primaryExp | unaryOp unaryExp;
unaryExp: primaryExp | unaryOp unaryExp;
primaryExp: '(' exp ')' | NUMBER;
unaryOp: '+' | '-';


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
//UNARYOP :'+' | '-';

COMMENT
    : '/*' .*? '*/' -> skip
;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
;