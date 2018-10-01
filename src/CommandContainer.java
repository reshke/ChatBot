import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandContainer {
	private final HashMap<String, ICommand> commandContainer = new HashMap<String, ICommand>();
	
	public CommandContainer(List<ICommand> commands) {
		for (ICommand command: commands) 
			commandContainer.put(command.GetCommandName(), command);
	}
	
	public void AddCommand(ICommand command) {
		commandContainer.put(command.GetCommandName(), command);
	}
	
	public void Clear() {
		commandContainer.clear();
	}
	
	public ResultInformation ExecuteQuery(String query) {
		String[] argumentsQuery = query.split(" ");
		if (argumentsQuery.length == 0)
			return new ResultInformation("No command", ResultState.Unknowm);
		String nameCommand = argumentsQuery[0];
		if (!commandContainer.containsKey(nameCommand))
			return new ResultInformation("Unknown command! Read /help!", ResultState.Unknowm);
		ICommand command = commandContainer.get(nameCommand);
		try 
		{
			String result = command.ExecuteCommand(argumentsQuery);
			return new ResultInformation(result, ResultState.Success);
		}
		catch (IllegalArgumentException exception)
		{
			return new ResultInformation(exception.getMessage(), ResultState.WrongArguments);
		}
	}
	
}
