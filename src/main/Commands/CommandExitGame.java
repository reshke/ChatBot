package main.Commands;

import main.ICommand;

public class CommandExitGame implements ICommand {
	private final String name;
	private final Procedure function;
	public CommandExitGame(String name, Procedure function) {
		this.name = name;
		this.function = function;
	}
	
	@Override
	public String GetCommandName() {
		return name;
	}	
	
	@Override
	public String ExecuteCommand(String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Count of arguments is uncorrect(Expected zero arguments)");
		function.invoke();
		return "You exited game";
	}
	
	public interface Procedure {
		void invoke();
	}
	
}

