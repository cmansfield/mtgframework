grammar TextGrammar;

// Parser

text
    : (commonAbility | uniqueAbility)+
    ;

commonAbility
    : (ABILITY_KEYWORD | COMMA | NEWLINE | WHITESPCE)+ NEWLINE
    ;

uniqueAbility
    : (ACTION_KEYWORD | ABILITY_KEYWORD | WORD | counters | WHITESPCE | COMMA | NEWLINE)+ '.'
    ;

counters
    : MODIFIER NUMBER '/' MODIFIER NUMBER
    ;

// Lexer

ACTION_KEYWORD
    : [Aa]'ttach'
    | [Cc]'ounter'
    | [Ee]'xile'
    | [Ff]'ight'
    | [Rr]'egenerate'
    | [Ss]'acrifice'
    | [Tt]'ap'
    | [Uu]'ntap'
    ;

ABILITY_KEYWORD
    : [Dd]'eathtouch'
    | [Dd]'efender'
    | [Dd]'ouble strike'
    | [Ee]'nchant'
    | [Ee]'quip'
    | [Ff]'irst strike'
    | [Ff]'lash'
    | [Ff]'lying'
    | [Hh]'aste'
    | [Hh]'exproof'
    | [Ii]'ndestructible'
    | [Ll]'ifelink'
    | [Mm]'enace'
    | [Pp]'rowess'
    | [Rr]'each'
    | [Ss]'cry'
    | [Tt]'rample'
    | [Vv]'igilance'
    | [Aa]'bsorb'
    | [Aa]'ffinity'
    | [Aa]'ftermath'
    | [Aa]'mplify'
    | [Aa]'nnihilator'
    | [Aa]'ura swap'
    | [Bb]'ands with other'
    | [Bb]'attle cry'
    | [Bb]'estow'
    | [Bb]'olster'
    | [Bb]'loodthirst'
    | [Bb]'ushido'
    | [Bb]'uyback'
    | [Cc]'ascade'
    | [Cc]'hampion'
    | [Cc]'hangeling'
    | [Cc]'ipher'
    | [Cc]'lash'
    | [Cc]'onspire'
    | [Cc]'onvoke'
    | [Cc]'rew'
    | [Cc]'umulative upkeep'
    | [Cc]'ycling'
    | [Dd]'ash'
    | [Dd]'elve'
    | [Dd]'etain'
    | [Dd]'evour'
    | [Dd]'redge'
    | [Ee]'cho'
    | [Ee]'mbalm'
    | [Ee]'ntwine'
    | [Ee]'pic'
    | [Ee]'volve'
    | [Ee]'voke'
    | [Ee]'xalted'
    | [Ee]'xert'
    | [Ee]'xploit'
    | [Ee]'xtort'
    | [Ff]'ading'
    | [Ff]'ateseal'
    | [Ff]'lanking'
    | [Ff]'lashback'
    | [Ff]'lip'
    | [Ff]'orecast'
    | [Ff]'ortify'
    | [Ff]'renzy'
    | [Gg]'raft'
    | [Gg]'ravestorm'
    | [Hh]'aunt'
    | [Hh]'ideaway'
    | [Hh]'orsemanship'
    | [Ii]'nfect'
    | [Kk]'icker'
    | [Ll]'evel up'
    | [Ll]'iving weapon'
    | [Mm]'adness'
    | [Mm]'anifest'
    | [Mm]'eld'
    | [Mm]'iracle'
    | [Mm]'odular'
    | [Mm]'onstrosity'
    | [Mm]'orph'
    | [Mm]'ultikicker'
    | [Nn]'injutsu'
    | [Oo]'ffering'
    | [Oo]'verload'
    | [Pp]'ersist'
    | [Pp]'hasing'
    | [Pp]'oisonous'
    | [Pp]'opulate'
    | [Pp]'roliferate'
    | [Pp]'rovoke'
    | [Pp]'rowl'
    | [Rr]'ampage'
    | [Rr]'ebound'
    | [Rr]'ecover'
    | [Rr]'einforce'
    | [Rr]'enown'
    | [Rr]'eplicate'
    | [Rr]'etrace'
    | [Rr]'ipple'
    | [Ss]'cavenge'
    | [Ss]'hadow'
    | [Ss]'oulbond'
    | [Ss]'oulshift'
    | [Ss]'plice'
    | [Ss]'plit second'
    | [Ss]'torm'
    | [Ss]'unburst'
    | [Ss]'uspend'
    | [Tt]'otem armor'
    | [Tt]'ransfigure'
    | [Tt]'ransform'
    | [Tt]'ransmute'
    | [Tt]'ypecycling'
    | [Uu]'ndying'
    | [Uu]'nearth'
    | [Uu]'nleash'
    | [Vv]'anishing'
    | [Ww]'ither'
    | [Bb]'attalion'
    | [Bb]'loodrush'
    | [Cc]'hannel'
    | [Cc]'hroma'
    | [Dd]'omain'
    | [Ff]'ateful hour'
    | [Ff]'erocious'
    | [Gg]'randeur'
    | [Hh]'ellbent'
    | [Hh]'eroic'
    | [Ii]'mprint'
    | [Jj]'oin forces'
    | [Kk]'inship'
    | [Ll]'andfall'
    | [Mm]'etalcraft'
    | [Mm]'orbid'
    | [Rr]'adiance'
    | [Rr]'aid'
    | [Rr]'ally'
    | [Ss]'weep'
    | [Tt]'hreshold'
    | [Bb]'anding'
    | [Bb]'ury'
    | [Ff]'ear'
    | [Ii]'ntimidate'
    | [Ll]'andhome'
    | [Ll]'andwalk'
    | [Pp]'hasing'
    | [Pp]'rotection'
    | [Ss]'hroud'
    | [Ss]'ubstance'
    ;

MODIFIER
    : ('+' | '-')
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