package Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.CommandContainer;
import main.DialogGame;
import main.GamesHelper;
import main.IDialogGame;
import main.IResult;
import main.RandomGenerator;
import main.ResultInformation;
import main.ResultState;
import main.TypeAction;
import main.Games.StringGuessGame;
import main.IO.Reader;

public class GameDialogTests {
	@Test
	public void testStartExecutesCorrect() {
		IDialogGame game = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
		ResultState expectedState = ResultState.SUCCESS;
		IResult result = game.getHelp(new String[0]);
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testIncorrectArgumentsStart() {
		IDialogGame game = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		
		IResult result = game.sendAnswer(new String[]{"send", "sdjaklj" , "dasdjkl"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testCorrectAsks() {
		IDialogGame game = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
		ResultState expectedState = ResultState.SUCCESS;
		
		IResult result = game.addRequest(new String[]{"ask", "1" , "3"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testIncorrectAsk() {
		IDialogGame game = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		
		IResult result = game.addRequest(new String[]{"ask", "2" , "1"});
		
		assertEquals(expectedState, result.getState());
	}
	
	
	@Test
	public void testStopStoppedGame() {
		IDialogGame game = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
				new CommandContainer<TypeAction>(), 
				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
		
		ResultState expectedState = ResultState.UNKNOWN;
		
		IResult result = game.stopGame(new String[] {"end"});
		
		assertEquals(expectedState, result.getState());
	}
	
}
