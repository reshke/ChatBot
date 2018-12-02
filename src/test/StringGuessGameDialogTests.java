package test;
//package Test;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//
//import main.GameDialog;
//import main.GamesHelper;
//import main.IResult;
//import main.RandomGenerator;
//import main.ResultState;
//import main.StringGuessGameDialog;
//import main.Games.StringGuessGame;
//import main.IO.Reader;
//
//public class StringGuessGameDialogTests {
//	@Test
//	public void testStartExecutesCorrect() {
//		GameDialog game = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.getHelp("");
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testIncorrectArgumentsStart() {
//		GameDialog game = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
//		
//		IResult<String> result = game.postQuery(new String[]{"send", "sdjaklj" , "dasdjkl"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testCorrectAsks() {
//		GameDialog game = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		
//		IResult<String> result = game.postQuery(new String[]{"ask", "1" , "3"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testIncorrectAsk() {
//		GameDialog game = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.WRONG_ARGUMENTS;
//		
//		IResult<String> result = game.postQuery(new String[]{"ask", "2" , "1"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	
//	@Test
//	public void testStopStoppedGame() {
//		GameDialog game = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()),new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.UNSUPPORTED_OPERATION;
//		game.postQuery(new String[] {"end"});
//		IResult<String> result = game.postQuery(new String[] {"end"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//}
