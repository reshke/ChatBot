package main;

import java.util.function.Function;

public interface ICommandSender<TKey> {
	public void addCommandSender(TKey key, Function<String[], IResult<Object>> commandFunction);
	public IResult<String> executeCommand(TKey key, String[] args);
	public void clear();
}
