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
	private final ICommandSender<String> senderCommandContainer = new CommandSender<String>();
	private IResult previousAnswer;
	
	public CommonUserDialog() {
		commandContainer = new CommandContainer<String>();
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList<String>("gamesList", "gamesList"));
	}
	
	private void updateSender() {
		senderCommandContainer.clear();
		senderCommandContainer.addCommandSender("start",x -> currentGameDialog.startGame(x));
		senderCommandContainer.addCommandSender("gamehelp", x -> currentGameDialog.getHelp(x));
		senderCommandContainer.addCommandSender("last", x-> currentGameDialog.getLastAnswer(x));
		senderCommandContainer.addCommandSender("ask", x -> currentGameDialog.addRequest(x));
		senderCommandContainer.addCommandSender("state", x -> currentGameDialog.getState(x));
		senderCommandContainer.addCommandSender("result", x -> currentGameDialog.sendAnswer(x));
		senderCommandContainer.addCommandSender("end", x -> currentGameDialog.stopGame(x));
		senderCommandContainer.addCommandSender("hint",x -> currentGameDialog.getHint(x));
	}
	
	public void switchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GUESS_STRING: currentGameDialog = 
				new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
						new CommandContainer<TypeAction>(), 
						new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
				updateSender();
				break;
		case NUM_GAME: currentGameDialog = 
				new DialogGame(new NumGame(new RandomGenerator()), 
						new CommandContainer<TypeAction>(), 
						new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
				updateSender();
				break;
						  
		case CHGK_Game: currentGameDialog = new DialogGame(new CHGK_Game(new PseudoBase()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
				updateSender();
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
			return senderCommandContainer.executeCommand(arguments[0], arguments);
		else if (result.getState() == ResultState.POSSIBLE_MISTAKE)
		{
			IResult senderResult = senderCommandContainer.executeCommand(arguments[0], arguments);
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
