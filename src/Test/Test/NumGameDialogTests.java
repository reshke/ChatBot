package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.IResult;
import main.ResultState;
import main.Games.NumGame;
import main.Games.NumGameFactory;


public class NumGameDialogTests {
	private NumGame game;
	@Before
	public void before()
	{
		game = new NumGame(new MockRandomGenerator("1235"));
	}
	
	@Test
	public void testStartExecutesCorrect() {
		ResultState result = (new NumGameFactory().Create()).getHelp().getState();
		
		assertEquals(ResultState.SUCCESS, result);
	}
	
	@Test
	public void testGamehelpExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"gamehelp"});
		
		assertEquals(expectedState, result.getState());
	}
	

	@Test
	public void testGuessQueryExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"guess", "1234"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testAskQueryExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"guess", "1234"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testHintQueryExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"hint", "1"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testAskQueryNotAcceptIncorrectResult() {
		String expectedState = "You won!";
		IResult<String> result = game.executeQuery(new String[] {"guess", "1294"});
		
		assertNotEquals(expectedState, result.getResult());
	}
}