package main;

import main.Commands.CommandContainer;
import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;
import main.Games.StringGuessGame;

public class CommonUserDialog implements IDialog {
	private IDialog currentDialog;
	private final CommandContainer commandContainer = new CommandContainer();
	private ResultInformation previousAnswer;
	
	public CommonUserDialog() {
		commandContainer.AddCommand(new CommandHelp("help"));
		commandContainer.AddCommand(new CommandSwitchGame("switch", (x) -> SwitchGame(x)));
		commandContainer.AddCommand(new CommandExitGame("exit", () -> ExitGame()));
		commandContainer.AddCommand(new CommandGamesList("gamesList"));
	}
	
	private void SwitchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GuessString: currentDialog = new DialogGame();
						  break;
		default: throw new IllegalArgumentException("Unknown game");
		}
	}
	
	private ResultInformation ExecuteQuery(String query) {
		ResultInformation result = commandContainer.ExecuteQuery(query);
		if (result.State != ResultState.Unknowm || currentDialog == null)
			return result;
		return currentDialog.HandleQuery(query);
	}
	
	private void ExitGame() {
		if (currentDialog == null)
			throw new UnsupportedOperationException("Game is not chosen!");
		currentDialog = null;
	}
	
	
	@Override
	public ResultInformation HandleQuery(String query) {
		previousAnswer = ExecuteQuery(query);
		return previousAnswer;
	}

	@Override
	public ResultInformation GetLastAnswer() {
		return previousAnswer;
	}

}
