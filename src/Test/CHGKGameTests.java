package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.Game;
import main.IResult;
import main.ResultState;
import main.Games.CHGK_Game;
import main.Games.PseudoBase;


public class CHGKGameTests {
	private Game game;
	@Before
	public void bef()
	{
		game = new CHGK_Game(new PseudoBase());
	}
	
	@Test
	public void testStartExecutesCorrect() {ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.getHelp();
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testGamehelpExecutesCorrect() {ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"gamehelp"});
		
		assertEquals(expectedState, result.getState());
	}
	

	@Test
	public void testGuessQueryExecutesCorrect() {ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"ask"});
		
		assertEquals(expectedState, result.getState());
	}
	
	@Test
	public void testAskQueryExecutesCorrect() {
		ResultState expectedState = ResultState.SUCCESS;
		IResult<String> result = game.executeQuery(new String[] {"result", "5"});
		
		assertEquals(expectedState, result.getState());
	}
}