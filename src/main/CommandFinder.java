package main;

import java.util.HashMap;

import main.Commands.CommandHelp;

public class CommandFinder<TValue> implements ICommandFinder<TValue> {

	@Override
	public FindResult getCommandByName(TValue value, HashMap<TValue, ICommand<TValue>> commandContainer) {
		// TODO Auto-generated method stub
		Boolean res = commandContainer.containsKey(value);
		if (res) {
			return new FindResult(commandContainer.get(value) , ResultState.SUCCESS);
		}
		else {
			if (value instanceof String) {
				StringFuzzyMapper mapper = new StringFuzzyMapper();
				
				for (TValue commandName : commandContainer.keySet()){
					if (mapper.fuzzyMapStrings(commandName.toString(), value.toString())) {
						return new FindResult(commandContainer.get(commandName) , ResultState.SUCCESS);
					}
				}
				return new FindResult(new CommandHelp<String>("help", "help"), ResultState.UNKNOWN);
			}
			else
			{
				return new FindResult(new CommandHelp<String>("help", "help"), ResultState.UNKNOWN);
			}
		}
	}
}
