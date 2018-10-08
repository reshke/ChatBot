package main.Commands;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import main.ICommand;
import main.TypeGame;

public class CommandSwitchGame implements ICommand {

	private final String name;
	private final Consumer<TypeGame> function;
	private static final Map<String, TypeGame> stringConformity;
	static
	{
		stringConformity = new HashMap<String, TypeGame>();
		stringConformity.put("guessGame", TypeGame.GUESS_STRING);
	}
	
	public CommandSwitchGame(String name, Consumer<TypeGame> function) {
		this.name = name;
		this.function = function;
	}
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Count of arguments is not correct");
		if (!stringConformity.containsKey(args[1]))
			throw new IllegalArgumentException("Unknown game name!");
		function.accept(stringConformity.get(args[1]));
		return "Game is changed!";
	}

}
