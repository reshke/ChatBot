package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.IGameAskAnswerString;
import main.IRandomGenerator;
import main.RandomGenerator;
import main.TypeGame;
import main.Games.NumGame;

public class TestNumGame {
	IGameAskAnswerString game;
	
	@Before
	public void setUp() {
		game = new NumGame(new RandomGenerator());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getHint_ThrowsArgumentException_WhenGetsZeroIndex() {
		game.getHint(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getHint_ThrowsArgumentException_WhenGetsNegativeIndex() {
		game.getHint(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getHint_ThrowsArgumentException_WhenGetIndexMoreThanFour() {
		game.getHint(5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsNotANumberWithLengthFour() {
		game.postQuery("abcd");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsNotANumberWithLengthNotFour() {
		game.postQuery("abcd.");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsNotANumberWithDigitsWithLengthFour() {
		game.postQuery("2bc3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsANumberWithRepeatedDigits() {
		game.postQuery("1233");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsANumberWithLengthGreaterFour() {
		game.postQuery("12345");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void askInformation_ThrowsArgumentException_WhenGetsANumberWithLengthLessThanFour() {
		game.postQuery("123");
	}
	
	@Test(expected = Test.None.class)
	public void askInformation_DoesNotThrowException_WhenGetsACorrectNumber() {
		game.postQuery("1234");
	}
	
	@Test(expected = NullPointerException.class)
	public void askInformation_ThrowsNullPointerException_WhenGetsANullArgument() {
		game.postQuery(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void creationNumGame_ThrowsNullPointerException_WhenGetsANullInsteadOfAIRandomGenerator() {
		NumGame game = new NumGame(null);
	}
	
	@Test
	public void guessAnswer_ReturnsTrue_WhenGetsAnswerByUsingHint() {
		String result = "";
		
		for (int i = 1; i <= 4; i++) {
			result += game.getHint(i);
		}
		
		assertTrue(game.guessAnswer(result));
	}
	
	private String getQueryStringByNumber(int number) {
		String stringNumber = Integer.toString(number);
		
		while (stringNumber.length() < 4)
			stringNumber = "0" + stringNumber;
		
		return stringNumber;
	}
	
	@Test
	public void guessAnswer_ReturnsOnlyOneTrue_WhenRunsThroughAllPossibleItems() {
		int trueValues = 0;
		
		for (int index = 0; index < 10000; index++) {
			if (game.guessAnswer(getQueryStringByNumber(index)))
				trueValues++;
		}
		
		assertEquals(trueValues, 1);
	}
	
	@Test
	public void postQuery_ShouldReturnTwoCowsAndZeroBulls_WhenTwoDigitsExchangedPlacesAndOtherDigitsWrongs() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator);
		
		assertEquals(numGame.postQuery("2156"), "0 cows and 2 bulls!");
	}
	
	@Test
	public void postQuery_ShouldReturnFourCowsAndZeroBulls_WhenGetsFullNumber() {
		IRandomGenerator generator = new MockRandomGenerator("2567");
		NumGame numGame = new NumGame(generator);
		
		assertEquals(numGame.postQuery("2567"), "4 cows and 0 bulls!");
	}
	
	@Test
	public void postQuery_ShouldReturnZeroCowsAndZeroBulls_WhenAllNumbersAreAbsolutelyWrong() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator);
		
		assertEquals(numGame.postQuery("5678"), "0 cows and 0 bulls!");
	}
	
	@Test
	public void postQuery_ShouldReturnOneCowAndTwoBulls_WhenOneDigitInCorrectPositionAndTwoDigitsExchangedPlaces() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator);
		
		assertEquals(numGame.postQuery("1328"), "1 cows and 2 bulls!");
	}
	
	@Test
	public void guessAnswer_GetsAnswerToSecondGame_ShouldReturnFalse_WhenFirstGameAndSecondGameContainsDifferentNumbers() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator);
		IRandomGenerator secondGenerator = new MockRandomGenerator("5678");
		NumGame secondGame = new NumGame(secondGenerator);
		
		assertFalse(numGame.guessAnswer("5678"));
	}
	
	@Test
	public void postQueriesToFirstGameAndSecondGame_ShouldReturnDifferentStrings_WhenQueryContainsOneDigitFromFirstGameAndContainsTwoDigitsFromSecond() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator);
		IRandomGenerator secondGenerator = new MockRandomGenerator("5678");
		NumGame secondGame = new NumGame(secondGenerator);
		
		assertNotEquals(numGame.postQuery("1679"), secondGame.postQuery("1679"));
	}
	
	@Test(timeout = 1000)
	public void postQueries_ShouldWorksFast_WhenExecutesOperationManyTimes()
	{
		for (int i = 0; i < 100000; i++)
			game.guessAnswer("1234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationNumGame_ShouldFail_WhenGetsInCorrectNumber()
	{
		IRandomGenerator generator = new MockRandomGenerator("1233");
		NumGame numGame = new NumGame(generator);
		
	}
	
	@Test
	public void getTypeGame_ShouldReturnNumTypeGame() {
		assertEquals(game.getTypeGame(), TypeGame.NUM_GAME);
	}
	
}
