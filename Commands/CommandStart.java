import java.util.function.Consumer;

public class CommandStart implements ICommand {

	private final Consumer<Integer> function; 
	private final String name;  
	
	
	public CommandStart(String name, Consumer<Integer> function) {
		this.function = function;
		this.name = name;
	}
	
	@Override
	public String GetCommandName() {
		return name;
	}

	@Override
	public String ExecuteCommand(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Count of arguments is not correct");
		Integer length;
		try
		{
			length = Integer.parseInt(args[1]);
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Arguments should be integer!");
		}
		function.accept(length);
		return "Game is started!";
	
	}
	
}
