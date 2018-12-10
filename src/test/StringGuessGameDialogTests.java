package test;
 import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.java.Games.StringGuessGameFactory;
import main.java.bot.Game;
import main.java.bot.IResult;
import main.java.bot.ResultState;
 public class StringGuessGameDialogTests {
	@Test
	public void testStartExecutesCorrect() {
		Game game = new StringGuessGameFactory().Create();
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.getHelp();
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testIncorrectArgumentsStart() {
		Game game = new StringGuessGameFactory().Create();
		ResultState expectedState = ResultState.POSSIBLE_MISTAKE;
		
		IResult<String> result = game.executeQuery(new String[]{"send", "sdjaklj" , "dasdjkl"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testCorrectAsks() {
		Game game = new StringGuessGameFactory().Create();
		ResultState expectedState = ResultState.SUCCESS;
		
		IResult<String> result = game.executeQuery(new String[]{"ask", "1" , "3"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testIncorrectAsk() {
		Game game = new StringGuessGameFactory().Create();
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		
		IResult<String> result = game.executeQuery(new String[]{"ask", "2" , "1"});
		
		assertEquals(expectedState, result.getState());
	}
	
	
	@Test
	public void testStopStoppedGame() {
		Game game = new StringGuessGameFactory().Create();
		ResultState expectedState = ResultState.POSSIBLE_MISTAKE;
		game.executeQuery(new String[] {"end"});
		IResult<String> result = game.executeQuery(new String[] {"end"});
		
		assertEquals(expectedState, result.getState());
	}
	
}