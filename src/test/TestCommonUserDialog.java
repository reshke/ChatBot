package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import main.java.Commands.CommandHelp;
import main.java.bot.CommandContainer;
import main.java.bot.CommonUserDialog;
import main.java.bot.GameSaver;
import main.java.bot.ICommand;
import main.java.bot.ResultState;
import main.java.classLoader.LoaderGames;
public class TestCommonUserDialog {
	CommonUserDialog dialog;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {

		dialog = new CommonUserDialog(new LoaderGames().Load(), 
				new CommandContainer(new ICommand[] { new CommandHelp<String>("help", "help")}),
				new GameSaver(System.getProperty("user.dir") + "\\out\\production\\ChatBot\\main\\data\\"), null);
	}


	@Test(expected = UnsupportedOperationException.class)
	public void testThrowsExceptionWhenExitEdnenGame(){
		dialog.switchGame(new String[] { "guessGame" } );

		dialog.exitGame();
		dialog.exitGame();
	}

	@Test(expected = Test.None.class)
	public void testSwitchWorkWell(){
		dialog.switchGame(new String[] { "guessGame" });
		dialog.switchGame(new String[] { "numGame" });
	}

	@Test(expected = Test.None.class)
	public void testSwitchWorkWellWithDoubleSwitch(){
		dialog.switchGame(new String[] { "guessGame" });
		dialog.switchGame(new String[] { "guessGame" });
	}

	@Test
	public void testFuzzyCommandSearcthWorksWell(){
		dialog.switchGame(new String[] { "guessGame" });
		assertTrue(dialog.handleQuery("hing 1").getState() != ResultState.SUCCESS);
	}

	@Test
	public void testFuzzyCommanFindsPossibleTypedCommands(){
		dialog.switchGame(new String[] { "guessGame" });
		assertTrue(dialog.handleQuery("higg").getState() == ResultState.POSSIBLE_MISTAKE);
	}
	@Test
	public void testFuzzyCommandDoNotFindVeryBadTypedCommand(){
		dialog.switchGame(new String[] { "guessGame" });
		assertTrue(dialog.handleQuery("higggggggggg").getState() == ResultState.UNKNOWN);
	}
}