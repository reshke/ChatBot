package main;

import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;
import main.Games.CHGK_Game;
import main.Games.NumGame;
import main.Games.PseudoBase;
import main.IO.Reader;
import main.classLoader.ModuleEngine;

public class CommonUserDialog implements IDialogCommon {
	private IDialogGame currentGameDialog;
	private final ICommandContainer commandContainer;
	private IResult<String> previousAnswer;
	protected final ModuleEngine moduleEngine = new ModuleEngine();

	
	public CommonUserDialog() {
		commandContainer = new CommandContainer();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame2(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList<String>("gamesList", "gamesList"));
	}
	
	public void switchGame2(String typeGame) {
		int a = 1;
		
		this.moduleEngine.loadClass(new String[] {"C:\\Users\\rockl\\Desktop\\java\\ChatBot\\bin\\", typeGame});
	}
	
	
	public void switchGame(TypeGame typeGame) {}
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
		if (currentGameDialog == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN)
			return currentGameDialog.postQuery(arguments);  //senderCommandContainer.executeCommand(arguments[0], arguments);
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE)
		{
			IResult<String> senderResult = currentGameDialog.postQuery(arguments);
			if (senderResult.getState() != ResultState.UNKNOWN)
				return senderResult;
		}
		return result;
	}
	
	public void exitGame() {
		if (currentGameDialog == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		currentGameDialog = null;
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
