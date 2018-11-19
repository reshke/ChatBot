package main;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.grizzly.utils.Pair;


public class CommandContainer<TValue> implements ICommandContainer<TValue> {
	private final FuzzyDictionary<TValue, ICommand<TValue>> commandContainer = new LevenshteinDictionary<TValue, ICommand<TValue>>();
	
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
			commandContainer.put(command.getKey(), command);
	}
	
	public void clear() {
		commandContainer.clear();
	}
	
	private IResult<TValue> handleNotExistingCommand(TValue value)
	{
		ArrayList<Pair<TValue, ICommand<TValue>>> list = commandContainer.get(value, 3);
		if (list.size() == 0)
			return new Result<TValue>("Unknown command! Read help!", ResultState.UNKNOWN);
		StringBuilder messageWithTips = new StringBuilder();
		for (Pair<TValue, ICommand<TValue>> item: list)
		{
			messageWithTips.append(" " + item.getFirst().toString());
		}
		return new Result<TValue>("Unknown command! Maybe you mean: " + messageWithTips.toString(), ResultState.POSSIBLE_MISTAKE);
	}
	
	private IResult<TValue> executeCommand(ICommand<TValue> command, String[] args) {
		try {
			String result = command.executeCommand(args);
			return new Result<TValue>(result, ResultState.SUCCESS);
		}
		catch (IllegalArgumentException exception) {
			return new Result<TValue>(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
		catch (UnsupportedOperationException exception) {
			return new Result<TValue>(exception.getMessage(), ResultState.UNSUPPORTED_OPERATION);
		}
	}
	
	@Override
	public IResult<TValue> executeCommand(TValue value, String[] args) {
		ArrayList<Pair<TValue, ICommand<TValue>>> commands = commandContainer.get(value, 1);
		if (commands.size() == 0)
			return (IResult<TValue>) handleNotExistingCommand(value);
		ICommand<TValue> command = commands.get(0).getSecond();
		return executeCommand(command, args);
	}
	
}
