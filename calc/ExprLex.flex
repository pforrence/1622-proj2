import java_cup.runtime.Symbol;

%%

%class ExprLex
%cup
%implements sym
%line
%column
%ignorecase
%eofclose

%{
  private void error(String message) {
    System.err.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }
%} 

Identifier = [a-zA-Z_][a-zA-Z0-9_]*
Hex_Int = 0[xX][0-9a-fA-F]+
Int =  0 | [1-9][0-9]* 
LineBreak = \r|\n|\r\n|\z
Comment = #[^\r\n]*{LineBreak}?
BlockComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
WhiteSpace = {LineBreak} | [ \t\f]

%%


/* keywords */

{Comment} {/*Do Nothing*/}
"add" {return new Symbol(ROP, yyline+1, yycolumn+1, "ADD");}
"sub" {return new Symbol(ROP, yyline+1, yycolumn+1, "SUB");}
"slt" {return new Symbol(ROP, yyline+1, yycolumn+1,"SLT");}
"addi" {return new Symbol(IOP, yyline+1, yycolumn+1,"ADDI");}
"beq" {return new Symbol(IOP, yyline+1, yycolumn+1,"BEQ");}
"xor" {return new Symbol(ROP, yyline+1, yycolumn+1,"XOR");}
"sb" {return new Symbol(IOPM, yyline+1, yycolumn+1,"SB");}
"j" {return new Symbol(JOP, yyline+1, yycolumn+1,"JUMP");}
"lb" {return new Symbol(IOPM, yyline+1, yycolumn+1,"LB");}
"rand" {return new Symbol(RAOP, yyline+1, yycolumn+1,"RAND");}
"disp" {return new Symbol(DOP, yyline+1, yycolumn+1,"DISP");}
"halt" {return new Symbol(HOP, yyline+1, yycolumn+1,"HALT");}
".text" {return new Symbol(DOT_TEXT, yyline+1, yycolumn+1);}
")" {return new Symbol(RPAREN, yyline+1, yycolumn+1); }
"(" {return new Symbol(LPAREN, yyline+1, yycolumn+1); }
":" {return new Symbol(COLON, yyline+1, yycolumn+1); }
"," {return new Symbol(COMMA, yyline+1, yycolumn+1); }
"$r0" {return new Symbol(REG, yyline+1, yycolumn+1, "0");}
"$r1" {return new Symbol(REG, yyline+1, yycolumn+1, "1");}
"$r2" {return new Symbol(REG, yyline+1, yycolumn+1, "2");}
"$r3" {return new Symbol(REG, yyline+1, yycolumn+1, "3");}
{Identifier} {return new Symbol(ID, yyline+1, yycolumn+1, yytext());}
{Hex_Int} {return new Symbol(INT, yyline+1, yycolumn+1,  new Integer(Integer.decode(yytext())));}
{Int} {return new Symbol(INT, yyline+1, yycolumn+1, Integer.parseInt(yytext()));}


{WhiteSpace} { /*Do Nothing*/ }
/* error fallback */
.|\n	{ error("Illegal character <"+ yytext()+">"); }
