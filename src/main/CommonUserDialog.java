package main;

import java.util.HashMap;

import main.Commands.Command;
import main.Commands.CommandExitGame;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;

public class CommonUserDialog implements IDialogCommon {
	private Game currentGame;
	private final ICommandContainer commandContainer;
	private IResult<String> previousAnswer;
	private HashMap<String, Game> games = new HashMap<String, Game>();

	
	public CommonUserDialog(HashMap<String, Game> games) {
		commandContainer = new CommandContainer();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
		this.games = games;
		
	}
	
	public IResult<String> getGamesList(String[] args)
	{
		StringBuilder result = new StringBuilder("Realized games list: \n");
		
		for (String gameName : this.games.keySet()) {
			result.append(this.games.get(gameName).getGameDescriptor().getResult());
			result.append("\n\n\n");
		}
		
		return new Result(result.toString(), ResultState.SUCCESS);
	}
	
	public void switchGame(String typeGame) {
		this.currentGame = this.games.get(typeGame);
	}
	
	private IResult<String> executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult<String> result = commandContainer.executeCommand(arguments[0], arguments);
		if (this.currentGame == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN)
			return this.currentGame.executeQuery(arguments);  //senderCommandContainer.executeCommand(arguments[0], arguments);
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE)
		{
			IResult<String> senderResult = this.currentGame.executeQuery(arguments);
			if (senderResult.getState() != ResultState.UNKNOWN)
				return senderResult;
		}
		return result;
	}
	
	public void exitGame() {
		if (this.currentGame == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		this.currentGame = null;
	}
	
	@Override
	public IResult<String> handleQuery(String query) {
		previousAnswer = executeQuery(query);
		return previousAnswer;
	}

	@Override
	public IResult<String> getLastAnswer() {
		return previousAnswer;
	}

	@Override
	public String[] getCurrentUserExecutableCommands() {
		return this.commandContainer.getCommands();
	}
}