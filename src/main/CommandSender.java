package main;

import java.util.ArrayList;
import java.util.function.Function;

import org.glassfish.grizzly.utils.Pair;

public class CommandSender<TKey> implements ICommandSender<TKey> {

	private final FuzzyDictionary<TKey, Function<String[], IResult>> functions = new LevenshteinDictionary<TKey, Function<String[], IResult>>();
	
	@Override
	public void addCommandSender(TKey key, Function<String[], IResult> function) {
		functions.put(key, function);
	}

	private IResult handleNotExistingCommand(TKey value)
	{
		ArrayList<Pair<TKey, Function<String[], IResult>>> list = functions.get(value, 3);
		if (list.size() == 0)
			return new Result("Unknown command! Read help!", 
					ResultState.UNKNOWN);
		StringBuilder builder = new StringBuilder();
		for (Pair<TKey, Function<String[], IResult>> item: list)
		{
			builder.append(" " + item.getFirst().toString());
		}
		return new Result("Unknown command! Maybe you mean: " + builder.toString(), 
				ResultState.POSSIBLE_MISTAKE);
	}
	
	@Override
	public IResult executeCommand(TKey value, String[] args) {
		ArrayList<Pair<TKey, Function<String[], IResult>>> list = functions.get(value, 1);
		if (list.size() == 0)
			return handleNotExistingCommand(value);
		return list.get(0).getSecond().apply(args);
	}

	@Override
	public void clear() {
		functions.clear();
	}

}
