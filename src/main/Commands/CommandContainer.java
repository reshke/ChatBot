package main.Commands;
import java.util.HashMap;
import java.util.List;
import main.ICommand;
import main.ICommandContainer;
import main.IResult;
import main.ResultInformation;
import main.ResultState;

public class CommandContainer<TValue> implements ICommandContainer<TValue> {
	private final HashMap<TValue, ICommand<TValue>> commandContainer = new HashMap<TValue, ICommand<TValue>>();
	
	public CommandContainer(List<ICommand<TValue>> commands) {
		for (ICommand<TValue> command: commands) 
			commandContainer.put(command.getKey(), command);
	}
	
	public CommandContainer() {}
	
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
		if (!commandContainer.containsKey(value))
			return new ResultInformation("Unknown command! Read /help!", ResultState.UNKNOWN);
		ICommand<TValue> command = commandContainer.get(value);
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
