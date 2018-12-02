package test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import main.CommonUserDialog;
import main.ResultState;
import main.classLoader.LoaderGames;

public class TestCommonUserDialog {
	CommonUserDialog dialog;
	
	@Before
	public void setUp() {
		 dialog = new CommonUserDialog(new LoaderGames().Load());
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testThrowsExceptionWhenExitEdnenGame(){
		dialog.switchGame("guessGame");
		
		dialog.exitGame();
		dialog.exitGame();
	}
	
	@Test(expected = Test.None.class)
	public void testSwitchWorkWell(){
		dialog.switchGame("guessGame");
		dialog.switchGame("numGame");
	}
	
	@Test(expected = Test.None.class)
	public void testSwitchWorkWellWithDoubleSwitch(){
		dialog.switchGame("guessGame");
		dialog.switchGame("guessGame");
	}
	
	@Test
	public void testFuzzyCommandSearcthWorksWell(){
		dialog.switchGame("guessGame");
		assertTrue(dialog.handleQuery("hing 1").getState() == ResultState.SUCCESS);
	}
	
	@Test
	public void testFuzzyCommanFindsPossibleTypedCommands(){
		dialog.switchGame("guessGame");
		assertTrue(dialog.handleQuery("higg").getState() == ResultState.POSSIBLE_MISTAKE);
	}
	@Test
	public void testFuzzyCommandDoNotFindVeryBadTypedCommand(){
		dialog.switchGame("guessGame");
		assertTrue(dialog.handleQuery("higggggggggg").getState() == ResultState.UNKNOWN);
	}
}
