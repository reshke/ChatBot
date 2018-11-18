package main;

import main.Commands.CommandAskStringAndGetString;
import main.Commands.CommandEndGame;
import main.Commands.CommandGetString;
import main.Commands.CommandGuess;
import main.Commands.CommandHint;

public class StringGuessGameDialog extends GameDailog {
	
	public StringGuessGameDialog(IGuessStringGame game, IHelper helper) {
		createBaseForGame(helper, game);
	}
//	
//	public DialogGame(IAskAnswerStringGame game,
//			ICommandContainer<TypeAction> containerGameCommands, 
//			ICommandContainer<TypeAction> containerCommonCommands,
//			IHelper helper) {
//		createBaseForGame(containerGameCommands, containerCommonCommands, helper, (IGame) game);
//		updateContainer(game);
//	}
//	
//	public DialogGame(ICHGKGame game, 
//			ICommandContainer<TypeAction> containerGameCommands, 
//			ICommandContainer<TypeAction> containerCommonCommands,
//			IHelper helper)
//	{
//		createBaseForGame(containerGameCommands, containerCommonCommands, helper, game);
//		updateContainer(game);
//	}
//	
	private void createBaseForGame(IHelper helper, IGuessStringGame game) {
		this.helper = helper;
		this.game = game;

		ICommand gameCommands[] = { new CommandPostQuery<String>("ask", "ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess<String>("result", "result", (x) -> game.guessAnswer(x)),
				new CommandEndGame<String>("end", "end", (x) -> game.endGame()),
				new CommandHint<String>("hint", "hint", (x) -> game.getHint(x))};
		for (ICommand<String> command: gameCommands) {
			this.gameCommandContainer.addCommand(command);
		}
	}
	
//	private void updateContainer(ICHGKGame game)
//	{
//		containerGameCommands.clear();
//		ICommand gameCommands[] = { new CommandGetString<TypeAction>(TypeAction.ASK, "ask", (x) -> game.getQuestionWording()),
//				new CommandGuess<TypeAction>(TypeAction.ANSWER, "result", (x) -> game.postQuery(x)),
//				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> game.endGame()),
//				new CommandHint<TypeAction>(TypeAction.HINT, "Hint", (x) -> game.getHint(x)) };
//		for (ICommand<TypeAction> command: gameCommands) {
//			containerGameCommands.addCommand(command);
//		}
//	}
//	
//	private void updateContainer(IAskAnswerStringGame game) 
//	{
//		containerGameCommands.clear();
//		ICommand gameCommands[] = { new CommandAskStringAndGetString<TypeAction>(TypeAction.ASK, "Ask", (x) -> game.postQuery(x)),
//				new CommandGuess<TypeAction>(TypeAction.ANSWER, "ask", (x) -> game.guessAnswer(x)),
//				new CommandEndGame<TypeAction>(TypeAction.END, "End", (x) -> ((IGame) game).endGame()),
//				new CommandHint<TypeAction>(TypeAction.HINT, "Hint", (x) -> game.getHint(x))};
//		for (ICommand<TypeAction> command: gameCommands) {
//			containerGameCommands.addCommand(command);
//		}
//	}
//	

}

