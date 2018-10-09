package Test;
import org.junit.*;
import static org.junit.Assert.*;

import main.GameState;
import main.Games.StringGuessGame;


public class StringGameTests {

	@Test
	public void testWinningGameOneSymbol() {
		StringGuessGame game = new StringGuessGame(1);
		
		game.startGame();
		Boolean firstStringResult = game.guessAnswer("0");
		Boolean secondStringResult = game.guessAnswer("1");
		
		assertNotSame(firstStringResult, secondStringResult);
	}

	@Test
	public void testOneWinnerGameTwoSymbol() {
		StringGuessGame game = new StringGuessGame(2);
		Integer countWinnerWords = 0;
		Integer expectedCountWords = 1;
		String[] words = {"00", "01", "10", "11"};
		
		game.startGame();
		for (String word: words) {
			countWinnerWords = game.guessAnswer(word) ? 1 : 0;
		}
		
		assertSame(expectedCountWords, countWinnerWords);
	}
	
	@Test
	public void testKeyWordIsInvariant() {
		StringGuessGame game = new StringGuessGame(2);
		String[] words = {"00", "01", "10", "11"};
		String winnerWord = "";
		Integer repetitionsCount = 100;
		
		game.startGame();
		for (String word: words) {
			if (game.guessAnswer(word))
				winnerWord = word;
		}
		
		for (Integer index = 0; index < repetitionsCount; index++)
			assertTrue(game.guessAnswer(winnerWord));
	}
	
	@Test
	public void testWrongAnswerIsAlwaysWrong() {
		StringGuessGame game = new StringGuessGame(4);
		String[] twoWords = {"0000", "0001"};
		String wrongAnswer = "";
		Integer repetitionsCount = 100;
		
		game.startGame();
		for (String word: twoWords) {
			if (!game.guessAnswer(word))
				wrongAnswer = word;
		}
		
		
		for (Integer index = 0; index < repetitionsCount; index++) {
			assertFalse(game.guessAnswer(wrongAnswer));
		}
	}
			
	@Test
	public void testAskAnswerOneSymbolIsCorrect() {
		StringGuessGame game = new StringGuessGame(1);
		Integer repetitionsCount = 100;
		Integer leftBound = 0;
		Integer rightBound = 1;
		
		game.startGame();
		for (Integer index = 0; index < repetitionsCount; index++) {
			Integer countOne = game.postQuery(1, 1);
			assertTrue(leftBound <=countOne && countOne <= rightBound);
		}
	}
	
	
	@Test
	public void testAskAnswerMuchSymbolsIsCorrect() {
		StringGuessGame game = new StringGuessGame(100);
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
		StringGuessGame game = new StringGuessGame(100);
		Integer maximumOneDiaposon = rightQueryBound - leftQueryBound + 1;
		
		game.startGame();
		Integer countOne = game.postQuery(leftQueryBound, rightQueryBound);
		
		assertTrue(0 <=countOne && countOne <= maximumOneDiaposon);
		}
	
	@Test 
	public void testAskingFromBigToSmallIsCorrect() {
		Integer lengthLine = 100;
		StringGuessGame game = new StringGuessGame(lengthLine);
		
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
		StringGuessGame game = new StringGuessGame(lengthLine);
		
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
		StringGuessGame game = new StringGuessGame(lengthLine);
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
		StringGuessGame game = new StringGuessGame(lengthLine);
		GameState expectedState = GameState.RUNNING;
		
		game.startGame();
		GameState state = game.getGameState();
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testNotStartedGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine);
		GameState expectedState = GameState.NOT_STARTED;
		
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testOverGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine);
		GameState expectedState = GameState.OVER;
		
		game.startGame();
		game.endGame();
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
	
	@Test
	public void testPauseGame() {
		Integer lengthLine = 10;
		StringGuessGame game = new StringGuessGame(lengthLine);
		GameState expectedState = GameState.PAUSED;
		
		game.startGame();
		game.pauseGame();
		GameState state = game.getGameState();
		
		assertEquals(expectedState, state);
	}
}
