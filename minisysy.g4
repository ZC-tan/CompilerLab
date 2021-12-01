grammar minisysy;

compUnit: funcDef;//catch[RecognitionException e] { throw e; }finally{}
decl: constDecl | varDecl;
constDecl:'const' bType constDef (',' constDef )* ';';
bType:'int';
constDef:IDENT '=' constInitVal;
constInitVal:constExp;
constExp:addExp;
varDecl:bType varDef (',' varDef)* ';';
varDef:IDENT | IDENT '=' initVal;
initVal:exp;
funcDef: funcType  IDENT  '('  ')'  block;//catch[RecognitionException e] { throw e; }finally{}
funcType: 'int';//catch[RecognitionException e] { throw e; }finally{}
//IDENT: NONDIGIT| IDENT NONDIGIT| IDENT DIGIT;//'main';//catch[RecognitionException e] { throw e; }finally{}
block: '{'  (blockItem)*  '}';//catch[RecognitionException e] { throw e; }finally{}
blockItem: decl | stmt;
stmt: lVal '=' exp ';'| (exp)? ';'|'return' exp ';';//catch[RecognitionException e] { throw e; }finally{}
lVal:IDENT;
exp: addExp;
//addExp: mulExp | addExp ('+' | '−') mulExp;
addExp: mulExp (('+' | '-') mulExp)*;
//mulExp: unaryExp | mulExp ('*' | '/' | '%') unaryExp;
mulExp: unaryExp (('*' | '/' | '%') unaryExp)*;
//unaryExp: primaryExp | unaryOp unaryExp;
unaryExp: primaryExp | unaryOp unaryExp | IDENT '(' (funcRParams)? ')';
funcRParams: exp (',' exp)*;
primaryExp: '(' exp ')' | NUMBER | lVal;
unaryOp: '+' | '-';


fragment HEXADECIMALPREFIX : '0x' | '0X';
fragment NONZERODIGIT      : '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
fragment OCTALDIGIT        : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
fragment DIGIT              : '0' | NONZERODIGIT;
fragment HEXADECIMALDIGIT  : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
                      | 'a' | 'b' | 'c' | 'd' | 'e' | 'f'
                      | 'A' | 'B' | 'C' | 'D' | 'E' | 'F';
fragment NONDIGIT: '_' | [a-zA-Z];
IDENT: (NONDIGIT)+ |  NONDIGIT (NONDIGIT | DIGIT)*;
NUMBER             : DECIMALCONST | OCTALCONST  | HEXADECIMALCONST ;

DECIMALCONST      : NONZERODIGIT DIGIT*;
//NONZERODIGIT | DECIMALCONST DIGIT;

OCTALCONST        : '0' OCTALDIGIT*;
//'0' | OCTALCONST  OCTALDIGIT;
HEXADECIMALCONST  : HEXADECIMALPREFIX  HEXADECIMALDIGIT+;
//HEXADECIMALPREFIX  HEXADECIMALDIGIT | HEXADECIMALCONST  HEXADECIMALDIGIT;


//DIGIT:'0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';

WHITESPACE :[ \r\n\t] -> skip ;
//UNARYOP :'+' | '-';

COMMENT
    : '/*' .*? '*/' -> skip
;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
;