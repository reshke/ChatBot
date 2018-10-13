package main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandSender<TKey> implements ICommandSender<TKey> {

	private final Map<TKey, Function<String[], IResult>> functions = new HashMap<TKey, Function<String[], IResult>>();
	
	
	@Override
	public void addCommandSender(TKey key, Function<String[], IResult> function) {
		functions.put(key, function);
	}

	@Override
	public IResult executeCommand(TKey key, String[] args) {
		if (!functions.containsKey(key))
			return new ResultInformation("Unknown command", ResultState.UNKNOWN);
		return functions.get(key).apply(args);
	}

	@Override
	public void clear() {
		functions.clear();
	}

}
