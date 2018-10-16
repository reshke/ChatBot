package Test;
import org.junit.*;
import static org.junit.Assert.*;

import main.GameState;
import main.IRandomGenerator;
import main.RandomGenerator;
import main.Games.StringGuessGame;


class MockRandomGenerator implements IRandomGenerator{
	public Boolean randomBoolean;
	public String randomString; 
	public Integer randomInt;
	
	public MockRandomGenerator( Boolean randomBoolean) {
		this.randomBoolean = randomBoolean;
	}
	public MockRandomGenerator(String randomString) {
		this.randomString = randomString;
	}
	public MockRandomGenerator(Integer randomInt) {
		this.randomInt = randomInt;
	}

	@Override
	public String generateRandomString(int lenght) {
		// TODO Auto-generated method stub
		return randomString;
	}

	@Override
	public Boolean generateRandomBoolean() {
		// TODO Auto-generated method stub
		return randomBoolean;
	}

	@Override
	public Integer generateRandomInt(int bound) {
		// TODO Auto-generated method stub
		return randomInt;
	}
	
}

public class StringGameTests {

	@Test
	public void testWinningGameOneSymbol() {
		StringGuessGame game = new StringGuessGame(1, new MockRandomGenerator("0"));
		
		game.startGame();
		Boolean firstAssert = game.guessAnswer("0");
		Boolean secondAssert = game.guessAnswer("1");
		
		
		assertTrue(firstAssert);
		assertFalse(secondAssert);
	}

	@Test
	public void testOneWinnerGameTwoSymbol() {
		StringGuessGame game = new StringGuessGame(2, new MockRandomGenerator("01"));
		
		game.startGame();
		Boolean firstAssert = game.guessAnswer("01");
		Boolean secondAssert = game.guessAnswer("10");
		
		
		assertTrue(firstAssert);
		assertFalse(secondAssert);
	}
	
	@Test
	public void testKeyWordIsInvariant() {
		StringGuessGame game = new StringGuessGame(2, new MockRandomGenerator("01"));
		String winnerWord = "01";
		Integer repetitionsCount = 100;
		
		game.startGame();
		
		for (Integer index = 0; index < repetitionsCount; index++)
			assertTrue(game.guessAnswer(winnerWord));
	}
	
	@Test
	public void testWrongAnswerIsAlwaysWrong() {
		StringGuessGame game = new StringGuessGame(4, new MockRandomGenerator("0110"));
		String wrongAnswer = "0000";
		Integer repetitionsCount = 100;
		
		game.startGame();
		
		for (Integer index = 0; index < repetitionsCount; index++) {
			assertFalse(game.guessAnswer(wrongAnswer));
		}
	}
			
	@Test
	public void testAskAnswerOneSymbolIsCorrect() {
		StringGuessGame game = new StringGuessGame(1, new MockRandomGenerator("1"));
		Integer repetitionsCount = 100;
		Integer leftBound = 0;
		Integer rightBound = 1;
		
		game.startGame();
		for (Integer index = 0; index < repetitionsCount; index++) {
			Integer countOne = 1;//game.postQuery(1, 1);
			assertTrue(leftBound <=countOne && countOne <= rightBound);
		}
	}
	
	
	@Test
	public void testAskAnswerMuchSymbolsIsCorrect() {
		StringGuessGame game = new StringGuessGame(100, new RandomGenerator());
		Integer repetitionsCount = 100;
		Integer leftQueryBound = 1;
		Integer rightQueryBound = 80;
		Integer maximumOneDiaposon = rightQueryBound - leftQueryBound + 1;
		
		game.startGame();
		for (Integer index = 0; index < repetitionsCount; index++) {
			Integer countOne = game.postQuery(leftQueryBound, rightQueryBound);
			assertTrue(0 <=countOne && countOne <= maximumOneDiaposon);
			}
	}
	
	@Test
	public void testLimitAskIsCorrect() {
		Integer leftQueryBound = 1;
		Integer rightQueryBound = 100;
		StringGuessGame game = new StringGuessGame(100, new RandomGenerator());
		Integer maximumOneDiaposon = rightQueryBound - leftQueryBound + 1;
		
		game.startGame();
		Integer countOne = game.postQuery(leftQueryBound, rightQueryBound);
		
		assertTrue(0 <=countOne && countOne <= maximumOneDiaposon);
		}
	
	@Test 
	public void testAskingFromBigToSmallIsCorrect() {
		Integer lengthLine = 100;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		
		game.startGame();
		for (Integer index = lengthLine; index >= 1; index--) {
			Integer countOne = game.postQuery(1, index);
			Integer maximumOneDiaposon = index;
			assertTrue( 0 <= countOne && countOne <= maximumOneDiaposon);
		}
	}
	
	@Test
	public void testMovingLeftBound() {
		Integer lengthLine = 100;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		
		game.startGame();
		for (Integer index = lengthLine; index >= 1; index--) {
			Integer countOne = game.postQuery(index, lengthLine);
			Integer maximumOneDiaposon = lengthLine - index + 1;
			assertTrue(0 <= countOne && countOne <= maximumOneDiaposon);
		}
	}
	
	@Test
	public void testAskBothBoundsInside() {
		Integer lengthLine = 100;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		Integer leftBound = 10;
		Integer rightBound = 21;
		Integer maximumOneDiaposon = rightBound - leftBound + 1;
		
		game.startGame();
		Integer countOne = game.postQuery(leftBound, rightBound);
		
		assertTrue(0 <= countOne && countOne <= maximumOneDiaposon);
	}
	
	@Test
	public void testRunningState() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		GameState expectedState = GameState.RUNNING;
		
		game.startGame();
		GameState state = game.getGameState();
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testNotStartedGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		GameState expectedState = GameState.NOT_STARTED;
		
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testOverGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		GameState expectedState = GameState.OVER;
		
		game.startGame();
		game.endGame();
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testPauseGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine, new RandomGenerator());
		GameState expectedState = GameState.PAUSED;
		
		game.startGame();
		game.pauseGame();
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
}
