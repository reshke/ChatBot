package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
import org.glassfish.grizzly.utils.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.FuzzyDictionary;
import main.LevenshteinDictionary;

public class TestFuzzyDictoinary {
	FuzzyDictionary<String, Integer> stringDictionary;
	FuzzyDictionary<Integer, Integer> intDictionary;

	@Before
	public void setUp() {
		stringDictionary = new LevenshteinDictionary<String, Integer>();
		intDictionary = new LevenshteinDictionary<Integer, Integer>();
	}

	@After
	public void tearDown() {
		stringDictionary.clear();
		intDictionary.clear();
	}

	@Test
	public void getItem_ReturnCorrectValue_WhenStringDictionaryContainsKey()
	{
		stringDictionary.put("aaa", 1);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aaa", 0);

		assertEquals(actualItems.get(0).getSecond().intValue(), 1);
	}

	@Test
	public void getItem_ReturnCorrectItem_WhenIntegerDictionaryContainsKey() {
		intDictionary.put(1, 1);

		List<Pair<Integer, Integer>> actualItems = intDictionary.get(1, 0);

		assertEquals(actualItems.get(0).getSecond().intValue(), 1);

	}

	@Test
	public void getItem_ReturnEmptyList_WhenStringDictionaryDoesNotContainKey() {
		stringDictionary.put("aaab", 1);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aab", 0);

		assertEquals(actualItems.size(), 0);
	}

	@Test
	public void getItem_ReturnEmptyList_WhenIntegerDictionaryDoesNotContainKey() {
		intDictionary.put(1, 1);

		List<Pair<Integer, Integer>> actualItems = intDictionary.get(10, 0);

		assertEquals(actualItems.size(), 0);
	}

	@Test
	public void getItem_ReturnEmptyList_WhenIntegerDictionaryContainsItemsWithOtherKeysWithPositiveDifference() {
		intDictionary.put(1, 1);

		List<Pair<Integer, Integer>> actualItems = intDictionary.get(20, 100);

		assertEquals(actualItems.size(), 0);
	}

	@Test
	public void getItem_ReturnItem_WhenStringDictionaryContainsItemWithOtherKeyButWithAcceptableDifference() {
		stringDictionary.put("aaa", 1);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aa", 10);

		assertEquals(actualItems.get(0).getSecond().intValue(), 1);
	}

	@Test
	public void getItem_ReturnDifferentItems_WhenStringDictionaryContainsManySuitableItems()
	{
		stringDictionary.put("aab", 1);
		stringDictionary.put("aaa", 2);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aa", 10);

		assertNotEquals(actualItems.get(0).getSecond().intValue(), actualItems.get(1).getSecond().intValue());
	}

	@Test
	public void getItem_ReturnCorrectCountOfItems_WhenContainsSuitableAndUnsuitableItems(){
		stringDictionary.put("aab", 1);
		stringDictionary.put("aaa", 2);
		stringDictionary.put("aaaaaaa", 3);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aa", 2);

		assertEquals(actualItems.size(), 2);
	}

	@Test
	public void getItem_ReturnOneItem_WhenAddedItemWithSameKeyTwoTime(){
		stringDictionary.put("aab", 1);
		stringDictionary.put("aab", 2);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aab", 0);

		assertEquals(actualItems.size(), 1);
	}

	@Test
	public void getItem_ReturnCorrectItem_WhenAddedItemWithSameKeyTwoTime(){
		stringDictionary.put("aab", 1);
		stringDictionary.put("aab", 2);

		List<Pair<String, Integer>> actualItems = stringDictionary.get("aab", 0);

		assertEquals(actualItems.get(0).getSecond().intValue(), 2);
	}

	@Test(timeout = 1000)
	public void putItem_WorksFast_WhenAddedManyItemsInIntegerDictionary(){
		for (int index = 0; index < 100000; index++)
			intDictionary.put(index, index);
	}

	@Test(timeout = 1000)
	public void getItem_WorksFast_WhenUsedManyItemsInIntegerDictionaryWithZeroDifference(){
		for (int index = 0; index < 1000; index++)
			intDictionary.put(index, index);

		for (int index = 0; index < 100000; index++) {
			intDictionary.get(index % 100, 0);
		}
	}

	@Test(timeout = 1000)
	public void getItem_WorksFast_WhenUsedManyItemsInIntegerDictionaryWithPositiveDifference(){
		for (int index = 0; index < 1000; index++)
			intDictionary.put(index, index);

		for (int index = 0; index < 100000; index++) {
			@SuppressWarnings("unused")
			List<Pair<Integer, Integer>> items = intDictionary.get(index % 100, 10);
		}
	}

	@Test(timeout = 1000)
	public void getItem_WorksFast_WhenUsedManyTimesWithPositiveDifferenceInStringDictionaryWithNotSoBigAmountOfItems() {
		for (int index = 0; index < 100; index++) {
			stringDictionary.put(Integer.toString(index), index);
		}

		for (int index = 0; index < 1000; index++) {
			@SuppressWarnings("unused")
			List<Pair<String, Integer>> items = stringDictionary.get(Integer.toString(index % 100), 10);
		}
	}

	@Test(timeout = 1000)
	public void getItem_WorksFast_WhenStringDictionaryUsedManyTimesAtBigCountOfItemsWithZeroDifference() {
		for (int index = 0; index < 1000; index++) {
			stringDictionary.put(Integer.toString(index), index);
		}

		for (int index = 0; index < 100000; index++) {
			@SuppressWarnings("unused")
			List<Pair<String, Integer>> items = stringDictionary.get(Integer.toString(index % 100), 0);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getItem_RaiseArgumentException_WhenDictionaryGetsNegativeDifference() {
		stringDictionary.get("", -1);
	}

	@Test(expected = NullPointerException.class)
	public void getItem_RaiseNullPointerException_WhenDictionaryGetsNullArgument() {
		stringDictionary.get(null, 10);
	}

	@Test
	public void getItem_ShouldReturnEmptyList_WhenFirstDictionaryContainsItemButSecondDoesNot() {
		stringDictionary.put("aaab", 1);
		FuzzyDictionary<String, Integer> secondDictionary = new LevenshteinDictionary<String, Integer>();

		List<Pair<String, Integer>> items = secondDictionary.get("aaab", 0);

		assertEquals(items.size(), 0);
	}

	@Test
	public void getItemAtSecondDictionary_ShouldReturnEmptyList_WhenFirstDictionaryContainsItemButSecondDoesNot() {
		stringDictionary.put("aaab", 1);
		FuzzyDictionary<String, Integer> secondDictionary = new LevenshteinDictionary<String, Integer>();

		List<Pair<String, Integer>> items = secondDictionary.get("aaab", 0);

		assertEquals(items.size(), 0);
	}

	@Test
	public void getItemAtFirstDictionary_ShouldReturnOneItem_WhenFirstDictionaryContainsItemButSecondDoesNot() {
		stringDictionary.put("aaab", 1);
		@SuppressWarnings("unused")
		FuzzyDictionary<String, Integer> secondDictionary = new LevenshteinDictionary<String, Integer>();

		List<Pair<String, Integer>> items = stringDictionary.get("aaab", 0);

		assertEquals(items.size(), 1);
	}
}
