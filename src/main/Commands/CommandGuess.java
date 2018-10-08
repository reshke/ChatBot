package main.Commands;
import java.util.function.Function;

import main.ICommand;

public class CommandGuess implements ICommand {

	private final String name;
	private final Function<String, Boolean> function;
	
	
	public CommandGuess(String name, Function<String, Boolean> function) {
		this.name = name;
		this.function = function;
	}
	
	
	@Override
	public String GetCommandName() {
		return name;
	}

	@Override
	public String ExecuteCommand(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Count of arguments is not correct");
		return function.apply(args[1])
				? "You guessed right!"
				: "You are wrong!";
	}

}
