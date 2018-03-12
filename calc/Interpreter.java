import java_cup.runtime.Symbol;
import java.io.*;
import java.util.*;

public class Interpreter {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java Interpreter file.asm");
			System.exit(1);
		}
		Symbol parse_tree = null;
		try {
			/*Parsing Phase*/
			ExprParser parser_obj = new ExprParser(new ExprLex(new FileInputStream(args[0])));
			parse_tree = parser_obj.parse();

			// System.out.println(CUP$ExprParser$actions.labelTable);
			Hashtable<String, Integer> labelTable= CUP$ExprParser$actions.labelTable;

			// System.out.println("\nParse List: ");
			ArrayList<Instruction> list = (ArrayList<Instruction>)parse_tree.value;
			
			Random rand = new Random();



			byte[] reg = new byte[4]; 
			byte[] rAM = new byte[256];

			/*Execution Phase */
			for (int i = list.size()-1; i >= 0; i--)
			{
				Instruction temp = list.get(i);
				// System.out.println(temp.toString());
				int type = temp.getType();
				/*Rtype*/
				if (type == 0)
				{
					if (temp.getOp().equals("add"))
						reg[temp.getRd()] = (byte)(reg[temp.getRs()] + reg[temp.getRt()]);
					else if (temp.getOp().equals("sub"))
						reg[temp.getRd()] = (byte)(reg[temp.getRs()] - reg[temp.getRt()]);
					else if (temp.getOp().equals("slt"))
					{
						reg[temp.getRd()] = (byte)0;	
						if (reg[temp.getRs()] < reg[temp.getRt()])
						   reg[temp.getRd()] = (byte)1;	
					}		
	                else if (temp.getOp().equals("xor"))
						reg[temp.getRd()] = (byte)(reg[temp.getRs()] ^ reg[temp.getRt()]);			
				}
				/*Itype*/
				else if (type == 1)
				{
					if (temp.getOp().equals("addi"))
						reg[temp.getRt()] = (byte)(reg[temp.getRs()] + Integer.parseInt(temp.getImmediate()));
					if (temp.getOp().equals("beq"))
					{
						if (reg[temp.getRs()] == reg[temp.getRt()])
							i = list.size() - labelTable.get(temp.getImmediate());

					}

				}
				/*IMtype*/
	            else if (type == 2)
	            {
					if (temp.getOp().equals("lb"))
						reg[temp.getRt()] = 
					        (byte)(rAM[reg[temp.getRs()]+Integer.parseInt(temp.getImmediate())]);
					else if (temp.getOp().equals("sb"))
						rAM[reg[temp.getRs()]+Integer.parseInt(temp.getImmediate())] = (byte)(reg[temp.getRt()]);

	            }
				/* Jtype */
				else if (type == 3)
				{
				    i = list.size() - labelTable.get(temp.getImmediate());
				}
				/*Dtype*/
		        else if (type == 4)
		        {
		        	System.out.println("Register_"+temp.getRs()+": "+ String.format("0x%02X", reg[temp.getRs()]));
		        }
				/*Htype*/
				else if (type == 5)
				{
					System.exit(0);
				}
				/*Raotype*/
				else if (type == 6)
				{
					reg[temp.getRt()] = (byte)rand.nextInt(reg[temp.getRs()]);
				}
			}
		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
