package main;

import main.Commands.CommandContainer;
import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;

public class CommonUserDialog implements IDialog {
	private IDialog currentDialog;
	private final CommandContainer commandContainer = new CommandContainer();
	private ResultInformation previousAnswer;
	
	public CommonUserDialog() {
		commandContainer.addCommand(new CommandHelp("help"));
		commandContainer.addCommand(new CommandSwitchGame("switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame("exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList("gamesList"));
	}
	
	private void switchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GUESS_STRING: currentDialog = new DialogGame();
						  break;
		default: throw new IllegalArgumentException("Unknown game");
		}
	}
	
	private ResultInformation ExecuteQuery(String query) {
		ResultInformation result = commandContainer.executeQuery(query);
		if (result.state != ResultState.UNKNOWN || currentDialog == null)
			return result;
		return currentDialog.handleQuery(query);
	}
	
	private void exitGame() {
		if (currentDialog == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		currentDialog = null;
	}
	
	
	@Override
	public ResultInformation handleQuery(String query) {
		previousAnswer = ExecuteQuery(query);
		return previousAnswer;
	}

	@Override
	public ResultInformation getLastAnswer() {
		return previousAnswer;
	}

}
