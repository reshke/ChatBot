package main;

import main.Commands.CommandAskStringAndGetString;
import main.Commands.CommandEndGame;
import main.Commands.CommandGuess;
import main.Commands.CommandHint;

public class NumGameDialog extends GameDialog {

	@SuppressWarnings("unchecked")
	public NumGameDialog(IAskAnswerStringGame  game, IHelper helper) {
		createBaseForGame(helper, game, new ICommand[] 
				{ new CommandAskStringAndGetString<String>("guess", "guess", (x) -> game.postQuery(x)),
				new CommandGuess<String>("result", "result", (x) -> game.guessAnswer(x)),
				new CommandEndGame<String>("end", "end", (x) -> ((IGame) game).endGame()),
				new CommandHint<String>("hint", "hint", (x) -> game.getHint(x))});
	}	
}