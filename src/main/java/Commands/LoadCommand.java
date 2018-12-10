package main.java.Commands;

import java.util.function.Function;

import main.java.bot.ICommand;

public class LoadCommand implements ICommand<String> {
	protected final Function<String, String>  supplier;
	public LoadCommand(Function<String, String> supplier){
		this.supplier = supplier;
	}

	@Override
	public String getKey() {
		return "load";
	}

	@Override
	public String getCommandName() {
		return "load";
	}

	@Override
	public String executeCommand(String[] args) {
		if (args.length != 2)
			return "Count of args is not correct!";
		return this.supplier.apply(args[1]);
	}

}
