package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import main.Game;
import main.Games.CHGK_Game;
import main.Games.NumGame;
import main.Games.StringGuessGame;

public class TestGamesHelp {
	@Test
	public void testHelperReturnsStringGuessGameHelp() {
		Game game = new StringGuessGame();
		assertTrue(game.getHelp().getResult().equals("To start game type start (length) and bot will make a line of zeros and units this length\r\n" + 
				"To send a request type on the keyboard \"ask leftBorder rightBorder\"\r\n" + 
				"To suggest a string type on the keyboard \"guess ....(your string)\"\r\n" + 
				"To end game type end"));
	}
	
	@Test
	public void testHelperReturnsNumGameHelp() {
		Game game = new NumGame();
		
		assertTrue(game.getHelp().getResult().equals("to guess a number type guess \"number\""));
	}
	
	@Test
	public void testHelperReturnsChgkGameHelp() {
		Game game = new CHGK_Game();
		
		assertTrue(game.getHelp().getResult().equals("to find out the condition, type ask\r\n" + 
				"to try answer the answer, type result \"your answer\"\r\n" + 
				"note, that answer is one single word"));
	}
}
