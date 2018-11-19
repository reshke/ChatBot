package main;

import main.Commands.CommandGuess;
import main.Commands.CommandHint;

public class StringGuessGameDialog extends GameDialog {
	
	@SuppressWarnings("unchecked")
	public StringGuessGameDialog(IGuessStringGame game, IHelper helper) {
		createBaseForGame(helper, game, 
				new ICommand[] { new CommandPostQuery<String>("ask", "ask", (x, y) -> game.postQuery(x, y)),
				new CommandGuess<String>("result", "result", (x) -> game.guessAnswer(x)),
				new CommandHint<String>("hint", "hint", (x) -> game.getHint(x))});
	}
}

