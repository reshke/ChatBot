//package Test;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import main.CommandContainer;
//import main.DialogGame;
//import main.GamesHelper;
//import main.RandomGenerator;
//import main.TypeAction;
//import main.Games.StringGuessGame;
//import main.IO.Reader;
//
//public class TestDialogGame {
//	
//	DialogGame gameDialog;
//	
//
//	@Before
//	public void setUp() {
//		gameDialog = new DialogGame(new StringGuessGame(10, new RandomGenerator()), 
//				new CommandContainer<TypeAction>(), 
//				new CommandContainer<TypeAction>(), new GamesHelper(new Reader()));
//	}
//	
//	@Test(expected = Test.None.class)
//	public void testr() {
//		gameDialog.getHelp(new String[]{"gamehelp"});
//	}
//}
