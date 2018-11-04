package main;

import java.util.ArrayList;
import java.util.HashMap;
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
			addCommand(command);
	}
	
	public void clear() {
		commandContainer.clear();
	}
	
	
	private IResult handleNotExistingCommand(TValue value)
	{
		ArrayList<Pair<TValue, ICommand<TValue>>> list = commandContainer.get(value, 3);
		if (list.size() == 0)
			return new ResultInformation("Unknown command! Read help!", 
					ResultState.UNKNOWN);
		StringBuilder builder = new StringBuilder();
		for (Pair<TValue, ICommand<TValue>> item: list)
		{
			builder.append(" " + item.getFirst().toString());
		}
		return new ResultInformation("Unknown command! Maybe you mean: " + builder.toString(), 
				ResultState.POSSIBLE_MISTAKE);
	}
	
	@Override
	public IResult executeCommand(TValue value, String[] args) {
		ArrayList<Pair<TValue, ICommand<TValue>>> list = commandContainer.get(value, 1);
		if (list.size() == 0)
			return handleNotExistingCommand(value);
		ICommand<TValue> command = list.get(0).getSecond();
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
