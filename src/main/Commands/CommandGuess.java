package main.Commands;
import java.util.function.Function;

import main.ICommand;

public class CommandGuess<TKey> implements ICommand<TKey> {

	private final String name;
	private final Function<String, Boolean> function;
	private final TKey key;
	
	public CommandGuess(TKey key, String name, Function<String, Boolean> function) {
		this.name = name;
		this.function = function;
		this.key = key;
	}
	
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Count of arguments is not correct");
		return function.apply(args[1])
				? "You guessed right!"
				: "You are wrong!";
	}


	@Override
	public TKey getKey() {
		return key;
	}

}
