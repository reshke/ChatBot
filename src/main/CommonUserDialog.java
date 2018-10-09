package main;

import main.Commands.CommandContainer;
import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;

public class CommonUserDialog implements IDialogCommon {
	private IDialogGame currentGameDialog;
	private final ICommandContainer<String> commandContainer = new CommandContainer<String>();
	private ResultInformation previousAnswer;
	
	public CommonUserDialog() {
		commandContainer.addCommand(new CommandHelp("help"));
		commandContainer.addCommand(new CommandSwitchGame("switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame("exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList("gamesList"));
	}
	
	
	public void switchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GUESS_STRING: currentGameDialog = new DialogGame();
						  break;
		default: throw new IllegalArgumentException("Unknown game");
		}
	}
	
	private ResultInformation executeQuery(String query) {
		ResultInformation result = commandContainer.executeQuery(query);
		if (result.state != ResultState.UNKNOWN || currentGameDialog == null)
			return result;
		return currentGameDialog.handleQuery(query);
	}
	
	private void exitGame() {
		if (currentGameDialog == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		currentGameDialog = null;
	}
	
	
	@Override
	public ResultInformation handleQuery(String query) {
		previousAnswer = executeQuery(query);
		return previousAnswer;
	}

	@Override
	public ResultInformation getLastAnswer() {
		return previousAnswer;
	}

}
