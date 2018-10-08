package main.Commands;
import java.util.function.Consumer;

import main.ICommand;

public class CommandEndGame implements ICommand {
	private final String name;
	private final Consumer<String> function;
	
	
	public CommandEndGame(String name, Consumer<String> function) {
		this.name = name;
		this.function = function;
	}
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1)
			throw new IllegalArgumentException("Count of arguments is not correct");
		
		try
		{ 
			function.accept("");
		}catch (UnsupportedOperationException e)
		{
			throw e;
		}
		
		return "Game was ended!";
	}

}
