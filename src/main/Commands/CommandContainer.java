package main.Commands;
import java.util.HashMap;
import java.util.List;
import main.ICommand;
import main.ICommandContainer;
import main.IResult;
import main.ResultInformation;
import main.ResultState;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
	
	public ResultInformation executeQuery(String query) {
		String[] argumentsQuery = query.split(" ");
		if (argumentsQuery.length == 0)
			return new ResultInformation("No command", ResultState.UNKNOWN);
		String nameCommand = argumentsQuery[0];
		if (!commandContainer.containsKey(nameCommand))
			return new ResultInformation("Unknown command! Read /help!", ResultState.UNKNOWN);
		ICommand command = commandContainer.get(nameCommand);
		try 
		{
			String result = command.executeCommand(argumentsQuery);
			return new ResultInformation(result, ResultState.SUCCESS);
		}
		catch (IllegalArgumentException | UnsupportedOperationException exception)
		{
			return new ResultInformation(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
	}
	
	

	@Override
	public IResult executeCommand(TValue value, String[] args) {
		throw new NotImplementedException();
	}
	
}
