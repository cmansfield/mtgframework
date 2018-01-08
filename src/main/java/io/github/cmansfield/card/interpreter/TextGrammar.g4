grammar TextGrammar;

start
    : message
    ;

message
    : (WORD | WHITESPCE)+
    ;

WORD
    : [a-z]+
    ;

WHITESPCE
    : ' '
    ;