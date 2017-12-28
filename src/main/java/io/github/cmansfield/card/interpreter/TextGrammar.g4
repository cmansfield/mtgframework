grammar TextGrammar;

message
    : (WORD | WHITESPCE)+
    ;

WORD
    : [a-z]+
    ;

WHITESPCE
    : ' '
    ;