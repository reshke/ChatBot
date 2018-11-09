package main.Commands;
import java.util.function.Function;

import main.ICommand;

public class CommandHelpGame<TKey> implements ICommand<TKey> {
	private final String name;
	private final Function<String, String> function;
	private final TKey key;
	
	public CommandHelpGame(TKey key, String name, Function<String, String> function) {
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
		if (args.length != 1)
			throw new IllegalArgumentException("Count of arguments is not correct");
		return function.apply("");
		
	}

	@Override
	public TKey getKey() {
		return key;
	}

}
