//package Test;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import main.GameDialog;
//import main.GamesHelper;
//import main.RandomGenerator;
//import main.StringGuessGameDialog;
//import main.Games.StringGuessGame;
//import main.IO.Reader;
//
//public class TestDialogGame {
//	GameDialog gameDialog;
//
//	@Before
//	public void setUp() {
//		gameDialog = new StringGuessGameDialog(new StringGuessGame(10, new RandomGenerator()),  new GamesHelper(new Reader()));
//	}
//	
//	@Test(expected = Test.None.class)
//	public void testr() {
//		gameDialog.getHelp("gamehelp");
//	}
//}
