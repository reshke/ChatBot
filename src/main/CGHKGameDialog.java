package main;

import main.Commands.CommandEndGame;
import main.Commands.CommandGetString;
import main.Commands.CommandGuess;
import main.Commands.CommandHint;

public class CGHKGameDialog extends GameDailog {
	
	@SuppressWarnings("unchecked")
	public CGHKGameDialog(ICHGKGame game, IHelper helper) {
		createBaseForGame(helper, game, new ICommand[] 
			{ new CommandGetString<String>("ask", "ask", (x) -> game.getQuestionWording()),
			new CommandGuess<String>("result", "result", (x) -> game.postQuery(x)),
			new CommandEndGame<String>("end", "end", (x) -> game.endGame()),
			new CommandHint<String>("hint", "hint", (x) -> game.getHint(x))});
		}	
}
