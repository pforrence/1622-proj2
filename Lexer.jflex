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
	public static final int ADD = 0;
	public static final int SUB = 1;
	public static final int SLT = 2;
	public static final int ADDI = 3;
	public static final int BEQ = 4;
	public static final int XOR = 5;
	public static final int SB = 6;
	public static final int JUMP = 7;
	public static final int LB = 8;
	public static final int RAND = 9;
	public static final int DISP = 10;
	public static final int HALT = 11;
	public static final int DOT_TEXT = 12;
	public static final int RPAREN = 13;
	public static final int LPAREN = 14;
	public static final int COLON = 15;
	public static final int COMMA = 16;
	public static final int R0 = 17;
	public static final int R1 = 18;
	public static final int R2 = 19;
	public static final int R3 = 20;
	public static final int ID = 21;
	public static final int INT = 22;


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
"add" {return symbol(ADD, "Rtype");}
"sub" {return symbol(SUB, "Rtype");}
"slt" {return symbol(SLT, "Rtype");}
"addi" {return symbol(ADDI, "Itype");}
"beq" {return symbol(BEQ, "Itype");}
"xor" {return symbol(XOR, "Rtype");}
"sb" {return symbol(SB, "Itype");}
"j" {return symbol(JUMP, "Jtype");}
"lb" {return symbol(LB, "Itype");}
"rand" {return symbol(RAND, "Jtype");}
"disp" {return symbol(DISP, "Dtype");}
"halt" {return symbol(HALT, "Htype");}
".text" {return symbol(DOT_TEXT);}
")" {return symbol(RPAREN);}
"(" {return symbol(LPAREN);}
":" {return symbol(COLON);}
"," {return symbol(COMMA);}
"$R0" {return symbol(R0);}
"$R1" {return symbol(R1);}
"$R2" {return symbol(R2);}
"$R3" {return symbol(R3);}
{Identifier} {return symbol(ID, yytext());}
{Hex_Int} {return symbol(INT, Integer.decode(yytext()));}
{Int} {return symbol(INT, Integer.parseInt(yytext()));}
{WhiteSpace} { /*Do Nothing*/ }

.|\n {System.out.println("Illegal character: '"+ yytext()+"'" + "at line " + yyline + " column " + yycolumn);}