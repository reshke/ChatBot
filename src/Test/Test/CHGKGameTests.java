//package Test;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//
//import main.CGHKGameDialog;
//import main.GameDialog;
//import main.GamesHelper;
//import main.IResult;
//import main.ResultState;
//import main.Games.CHGK_Game;
//import main.Games.PseudoBase;
//import main.IO.Reader;
//
//
//public class CHGKGameTests {
//	@Test
//	public void testStartExecutesCorrect() {
//		GameDialog game = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.getHelp("");
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testGamehelpExecutesCorrect() {
//		GameDialog game = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"gamehelp"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//
//	@Test
//	public void testGuessQueryExecutesCorrect() {
//		GameDialog game = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"ask"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//	
//	@Test
//	public void testAskQueryExecutesCorrect() {
//		GameDialog game = new CGHKGameDialog(new CHGK_Game(new PseudoBase()), new GamesHelper(new Reader()));
//		ResultState expectedState = ResultState.SUCCESS;
//		IResult<String> result = game.postQuery(new String[] {"result", "5"});
//		
//		assertEquals(expectedState, result.getState());
//	}
//}