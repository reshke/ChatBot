package main.Commands;
import java.util.HashMap;
import java.util.List;
import main.ICommand;
import main.ResultInformation;
import main.ResultState;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandContainer {
	private final HashMap<String, ICommand> commandContainer = new HashMap<String, ICommand>();
	
	public CommandContainer(List<ICommand> commands) {
		for (ICommand command: commands) 
			commandContainer.put(command.getCommandName(), command);
	}
	
	public CommandContainer() {
	}
	
	public void addCommand(ICommand command) {
		commandContainer.put(command.getCommandName(), command);
	}
	
	public void addSetOfCommands(ICommand commands[]){
		for (ICommand command : commands)
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
	
}
