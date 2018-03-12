public class Instruction
{
	private int type;
	private String op;
	private int rd, rs, rt;
	private String immediate;
	public String getImmediate()
	{
		return immediate;
	}
	public int getType()
	{
		return type;
	}
	public Instruction(String protoInstruction)
	{
		String[] strArray = protoInstruction.split(" ");
		// for (int i = 0; i < strArray.length; i++)
		// {
		// 	System.out.println(strArray[i]);
		// }
		op = strArray[0].toLowerCase();

		if (op.equals("add") || op.equals("sub") || op.equals("slt") || op.equals("xor"))
			rtype(strArray);
		else if (op.equals("addi") || op.equals("beq"))
			itype(strArray);
		else if (op.equals("lb") || op.equals("sb"))
			imtype(strArray);
		else if (op.equals("jump"))
			jtype(strArray);
		else if (op.equals("disp"))
			dtype(strArray);
		else if (op.equals("halt"))
			htype(strArray);
		else if (op.equals("rand"))
			raotype(strArray);
		else
		{
			System.out.println("error, opcode = " + op);
		}
	}	
	private void rtype(String[] strArray)
	{
		type = 0;
		rd = Integer.parseInt(strArray[1]);
		rs = Integer.parseInt(strArray[2]);
		rt = Integer.parseInt(strArray[3]);
	}
	private void itype(String[] strArray)
	{
		type = 1;
		rt = Integer.parseInt(strArray[1]);
		rs = Integer.parseInt(strArray[2]);
		immediate = strArray[3];
	}
	private void imtype(String[] strArray)
	{
		type = 2;
		rt = Integer.parseInt(strArray[1]);
		immediate = strArray[2];
		rs = Integer.parseInt(strArray[3]);	
	}
	private void jtype(String[] strArray)
	{
		type = 3;
		immediate = strArray[1];
	}
	private void dtype(String[] strArray)
	{
		type = 4;
		immediate = strArray[1];
	}
	private void htype(String[] strArray)
	{
		type = 5;
	}
	private void raotype(String[] strArray)
	{
		type = 6;
		rt = Integer.parseInt(strArray[1]);
		rs = Integer.parseInt(strArray[2]);
	}
	public String toString()
	{
		StringBuilder returnString = new StringBuilder(op);
		if (type == 0)
			returnString.append(" " + rd + " " + rs + " " + rt);
		else if (type == 1)
			returnString.append(" " + rt + " " + rs + " " + immediate);
		else if (type == 2)
			returnString.append(" " + rt + " " + immediate + " " + rs);
		else if (type == 3)
			returnString.append(" " + immediate);
		else if (type == 5)
			returnString.append(" " + rt + " " + rs);

		return returnString.toString();
	}
}