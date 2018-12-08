package main;

import java.util.HashMap;
import java.util.Map.Entry;

import main.Commands.Command;
import main.Commands.CommandExitGame;
import main.Commands.LoadCommand;

public class CommonUserDialog implements IDialogCommon {
	private Game currentGame;
	private final ICommandContainer commandContainer;
	private final IGameSaver gameSaver;
	private IResult<String> previousAnswer;
	private HashMap<String, Game> games = new HashMap<String, Game>();

	
	public CommonUserDialog(HashMap<String, Game> games, ICommandContainer commandContainer,
			IGameSaver gamesSaver) {
		this.commandContainer = commandContainer;
		this.gameSaver = gamesSaver;
		
		commandContainer.addCommand(
				new Command("switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
		commandContainer.addCommand(new LoadCommand((name) -> this.loadGame(name)));
		commandContainer.addCommand(new Command("save", (x) ->  this.saveCurrentGame(x)));
		
		this.games = games;
		
		for (Entry<String, Game> game : this.games.entrySet())
			gameSaver.registerGame(game.getKey(), game.getValue().getClass());
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
	
	public IResult<String> switchGame(String[] args) {
		if (args.length != 2)
			return new Result("Count of args is not correct!", ResultState.UNSUPPORTED_OPERATION);
		String typeGame = args[1];
		Game game = this.games.get(typeGame);
		if (game == null)
			return new Result("Incorrect game name " + typeGame + ": no such game founded!" , ResultState.UNSUPPORTED_OPERATION);
		
		this.currentGame = game;
		return new Result("Game swithed to " + typeGame);
	}
	
	private IResult<String> executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult<String> result = commandContainer.executeCommand(arguments[0], arguments);
		if (this.currentGame == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN)
			return this.currentGame.executeQuery(arguments);
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
	
	private String getSaveName() {
		return this.currentGame.gameName().getInfo();
	}
	
	public IResult<String> saveCurrentGame(String[] args){
		if (args.length > 2)
			return new Result("Count of args is not correct!", ResultState.WRONG_ARGUMENTS);
		if (this.currentGame == null)
			return new Result("Game is not chosen!");
		
		if (args.length == 2)
		{
			return new Result(this.saveCurrentGame(args[1]));
		}
			
		String res = this.saveCurrentGame(this.getSaveName());
		
		return new Result(res);
	}
	
	public String saveCurrentGame(String name){
		currentGame.pauseGame();
		gameSaver.saveGame(this.currentGame, name);

		return "Your game was saved sucessfully!";
	}

	@Override
	public Game LoadGame(String name) {
		return this.gameSaver.LoadGame(this.currentGame.gameName().getResult(), name);
	}
	
	public String loadGame(String name) {
		this.currentGame = this.LoadGame(name);
		
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
			return new String[] {"help", "gamesList", "gamehelp", "save", "load"};
		return new String[] {"help", "gamesList"};
	}
}