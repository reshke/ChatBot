package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import main.Games.StringGuessGame;


class StringGameTests extends TestCase {
	private StringGuessGame game;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUpStringGuessGame() throws Exception {
		game = new StringGuessGame(4);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDownStringGuessGame() throws Exception {
		game.endGame();
	}

	@Test
	void test() {
	}

}
