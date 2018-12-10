package test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import main.java.Games.ChgkGameFactory;
import main.java.bot.Game;
import main.java.bot.IResult;
import main.java.bot.ResultState;

public class CHGKGameTests {
	private Game game;
	@Before
	public void bef()
	{
		game = new ChgkGameFactory().Create();
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