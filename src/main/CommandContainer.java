package main;
import java.util.HashMap;
import java.util.List;


public class CommandContainer<TValue> implements ICommandContainer<TValue> {
	private final HashMap<TValue, ICommand<TValue>> commandContainer = new HashMap<TValue, ICommand<TValue>>();
	private final ICommandFinder<TValue> finder;
	
	
	public CommandContainer(List<ICommand<TValue>> commands, ICommandFinder<TValue> finder) {
		for (ICommand<TValue> command: commands) 
			commandContainer.put(command.getKey(), command);
		this.finder = finder;
	}
	
	public CommandContainer(ICommandFinder<TValue> finder) {this.finder = finder;}
	
	public void addCommand(ICommand<TValue> command) {
		commandContainer.put(command.getKey(), command);
	}
	
	public void addSetOfCommands(ICommand<TValue> commands[]){
		for (ICommand<TValue> command : commands)
			addCommand(command);
	}
	
	public void clear() {
		commandContainer.clear();
	}
	
	@Override
	public IResult executeCommand(TValue value, String[] args) {
		FindResult findResult = this.finder.getCommandByName(value, commandContainer);
		if (findResult.getState() == ResultState.UNKNOWN)
			return new ResultInformation("Unknown command! Read /help!", ResultState.UNKNOWN);
		ICommand<TValue> command = findResult.getResult();
		try {
			String result = command.executeCommand(args);
			return new ResultInformation(result, ResultState.SUCCESS);
		}
		catch (IllegalArgumentException exception) {
			return new ResultInformation(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
		catch (UnsupportedOperationException exception) {
			return new ResultInformation(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
	}
	
}
