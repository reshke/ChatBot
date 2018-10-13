package main;

import main.Commands.CommandContainer;
import main.Commands.CommandExitGame;
import main.Commands.CommandGamesList;
import main.Commands.CommandHelp;
import main.Commands.CommandSwitchGame;

public class CommonUserDialog implements IDialogCommon {
	private IDialogGame currentGameDialog;
	private final ICommandContainer<String> commandContainer = new CommandContainer<String>();
	private final ICommandSender<String> senderCommandContainer = new CommandSender<String>();
	private IResult previousAnswer;
	
	public CommonUserDialog() {
		commandContainer.addCommand(new CommandHelp<String>("help", "help"));
		commandContainer.addCommand(new CommandSwitchGame<String>("switch", "switch", (x) -> switchGame(x)));
		commandContainer.addCommand(new CommandExitGame<String>("exit", "exit", () -> exitGame()));
		commandContainer.addCommand(new CommandGamesList<String>("gamesList", "gamesList"));
	}
	
	private void updateSender() {
		senderCommandContainer.clear();
		senderCommandContainer.addCommandSender("start",x -> currentGameDialog.startGame(x));
		senderCommandContainer.addCommandSender("help", x -> currentGameDialog.getHelp(x));
		senderCommandContainer.addCommandSender("last", x-> currentGameDialog.getLastAnswer(x));
		senderCommandContainer.addCommandSender("ask", x -> currentGameDialog.addRequest(x));
		senderCommandContainer.addCommandSender("state", x -> currentGameDialog.getState(x));
		senderCommandContainer.addCommandSender("result", x -> currentGameDialog.sendAnswer(x));
		senderCommandContainer.addCommandSender("end", x -> currentGameDialog.stopGame(x));
		
	}
	
	public void switchGame(TypeGame typeGame) {
		switch(typeGame) {
		case GUESS_STRING: currentGameDialog = new DialogGame();
						  break;
		default: throw new IllegalArgumentException("Unknown game");
		}
	}
	
	private IResult executeQuery(String query) {
		String[] arguments = query.split(" ");
		IResult result = commandContainer.executeCommand(arguments[0], arguments);
		if (result.getState() != ResultState.UNKNOWN || currentGameDialog == null)
			return result;
		return senderCommandContainer.executeCommand(arguments[0], arguments);
	}
	
	private void exitGame() {
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
