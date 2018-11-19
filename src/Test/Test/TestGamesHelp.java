package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import main.GamesHelper;
import main.TypeGame;
import main.IO.Reader;

public class TestGamesHelp {
	@Test
	public void testHelperReturnsStringGuessGameHelp() {
		GamesHelper helper = new GamesHelper(new Reader());
		
		assertTrue(helper.getHelp(TypeGame.GUESS_STRING).equals("To start game type start (length) and bot will make a line of zeros and units this length\r\n" + 
				"To send a request type on the keyboard \"ask leftBorder rightBorder\"\r\n" + 
				"To suggest a string type on the keyboard \"guess ....(your string)\"\r\n" + 
				"To end game type end"));
	}
	
	@Test
	public void testHelperReturnsNumGameHelp() {
		GamesHelper helper = new GamesHelper(new Reader());
		
		assertTrue(helper.getHelp(TypeGame.NUM_GAME).equals("to guess a number type guess \"number\""));
	}
	
	@Test
	public void testHelperReturnsChgkGameHelp() {
		GamesHelper helper = new GamesHelper(new Reader());
		
		assertTrue(helper.getHelp(TypeGame.CHGK_Game).equals("to find out the condition, type ask\r\n" + 
				"to try answer the answer, type result \"your answer\"\r\n" + 
				"note, that answer is one single word"));
	}
}
