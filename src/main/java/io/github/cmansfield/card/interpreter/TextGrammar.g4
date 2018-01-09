grammar TextGrammar;

// Parser

text
    : (commonAbility | uniqueAbility)+
    ;

commonAbility
    : WORD NEWLINE
    ;

uniqueAbility
    : (WORD | counters | WHITESPCE | COMMA)+ '.'
    ;

counters
    : MODIFIER NUMBER '/' MODIFIER NUMBER
    ;

// Lexer

MODIFIER
    : ('+'|'-')
    ;

NUMBER
    : [0-9]+
    ;

WORD
    : [a-zA-Z]+
    ;

NEWLINE
    : [\n]
    ;

COMMA
    : ','
    ;

WHITESPCE
    : [ \t\r]+
    ;