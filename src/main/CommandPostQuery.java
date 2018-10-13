package main;


public class CommandPostQuery<TKey> implements ICommand<TKey> {

	private final String name;
	private final TwoArgsFunction<Integer, Integer, Integer> function;
	private final TKey key;
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
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
	
	public CommandPostQuery(TKey key, String name, TwoArgsFunction<Integer, Integer, Integer> executedFunction) {
		function = executedFunction;
		this.name = name;
		this.key = key;
	}

	@Override
	public TKey getKey() {
		return key;
	}
}

@FunctionalInterface
interface TwoArgsFunction<T1, T2, T3> {
	public T3 apply(T1 left, T2 right);
}