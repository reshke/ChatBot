package main;

import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;
import main.Games.PseudoBase;
import main.IO.Reader;
import main.classLoader.ModuleLoader;;

public class CommonUserDialog implements IDialogCommon {
	private Game currentGame;
	private final ICommandContainer commandContainer;
	private IResult<String> previousAnswer;
	private final ModuleLoader moduleLoader = new ModuleLoader("C:\\Users\\rockl\\Desktop\\java\\ChatBot\\bin\\");

	
	public CommonUserDialog() {
		commandContainer = new CommandContainer();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame2(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList<String>("gamesList", "gamesList"));
	}
	
	public void switchGame2(String typeGame) {
		try {
			this.currentGame = this.moduleLoader.findClass(typeGame).newInstance();
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
			this.currentGame = null;
				e.printStackTrace();
		}
	}
	
	
//	public void switchGame(TypeGame typeGame) {}
//		switch(typeGame) {
//		case GUESS_STRING: currentGameDialog = 
//				new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
//				break;
//		case NUM_GAME: 
//			currentGameDialog = new NumGameDialog(new NumGame(new RandomGenerator()), new GamesHelper(new Reader()));
//			break;
//						  
//		case CHGK_Game: 
//			currentGameDialog = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
//			break;
//				
//		default: throw new IllegalArgumentException("Unknown game");
//		}
//	}
	
	private IResult<String> executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult<String> result = commandContainer.executeCommand(arguments[0], arguments);
		if (this.currentGame == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN)
			return this.currentGame.postQuery(arguments);  //senderCommandContainer.executeCommand(arguments[0], arguments);
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE)
		{
			IResult<String> senderResult = this.currentGame.postQuery(arguments);
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
