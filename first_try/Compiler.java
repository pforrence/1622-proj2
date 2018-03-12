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

  	if (sym_num == 0) return "ROP";
  	if (sym_num == 1) return "IOP";
  	if (sym_num == 2) return "IOPM";
  	if (sym_num == 3) return "JOP";
  	if (sym_num == 4) return "DOP";
  	if (sym_num == 5) return "HOP";
  	if (sym_num == 6) return "DOT_TEXT";
  	if (sym_num == 7) return "RPAREN";
  	if (sym_num == 8) return "LPAREN";
  	if (sym_num == 9) return "COLON";
  	if (sym_num == 10) return "COMMA";
  	if (sym_num == 11) return "REG";
  	if (sym_num == 12) return "ID";
    else return "INT";
  }
}
