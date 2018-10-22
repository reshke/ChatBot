package main;

import java.util.HashMap;


public interface ICommandFinder<TValue> {
	public FindResult getCommandByName(TValue value, HashMap<TValue, ICommand<TValue>> commandContainer);	
}
