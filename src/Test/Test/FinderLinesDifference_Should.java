//package Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import main.finders.FinderLinesDifference;
//import main.finders.LevenshteinDifference;
//
//public class FinderLinesDifference_Should {
//	private final FinderLinesDifference finder = new LevenshteinDifference();
//
//	@Test(expected = IllegalArgumentException.class)
//	public void creation_RaisesException_WhenGetsNullInsteadOfFirstString() {
//		finder.getDifference(null, "aaa");
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void creation_RaisesException_WhenGetsNullInsteadOfSecondString(){
//		finder.getDifference("aaa", null);
//	}
//
//	@Test
//	public void getDifference_ReturnsZero_WhenStringsAreEqual() {
//		int result = finder.getDifference("aaaa", "aaaa");
//
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void getDifference_ReturnsCorrectAnswer_WhenFirstIsSubstringOfSecond() {
//		int result = finder.getDifference("aaaa", "aaaaaab");
//
//		assertEquals(3, result);
//	}
//
//
//	@Test
//	public void getDifference_ReturnsCorrectAnswer_WhenOneOfStringIsEmpty() {
//		int result = finder.getDifference("aaaa", "");
//
//		assertEquals(4, result);
//	}
//
//	@Test
//	public void getDifference_ReturnsCorrectAnswer_WhenSymbolsAreConfused() {
//		int result = finder.getDifference("aaba", "aaab");
//
//		assertEquals(2, result);
//	}
//
//	@Test
//	public void getDifference_ReturnsCorrectAnswer_WhenGetsStringWithSimilarSymbols() {
//		int result = finder.getDifference("acbdeaf", "arfklfe");
//		assertEquals(6, result);
//	}
//
//	@Test
//	public void getDifference_ReturnsCorrectAnswer_WhenGetsStringAndReversedString() {
//		int result = finder.getDifference("abcde", "edcba");
//
//		assertEquals(4, result);
//	}
//
//	@Test(timeout = 2000)
//	public void getDifference_WorksFast_WhenGetsManySmallString() {
//		String firstString = "aabsdksadasdas";
//		String secondString = "qweescksl;asd";
//		int iterationCount = 100000;
//
//		for (int index = 0; index < iterationCount; index++)
//			finder.getDifference(firstString, secondString);
//	}
//
//}
