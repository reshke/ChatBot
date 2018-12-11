package userDialog;

import java.util.HashMap;
import java.util.Map.Entry;

import Commands.Command;
import Commands.CommandExitGame;
import Commands.LoadCommand;
import IO.IGameSaver;
import bot.Game;
import bot.IDialogCommon;
import bot.IResult;
import bot.ResultState;

public class CommonUserDialog implements IDialogCommon {
	private Game currentGame;
	private final Long userId;
	private final ICommandContainer commandContainer;
	private final IGameSaver gameSaver;
	private IResult<String> previousAnswer;
	private HashMap<String, IGameFactory> games = new HashMap<String, IGameFactory>();

	
	public CommonUserDialog(HashMap<String, IGameFactory> games, ICommandContainer commandContainer,
			IGameSaver gamesSaver, Long userId) {
		this.commandContainer = commandContainer;
		this.gameSaver = gamesSaver;
		
		commandContainer.addCommand(new Command("switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
		commandContainer.addCommand(new LoadCommand((name) -> this.loadGame(name)));
		commandContainer.addCommand(new Command("save", (x) ->  this.saveCurrentGame(x)));
		this.commandContainer.addCommand(new Command("savesList", (x) -> this.getSavesList(x)));
		
		this.games = games;
		
		for (Entry<String, IGameFactory> game : this.games.entrySet())
			gameSaver.registerGame(game.getKey(), game.getValue().Create().getClass());
		
		this.userId = userId;
	}
	
	private IResult<String> getSavesList(String[] x) {
		return new Result(this.gameSaver.getSavesList(userId));
	}

	private IResult<String> getGamesList(String[] args)
	{
		StringBuilder result = new StringBuilder("Realized games list: \n");
		
		for (String gameName : this.games.keySet()) {
			result.append(this.games.get(gameName).Create().getGameDescriptor().getResult());
			result.append("\n\n\n");
		}
		
		return new Result(result.toString(), ResultState.SUCCESS);
	}
	
	public IResult<String> switchGame(String[] args) {
		if (args.length != 2)
			return new Result("Count of args is not correct!", ResultState.UNSUPPORTED_OPERATION);
		String gameName = args[1];
		Game game = this.games.get(gameName).Create();
		if (game == null)
			return new Result("Incorrect game name " + gameName + ": no such game founded!" , ResultState.UNSUPPORTED_OPERATION);
		
		this.currentGame = game;
		return new Result("Game swithed to " + gameName);
	}
	
	private IResult<String> executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult<String> result = commandContainer.executeCommand(arguments[0], arguments);
		if (this.currentGame == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN) {
			return this.currentGame.executeQuery(arguments);
		}
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE){
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
	
	public IResult<String> saveCurrentGame(String[] args){
		currentGame.pauseGame();
		return gameSaver.saveGame(this.currentGame, userId, args);		
	}

	@Override
	public Game LoadGame(String name) throws ClassNotFoundException {
		return this.gameSaver.LoadGame(this.currentGame.gameName().getResult(), this.userId, name);
	}
	
	public String loadGame(String name) {
		try {
			this.currentGame = this.LoadGame(name);
			if (this.currentGame == null)
				return "try again later!";
		} catch (ClassNotFoundException e) {
			return "Invalid game name: " + name;
		}
		
		return "Your game was loaded sucessfully!";
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
		if (this.currentGame != null)
			return new String[] {"help", "gamesList", "gamehelp", "save", "load", "savesList"};
		return new String[] {"help", "gamesList", "savesList"};
	}
}