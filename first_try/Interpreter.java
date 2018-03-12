import java_cup.runtime.Symbol;

public class Interpreter
{
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("What are ya doin? Gimme an input file");
			System.exit(1);
		}
		Symbol parse_list = null;
		try
		{
			MyParser parser = new MyParser(new Lexer(new java.io.FileInputStream(args[0])));
			parse_list = parser.parse();
		}
		catch (java.io.IOException e)
		{
			System.err.println("What are ya doin? That input file is a problem!");
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
}