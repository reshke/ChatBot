package main;

import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;
import main.Games.CHGK_Game;
import main.Games.NumGame;
import main.Games.PseudoBase;
import main.Games.StringGuessGame;
import main.IO.Reader;

public class CommonUserDialog implements IDialogCommon {
	private IDialogGame currentGameDialog;
	private final ICommandContainer<String> commandContainer;
	private final CommandContainer<String> senderCommandContainer = new CommandContainer<String>();
	private IResult previousAnswer;
	
	public CommonUserDialog() {
		commandContainer = new CommandContainer<String>();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList<String>("gamesList", "gamesList"));
	}
	
	public void switchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GUESS_STRING: currentGameDialog = 
				new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
				break;
		case NUM_GAME: 
			currentGameDialog = new NumGameDialog(new NumGame(new RandomGenerator()), new GamesHelper(new Reader()));
			break;
						  
		case CHGK_Game: 
			currentGameDialog = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
			break;
				
		default: throw new IllegalArgumentException("Unknown game");
		}
	}
	
	private IResult executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult result = commandContainer.executeCommand(arguments[0], arguments);
		if (currentGameDialog == null)
			return result;
		if (result.getState() == ResultState.UNKNOWN)
			return currentGameDialog.postQuery(arguments);  //senderCommandContainer.executeCommand(arguments[0], arguments);
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE)
		{
			IResult senderResult = currentGameDialog.postQuery(arguments);
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
	public IResult handleQuery(String query) {
		previousAnswer = executeQuery(query);
		return previousAnswer;
	}

	@Override
	public IResult getLastAnswer() {
		return previousAnswer;
	}
}
