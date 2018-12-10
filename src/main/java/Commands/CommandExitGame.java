package Commands;

import userDialog.ICommand;

public class CommandExitGame<TValue> implements ICommand<TValue> {
	private final String name;
	private final Procedure function;
	private final TValue key;
	
	public CommandExitGame(TValue key, String name, Procedure function) {
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
			throw new IllegalArgumentException("Count of arguments is uncorrect(Expected zero arguments)");
		function.invoke();
		return "You exited game";
	}
	
	@Override
	public TValue getKey() {
		return key;
	}
	
	public interface Procedure {
		void invoke();
	}
}

