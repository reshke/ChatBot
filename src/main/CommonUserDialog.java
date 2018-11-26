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
	private HashMap<String, String> gameNames = new HashMap<String, String>();

	
	public CommonUserDialog() {
		commandContainer = new CommandContainer();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new Command("gamesList", (x) -> this.getGamesList(x)));
	}
	
	public String getGamesList(String[] args)
	{
		StringBuilder result = new StringBuilder("Realized games list: \n");
		
		for (String value : this.gameNames.keySet()) {
			result.append(value);
			result.append(this.gameNames.get(value));
			result.append("\n");
		}
		
		return result.toString();
	}
	
	public void switchGame(String typeGame) {
		try {
//			try {
//				Game game =  (Game) this.moduleLoader.findClass(typeGame).getConstructors()[0].newInstance();
//			} catch (IllegalArgumentException | InvocationTargetException | SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Game game = 
			this.currentGame = this.moduleLoader.findClass(typeGame).newInstance();
			this.gameNames.put(this.currentGame.gameName(), this.currentGame.getHelp());
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
