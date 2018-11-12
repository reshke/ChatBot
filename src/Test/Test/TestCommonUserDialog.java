package Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import main.CommonUserDialog;
import main.ResultState;
import main.TypeGame;


public class TestCommonUserDialog {
	CommonUserDialog dialog;
	
	@Before
	public void setUp() {
		 dialog = new CommonUserDialog();
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testThrowsExceptionWhenExitEdnenGame(){
		dialog.switchGame(TypeGame.CHGK_Game);
		
		dialog.exitGame();
		dialog.exitGame();
	}
	
	@Test(expected = Test.None.class)
	public void testSwitchWorkWell(){
		dialog.switchGame(TypeGame.GUESS_STRING);
		dialog.switchGame(TypeGame.NUM_GAME);
		dialog.switchGame(TypeGame.CHGK_Game);
	}
	
	@Test(expected = Test.None.class)
	public void testSwitchWorkWellWithDoubleSwitch(){
		dialog.switchGame(TypeGame.GUESS_STRING);
		dialog.switchGame(TypeGame.GUESS_STRING);
	}
	
	@Test
	public void testFuzzyCommandSearcthWorksWell(){
		dialog.switchGame(TypeGame.CHGK_Game);
		assertTrue(dialog.handleQuery("hing").getResult().equals("5"));
	}
	
	@Test
	public void testFuzzyCommanFindsPossibleTypedCommands(){
		dialog.switchGame(TypeGame.CHGK_Game);
		assertTrue(dialog.handleQuery("higg").getState() == ResultState.POSSIBLE_MISTAKE);
	}
	@Test
	public void testFuzzyCommandDoNotFindVeryBadTypedCommand(){
		dialog.switchGame(TypeGame.CHGK_Game);
		assertTrue(dialog.handleQuery("higgg").getState() == ResultState.UNKNOWN);
	}
}
