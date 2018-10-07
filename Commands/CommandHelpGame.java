import java.io.File;
import java.util.function.Function;

public class CommandHelpGame implements ICommand {
	private final String name;
	private final Function<String, String> function;
	
	public CommandHelpGame(String name, Function<String, String> function) {
		this.name = name;
		this.function = function;
	}
	
	@Override
	public String GetCommandName() {
		return name;
	}

	@Override
	public String ExecuteCommand(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1)
			throw new IllegalArgumentException("Count of arguments is not correcT");
		
		try
		{ 
			return function.apply("");
		}
		catch (UnsupportedOperationException e)
		{
			throw e;
		}
	}

}
