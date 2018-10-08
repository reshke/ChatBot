package main;

@FunctionalInterface
interface Function<T1, T2, T3> {
	public T3 apply(T1 left, T2 right);
}

public class CommandPostQuery implements ICommand {

	private final String name;
	private final Function<Integer, Integer, Integer> function;
	
	
	@Override
	public String GetCommandName() {
		return name;
	}

	@Override
	public String ExecuteCommand(String[] args) {
		if (args.length != 3)
			throw new IllegalArgumentException("Count of arguments is not correct");
		Integer left, right;
		try
		{
			left = Integer.parseInt(args[1]);
			right = Integer.parseInt(args[2]);
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Arguments should be integer!");
		}
		
		return Integer.toString(function.apply(left, right));
	}
	
	public CommandPostQuery(String name, Function<Integer, Integer, Integer> executedFunct) {
		this.function = executedFunct;
		this.name = name;
	}
}

