package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import main.finders.EqualState;
import main.finders.FinderSimilarDistanceWords;
import main.finders.FinderSimilarLines;
import main.finders.LevenshteinDifference;

public class FinderSimilarDistanceWords_Should {
	private final FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(new LevenshteinDifference(), 1, 3);

	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void creation_Fails_WhenGetsNullInsteadOfFinder() {
		LevenshteinDifference finderDistance = null;
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(finderDistance, 1, 3);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void creation_Fails_WhenGetsNegativeDistance() {
		LevenshteinDifference finderDistance = new LevenshteinDifference();
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(finderDistance, -1, 3);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void creation_Fails_WhenGetsEqualDistanceLessThanAlmostEqual() {
		LevenshteinDifference finderDistance = new LevenshteinDifference();
		FinderSimilarLines finderSimilarLines = new FinderSimilarDistanceWords(finderDistance, 2, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getEqualRation_RaisesException_WhenGetsNullInsteadOfFirstString() {
		finderSimilarLines.getEqualRatio(null, "aaab");
	}

	@Test(expected = IllegalArgumentException.class)
	public void getEqualRation_RaisesException_WhenGetsNullInsteadOfSecondString() {
		finderSimilarLines.getEqualRatio("aaab", null);
	}

	@Test
	public void getEqualRatio_ReturnsEqualState_WhenStringsAreEqual() {
		EqualState state = finderSimilarLines.getEqualRatio("aaa", "aaa");
		assertEquals(EqualState.equal, state);
	}

	@Test
	public void getEqualRatio_ReturnsEqualState_WhenGetTwoStringWithOneSymbolDifference() {
		EqualState state = finderSimilarLines.getEqualRatio("aaa", "aab");
		assertEquals(EqualState.equal, state);
	}

	@Test
	public void getEqualRatio_ReturnsAlmostEqualState_WhenGetTwoStringWithMaxPossibleDistance() {
		EqualState state = finderSimilarLines.getEqualRatio("aaa", "aabbb");
		assertEquals(EqualState.almostEqual, state);
	}

	@Test
	public void getEqualRatio_ReturnsNotEqualString_WhenGetTwoStringsWithTooBigDistanceBetween() {
		EqualState state = finderSimilarLines.getEqualRatio("aaa", "aabbbbb");
		assertEquals(EqualState.notEqual, state);
	}

	@Test
	public void getEqualRation_DoNotReturnEqualState_WhenGetsItemWithDifferenceALittleBiggerThanPossible() {
		EqualState state = finderSimilarLines.getEqualRatio("aaa", "aaaaa");
		assertNotEquals(EqualState.equal, state);
	}


}
