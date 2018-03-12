/*User Code */
import java.util.*;
import java_cup.runtime.*;
import java_cup.sym;
%%
%class Lexer
%unicode
%public
%cup
%line
%column
%{

	public static final int ROP = 0;
	public static final int IOP = 1;
	public static final int IOPM = 2;
	public static final int JOP = 3;
	public static final int DOP = 4;
	public static final int HOP = 5;
	public static final int DOT_TEXT = 6;
	public static final int RPAREN = 7;
	public static final int LPAREN = 8;
	public static final int COLON = 9;
	public static final int COMMA = 10;
	public static final int REG = 11;
	public static final int ID = 12;
	public static final int INT = 13;

	public boolean end_of_file = false;
    StringBuffer string = new StringBuffer();

	private Symbol symbol(int type){
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, int op_type){
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value){
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

Identifier = [a-zA-Z_][a-zA-Z0-9_]*
Hex_Int = 0[xX][0-9a-fA-F]+
Int =  0 | [1-9][0-9]* 
LineBreak = \r|\n|\r\n|\z
Comment = #[^\r\n]*{LineBreak}?
BlockComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
WhiteSpace = {LineBreak} | [ \t\f]

%init{
	System.out.println("Line\t\t Column\t\t Token\t\t Value");
	System.out.println("=====================================================================");
%init}

%eof{
	end_of_file = true;
%eof}





/*Options and declarations */
%% 

{Comment} {/*Do Nothing*/}
"add" {return symbol(ROP, "ADD");}
"sub" {return symbol(ROP, "SUB");}
"slt" {return symbol(ROP, "SLT");}
"addi" {return symbol(IOP, "ADDI");}
"beq" {return symbol(IOP, "BEQ");}
"xor" {return symbol(ROP, "XOR");}
"sb" {return symbol(IOPM, "SB");}
"j" {return symbol(JOP, "JUMP");}
"lb" {return symbol(IOPM, "LB");}
"rand" {return symbol(JOP, "RAND");}
"disp" {return symbol(DOP, "DISP");}
"halt" {return symbol(HOP, "HALT");}
".text" {return symbol(DOT_TEXT);}
")" {return symbol(RPAREN);}
"(" {return symbol(LPAREN);}
":" {return symbol(COLON);}
"," {return symbol(COMMA);}
"$R0" {return symbol(REG, "0");}
"$R1" {return symbol(REG, "1");}
"$R2" {return symbol(REG, "2");}
"$R3" {return symbol(REG, "3");}
{Identifier} {return symbol(ID, yytext());}
{Hex_Int} {return symbol(INT, Integer.decode(yytext()));}
{Int} {return symbol(INT, Integer.parseInt(yytext()));}
{WhiteSpace} { /*Do Nothing*/ }

.|\n {System.out.println("Illegal character: '"+ yytext()+"'" + "at line " + yyline + " column " + yycolumn);}