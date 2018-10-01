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
	
	public ResultInformation ExecuteQuery(String query) {
		throw new NotImplementedException();
	}
	
}
