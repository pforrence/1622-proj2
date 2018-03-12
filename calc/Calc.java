import java_cup.runtime.Symbol;
import java.io.*;
import java.util.*;

public class Calc {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java Calc file.asm");
			System.exit(1);
		}
		Symbol parse_tree = null;
		try {
			/*Parsing Phase*/
			ExprParser parser_obj = new ExprParser(new ExprLex(new FileInputStream(args[0])));
			parse_tree = parser_obj.parse();

			System.out.println(CUP$ExprParser$actions.labelTable);
			Hashtable<String, Integer> labelTable= CUP$ExprParser$actions.labelTable;

			System.out.println("\nParse List: ");
			ArrayList<Instruction> list = (ArrayList<Instruction>)parse_tree.value;
			
			/*Execution Phase */
			for (int i = list.size()-1; i >= 0; i--)
			{
				Instruction temp = list.get(i);
				System.out.println(temp.toString());
				if (temp.getType() == 3)
				{
					// System.out.println(list.size()+2 -);
				    i = list.size() - labelTable.get(temp.getImmediate());
					System.out.println(i);
				}

			}
		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}