package def;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import Games.NumGame;
import userDialog.CommandContainer;
import userDialog.IRandomGenerator;
import userDialog.RandomGenerator;


public class TestNumGame {
	NumGame game;

	@Before
	public void setUp() {
		game = new NumGame(new RandomGenerator(), new CommandContainer());
	}

	@Test
	public void getHint_ReturnsHelpString_WhenGetsZeroIndex() {
		assertTrue(game.getHint(new String[]{"hint", "0"}).getResult().equals("You can ask digit only in range from 1 to 4"));
	}

	@Test
	public void getHint_ReturnsHelpString_WhenGetsNegativeIndex() {
		assertTrue(game.getHint(new String[]{"hint", "-1"}).getResult().equals("You can ask digit only in range from 1 to 4"));
	}

	@Test
	public void getHint_ReturnsHelpString_WhenGetIndexMoreThanFour() {
		assertTrue(game.getHint(new String[]{"hint", "5"}).getResult().equals("You can ask digit only in range from 1 to 4"));
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
		game.executeQuery(null);
	}

	@Test(expected = NullPointerException.class)
	public void creationNumGame_ThrowsNullPointerException_WhenGetsANullInsteadOfAIRandomGenerator() {
		new NumGame(null, null);
	}

	@Test
	public void guessAnswer_ReturnsTrue_WhenGetsAnswerByUsingHint() {
		String result = "";

		for (int i = 1; i <= 4; i++) {
			result += game.getHint(new String[]{"hint", "" + i}).getResult();
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
		NumGame numGame = new NumGame(generator, new CommandContainer());

		assertEquals(numGame.postQuery("2156"), "0 cows and 2 bulls!");
	}

	@Test
	public void postQuery_ShouldReturnFourCowsAndZeroBulls_WhenGetsFullNumber() {
		IRandomGenerator generator = new MockRandomGenerator("2567");
		NumGame numGame = new NumGame(generator, new CommandContainer());

		assertEquals(numGame.postQuery("2567"), "4 cows and 0 bulls!");
	}

	@Test
	public void postQuery_ShouldReturnZeroCowsAndZeroBulls_WhenAllNumbersAreAbsolutelyWrong() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator, new CommandContainer());

		assertEquals(numGame.postQuery("5678"), "0 cows and 0 bulls!");
	}

	@Test
	public void postQuery_ShouldReturnOneCowAndTwoBulls_WhenOneDigitInCorrectPositionAndTwoDigitsExchangedPlaces() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator, new CommandContainer());

		assertEquals(numGame.postQuery("1328"), "1 cows and 2 bulls!");
	}

	@Test
	public void guessAnswer_GetsAnswerToSecondGame_ShouldReturnFalse_WhenFirstGameAndSecondGameContainsDifferentNumbers() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator, new CommandContainer());
		assertFalse(numGame.guessAnswer("5678"));
	}

	@Test
	public void postQueriesToFirstGameAndSecondGame_ShouldReturnDifferentStrings_WhenQueryContainsOneDigitFromFirstGameAndContainsTwoDigitsFromSecond() {
		IRandomGenerator generator = new MockRandomGenerator("1234");
		NumGame numGame = new NumGame(generator, new CommandContainer());
		IRandomGenerator secondGenerator = new MockRandomGenerator("5678");
		NumGame secondGame = new NumGame(secondGenerator, new CommandContainer());

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
		new NumGame(generator,  new CommandContainer());
	}
}