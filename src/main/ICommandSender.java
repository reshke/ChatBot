package main;

import java.util.function.Function;

public interface ICommandSender<TKey> {
	public void addCommandSender(TKey key, Function<String[], IResult> function);
	public IResult executeCommand(TKey key, String[] args);
	public void clear();
}
