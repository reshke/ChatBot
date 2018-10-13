package main.Commands;
import java.util.function.Consumer;

import main.ICommand;

public class CommandEndGame<TValue> implements ICommand<TValue> {
	private final String name;
	private final Consumer<String> function;
	private final TValue key;
	
	public CommandEndGame(TValue key, String name, Consumer<String> function) {
		this.key = key;
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
		function.accept("");
		return "Game was ended!";
	}

	@Override
	public TValue getKey() {
		return key;
	}

}
