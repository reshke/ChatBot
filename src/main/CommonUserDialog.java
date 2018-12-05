package main;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.Commands.Command;
import main.Commands.CommandExitGame;
import main.Commands.CommandSwitchGame;
import main.Commands.SaveAndPauseGameCommand;

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
				new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
		this.commandContainer.addCommand(new SaveAndPauseGameCommand(() -> {
			try {
				return this.saveCurrentGame();
			} catch (IOException e) {
				e.printStackTrace();
				return "IO error occured";
			}
		}));
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
	
	public String saveCurrentGame() throws JsonGenerationException, JsonMappingException, IOException {
		if (this.currentGame == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		this.currentGame.pauseGame();
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		this.gameSaver.saveGame(
				mapper.writeValueAsBytes(this.currentGame), "name");

		return "Your game was saved sucessfully!";
	}
	
	public String loadGame(String name) {
		this.gameSaver.LoadGame(name);
		
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
			return new String[] {"help", "gamesList", "gamehelp"};
		return new String[] {"help", "gamesList"};
	}
}