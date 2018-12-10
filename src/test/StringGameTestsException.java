package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.java.Games.StringGuessGame;
import main.java.bot.CommandContainer;
import main.java.bot.RandomGenerator;

public class StringGameTestsException {
	@Test
	public void testZeroLengthLineException() {
		try {
			@SuppressWarnings("unused")
			StringGuessGame game = new StringGuessGame(0, new RandomGenerator(), new CommandContainer());
		}
		catch(IllegalArgumentException e) {
			return;
		}
		fail("Exception did not happened");
	}
	@Test
	public void testTooBigLineException() {
		try {
			@SuppressWarnings("unused")
			StringGuessGame game = new StringGuessGame(100000000, new RandomGenerator(),  new CommandContainer());
		}
		catch(IllegalArgumentException e) {
			return;
		}
		fail("Exception did not happened");
	}

	@Test
	public void testAskingLeftIsZeroException() {
		StringGuessGame game = new StringGuessGame(10, new RandomGenerator(),  new CommandContainer());
		game.startGame();
		try {
			game.postQuery(0, 5);
		}
		catch(IllegalArgumentException e) {
			return;
		}
		fail("Exception did not happened");
	}

	@Test
	public void testAnotherOrderArgumentsException() {
		StringGuessGame game = new StringGuessGame(10, new RandomGenerator(),  new CommandContainer());
		game.startGame();
		try {
			game.postQuery(2, 1);
		}
		catch(IllegalArgumentException e) {
			return;
		}
		fail("Exception did not happened");
	}

	@Test
	public void testRightPositionBeyondBoundException() {
		StringGuessGame game = new StringGuessGame(10, new RandomGenerator(),  new CommandContainer());
		game.startGame();
		try {
			game.postQuery(4, 11);
		}
		catch(IllegalArgumentException e) {
			return;
		}
		fail("Exception did not happened");
	}

	@Test
	public void testStoppingStoppedGameException() {
		StringGuessGame game = new StringGuessGame(10, new RandomGenerator(),  new CommandContainer());
		game.startGame();
		game.endGame();
		try {
			game.endGame();
		}
		catch(UnsupportedOperationException e) {
			return;
		}
		fail("Exception did not happened");
	}
}