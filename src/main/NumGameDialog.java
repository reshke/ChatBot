//package main;
//
//import CommandGuess;
//import main.Commands.CommandAskStringAndGetString;
//import main.Commands.CommandHint;
//
//public class NumGameDialog extends GameDialog {
//
//	@SuppressWarnings("unchecked")
//	public NumGameDialog(IAskAnswerStringGame  game, IHelper helper) {
//		createBaseForGame(helper, game, new ICommand[] 
//				{ new CommandAskStringAndGetString<String>("guess", "guess", (x) -> game.postQuery(x)),
//				new CommandGuess<String>("result", "result", (x) -> game.guessAnswer(x)),
//				new CommandHint<String>("hint", "hint", (x) -> game.getHint(x))});
//	}	
//}