package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.Game;
import main.IResult;
import main.RandomGenerator;
import main.ResultState;
import main.Games.NumGame;
import main.IO.Reader;


public class NumGameDialogTests {
	private NumGame game;
	@Before
	public void before()
	{
		game = new NumGame();
	}
	
	@Test
	public void testStartExecutesCorrect() {
		ResultState result = (new NumGame()).getHelp().getState();
		
		assertEquals(ResultState.SUCCESS, result);
	}
	
	@Test
	public void testGamehelpExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.postQuery(new String[] {"gamehelp"});
		
		assertEquals(expectedState, result.getState());
	}
	

//	@Test
//	public void testGuessQueryExecutesCorrect() {
//		GameDialog game = new NumGameDialog(new NumGame(new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"guess", "1234"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testAskQueryExecutesCorrect() {
//		GameDialog game = new NumGameDialog(new NumGame(new MockRandomGenerator("1234")), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"result", "1234"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testHintQueryExecutesCorrect() {
//		GameDialog game = new NumGameDialog(new NumGame(new MockRandomGenerator("1234")), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"hint", "1"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testAskQueryNotAcceptIncorrectResult() {
//		GameDialog game = new NumGameDialog(new NumGame(new MockRandomGenerator("1234")), new GamesHelper(new Reader()));
//		String expectedState = "You are wrong!";
//		IResult<String> result = game.postQuery(new String[] {"result", "1294"});
//		
//		assertEquals(expectedState, result.getResult());
//	}
}