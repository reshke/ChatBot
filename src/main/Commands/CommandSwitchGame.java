package main.Commands;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import main.ICommand;

public class CommandSwitchGame<TKey> implements ICommand<TKey> {

	private final TKey key;
	private final String name;
	private final Consumer<String> function;
	
	public CommandSwitchGame(TKey key, String name, Consumer<String> function) {
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
		function.accept(args[1]);
		return "Game is changed!";
	}

	@Override
	public TKey getKey() {
		return key;
	}

}
