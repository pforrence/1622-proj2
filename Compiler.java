import java.util.*;
import java_cup.runtime.*;

public class Compiler
{	
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Compiler <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        Lexer scanner = null;
        try {
          scanner = new Lexer( new java.io.FileReader(argv[i]) );
          Symbol temp = scanner.next_token();

          while ( !scanner.end_of_file ) 
          	{   
          		// System.out.println(temp.sym + );
                System.out.print(temp.left + "\t\t " + temp.right + "\t\t " + getSymName(temp.sym) + "\t\t ");
                if (temp.value != null) System.out.print(temp.value);
                System.out.println();
                temp = scanner.next_token();
          	}
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }
  public static String getSymName(int sym_num)
  {
  	if (sym_num == 0) return "ADD";
  	if (sym_num == 1) return "SUB";
  	if (sym_num == 2) return "SLT";
  	if (sym_num == 3) return "ADDI";
  	if (sym_num == 4) return "BEQ";
  	if (sym_num == 5) return "XOR";
  	if (sym_num == 6) return "SB";
  	if (sym_num == 7) return "JUMP";
  	if (sym_num == 8) return "LB";
  	if (sym_num == 9) return "RAND";
  	if (sym_num == 10) return "DISP";
  	if (sym_num == 11) return "HALT";
  	if (sym_num == 12) return "DOT_TEXT";
  	if (sym_num == 13) return "RPAREN";
  	if (sym_num == 14) return "LPAREN";
  	if (sym_num == 15) return "COLON";
  	if (sym_num == 16) return "COMMA";
  	if (sym_num == 17) return "R0";
  	if (sym_num == 18) return "R1";
  	if (sym_num == 19) return "R2";
  	if (sym_num == 20) return "R3";
  	if (sym_num == 21) return "ID";
    //if (sym_num == 22) return "INT";
    else return "INT";
  }
}
