package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import main.Commands.CommandExitGame;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;
import main.classLoader.ModuleLoader;

public class CommonUserDialog implements IDialogCommon {
//	protected GameInfo currentGameIngo;
	private Game currentGame;
	private final ICommandContainer commandContainer;
	private IResult<String> previousAnswer;
	private final ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\bin\\main\\Games\\");
	private HashMap<String, Game> games = new HashMap<String, Game>();

	
	public CommonUserDialog() {
		commandContainer = new CommandContainer();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
		
		try {
			for (Game game : this.moduleLoader.loadGames())
			{
				this.games.put(game.gameName(), game);
			}
		} catch (IllegalArgumentException | SecurityException e) {
		}
	}
	
	public String getGamesList(String[] args)
	{
		StringBuilder result = new StringBuilder("Realized games list: \n");
		
		for (String gameName : this.games.keySet()) {
			result.append(gameName);
			result.append(this.games.get(gameName).getGameDescriptor());
			result.append("\n");
		}
		
		return result.toString();
	}
	
	public void switchGame(String typeGame) {
		try {
			this.currentGame = (Game) this.moduleLoader.findClass(typeGame).newInstance();
			this.games.put(this.currentGame.gameName(), this.currentGame);
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			this.currentGame = null;
		}
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
}
