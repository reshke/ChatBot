package main.Commands;
import java.util.function.Function;

import main.ICommand;

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
			throw new IllegalArgumentException("Count of arguments is not correct");
		
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
