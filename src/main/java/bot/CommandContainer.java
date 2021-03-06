package bot;

import java.io.Serializable;
import java.util.List;

import finders.DictionaryItem;
import finders.DictionaryItems;
import finders.DictionaryWithAdditionalSearch;
import finders.DictionaryWithSimilarLinesDifference;
import finders.FinderSimilarDistanceWords;
import finders.FinderSimilarLines;
import finders.LevenshteinDifference;


public class CommandContainer implements ICommandContainer, Serializable {

	private static final long serialVersionUID = 5256811346286329141L;
	private final DictionaryWithAdditionalSearch<ICommand<String>> dictionary;
	
	public CommandContainer(List<ICommand<String>> commands) {
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(new LevenshteinDifference(), 1, 3);
		dictionary = new DictionaryWithSimilarLinesDifference<ICommand<String>>(finderSimilarLines);
		for (ICommand<String> command: commands) 
			dictionary.put(command.getKey().toString(), command);
	}
	
	public CommandContainer() {
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(new LevenshteinDifference(), 1, 3);
		dictionary = new DictionaryWithSimilarLinesDifference<ICommand<String>>(finderSimilarLines);
	}
	
	public CommandContainer(ICommand<String>[] commands) {
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(new LevenshteinDifference(), 1, 3);
		dictionary = new DictionaryWithSimilarLinesDifference<ICommand<String>>(finderSimilarLines);
		for (int i = 0; i < commands.length; i++) 
			dictionary.put(commands[i].getKey().toString(), commands[i]);
	}

	public void addCommand(ICommand<String> command) {
		dictionary.put(command.getKey(), command);
	}
	
	public void addSetOfCommands(ICommand<String> commands[]){
		for (ICommand<String> command : commands)
			dictionary.put(command.getKey().toString(), command);
	}
	
	public void clear() {
		dictionary.clear();
	}
	
	private IResult<String> handleNotExistingCommand(List<DictionaryItem<ICommand<String>>> possibleCommands)
	{
		StringBuilder messageWithTips = new StringBuilder();
		for(DictionaryItem<ICommand<String>> command: possibleCommands)
			messageWithTips.append(command.key.toString() + " ");
		return new Result("Unknown command! Maybe you mean: " + messageWithTips.toString(), ResultState.POSSIBLE_MISTAKE);
	}
	
	private IResult<String> executeCommand(ICommand<String> command, String[] args) {
		try {
			String result = command.executeCommand(args);
			return new Result(result, ResultState.SUCCESS);
		}
		catch (IllegalArgumentException exception) {
			return new Result(exception.getMessage(), ResultState.WRONG_ARGUMENTS);
		}
		catch (UnsupportedOperationException exception) {
			return new Result(exception.getMessage(), ResultState.UNSUPPORTED_OPERATION);
		}
	}
	
	@Override
	public IResult<String> executeCommand(String value, String[] args) {
		DictionaryItems<ICommand<String>> commands = dictionary.get(value.toString());
		if (commands.equalItems.size() > 0)
			return executeCommand(commands.equalItems.get(0).value, args);
		if (commands.almostEqualItems.size() > 0)
			return handleNotExistingCommand(commands.almostEqualItems);
		return new Result("Unknown command!", ResultState.UNKNOWN);
	}

	@Override
	public String[] getCommands() {
		return this.dictionary.getKeyArray();
	}
	
}
