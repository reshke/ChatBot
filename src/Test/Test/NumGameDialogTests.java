//package Test;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//
//import main.GameDialog;
//import main.GamesHelper;
//import main.IResult;
//import main.NumGameDialog;
//import main.RandomGenerator;
//import main.ResultState;
//import main.Games.NumGame;
//import main.IO.Reader;
//
//
//public class NumGameDialogTests {
//	@Test
//	public void testStartExecutesCorrect() {
//		GameDialog game = new NumGameDialog(new NumGame(new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.getHelp("");
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testGamehelpExecutesCorrect() {
//		GameDialog game = new NumGameDialog(new NumGame(new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"gamehelp"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//
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
//}