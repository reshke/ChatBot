//package Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//import java.awt.List;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import main.finders.DictionaryItems;
//import main.finders.DictionaryWithAdditionalSearch;
//import main.finders.DictionaryWithSimilarLinesDifference;
//import main.finders.FinderSimilarDistanceWords;
//import main.finders.FinderSimilarLines;
//import main.finders.LevenshteinDifference;
//
//public class DictionaryWithSimilarLinesDifference_Should {
//	private DictionaryWithAdditionalSearch<Integer> dictionary;
//
//	@Before
//	public void setUp() {
//		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(new LevenshteinDifference(), 1, 3);
//		dictionary = new DictionaryWithSimilarLinesDifference<Integer>(finderSimilarLines);
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void creation_RaisesException_WhenGetsNullInsteadOfFinder() {
//		dictionary = new DictionaryWithSimilarLinesDifference(null);
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void put_RaisesException_WhenSendNullString() {
//		dictionary.put(null, 123);
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void get_RaisesException_WhenSendNullString() {
//		dictionary.get(null);
//	}
//
//	@Test
//	public void put_WithSameKey_DoNotMakeTwoItemsInDictionary() {
//		dictionary.put("a", 1);
//		dictionary.put("a", 2);
//
//		DictionaryItems<Integer>  items = dictionary.get("a");
//		int countItems = items.equalItems.size();
//
//		assertNotEquals(2, countItems);
//	}
//
//	@Test
//	public void get_DoNotUnnecessaryItemsToEquals_WhenDictionaryContainNotEqualItems() {
//		dictionary.put("a", 1);
//		dictionary.put("aaaa", 4);
//
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int length = items.equalItems.size();
//
//		assertEquals(1, length);
//	}
//
//	@Test
//	public void get_ReturnsCorrectValueItems_WhenValueHasChanged() {
//		dictionary.put("a", 1);
//		dictionary.put("a", 2);
//
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int value = items.equalItems.get(0).value;
//
//		assertEquals(2, value);
//	}
//
//	@Test
//	public void get_ReturnsCorrectCountOfItems_WhenThereAreAFewSuitableItems() {
//		dictionary.put("a", 1);
//		dictionary.put("ab", 1);
//
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int length = items.equalItems.size();
//
//		assertEquals(2, length);
//	}
//
//	@Test
//	public void get_ReturnsCorrectCountOfItemsOnAlmostEqualsItems_WhenThereAreEqualsItems() {
//		dictionary.put("a", 1);
//		dictionary.put("abaa", 4);
//
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int length = items.almostEqualItems.size();
//
//		assertEquals(1, length);
//
//	}
//
//	@Test
//	public void get_ReturnsEmptyArrayOnEqualItems_WhenThereAreNoSuitableItems() {
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int length = items.equalItems.size();
//
//		assertEquals(0, length);
//	}
//
//
//	@Test
//	public void get_DoNotCountItems_WhichAreDoNotSuitForEqualOrAlmostEqualItems() {
//		dictionary.put("aaaaaaaa", 3);
//		dictionary.put("a", 1);
//		dictionary.put("aaaa", 3);
//
//		DictionaryItems<Integer> items = dictionary.get("a");
//		int length = items.almostEqualItems.size() + items.equalItems.size();
//
//		assertEquals(2, length);
//
//	}
//
//	@Test
//	public void get_ReturnsItems_WithCorrectValues() {
//		dictionary.put("aab", 3);
//		dictionary.put("aabbb", 5);
//
//		DictionaryItems<Integer> items = dictionary.get("aab");
//		int value = items.equalItems.get(0).value;
//
//		assertEquals(3, value);
//	}
//}
