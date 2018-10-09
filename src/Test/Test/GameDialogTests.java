package Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.DialogGame;
import main.ResultInformation;
import main.ResultState;

public class GameDialogTests {
	@Test
	public void testStartExecutesCorrect() {
		DialogGame game = new DialogGame();
		ResultState expectedState = ResultState.SUCCESS;
		
		ResultInformation result = game.handleQuery("start 10");
		
		assertEquals(expectedState, result.state);
	}
	
	@Test
	public void testIncorrectArgumentsStart() {
		DialogGame game = new DialogGame();
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		
		ResultInformation result = game.handleQuery("start -1");
		
		assertEquals(expectedState, result.state);
	}
	
	@Test
	public void testCorrectAsks() {
		DialogGame game = new DialogGame();
		ResultState expectedState = ResultState.SUCCESS;
		game.handleQuery("start 10");
		
		ResultInformation result = game.handleQuery("ask 1 2");
		
		assertEquals(expectedState, result.state);
	}
	
	@Test
	public void testIncorrectAsk() {
		DialogGame game = new DialogGame();
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		game.handleQuery("start 10");
		
		ResultInformation result = game.handleQuery("ask 2 1");
		
		assertEquals(expectedState, result.state);
	}
	
	@Test
	public void testStopStoppedGame() {
		DialogGame game = new DialogGame();
		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
		game.handleQuery("start 10");
		game.handleQuery("end");
		
		ResultInformation result = game.handleQuery("end");
		
		assertEquals(expectedState, result.state);
	}
}
