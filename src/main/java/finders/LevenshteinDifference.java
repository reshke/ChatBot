package main.java.finders;

public class LevenshteinDifference implements FinderLinesDifference{

	private static final long serialVersionUID = -578153278501987015L;

	@Override
    public int getDifference(String firstString, String secondString) {
        raiseIfArgumentsAreIncorrect(firstString, secondString);
        return calculateDifference(firstString, secondString);
    }


    private int calculateDifference(String firstString, String secondString) {
        int firstLength = firstString.length();
        int secondLength = secondString.length();
        int[][] levenshteinDistance = new int[firstLength + 1][secondLength + 1];
        for (int x = 0; x <= firstLength; x++) {
            for (int y = 0; y <= secondLength; y++) {
                levenshteinDistance[x][y] = countNextLevenshteinStep(firstString,
                        secondString,
                        x,
                        y,
                        levenshteinDistance);
            }
        }
        return levenshteinDistance[firstLength][secondLength];
    }

    private int countNextLevenshteinStep(String firstString,
                                         String secondString,
                                         int x,
                                         int y,
                                         int[][] levenshteinData) {
        if (x == 0) {
        	return y > 0 ? levenshteinData[x][y - 1] + 1 : 0;
        }
        if (y == 0)
        	return levenshteinData[x - 1][y] + 1;
        int deltaDistance = firstString.charAt(x - 1) == secondString.charAt(y - 1) ? 0 : 1;
        return Math.min(Math.min(levenshteinData[x - 1][y], levenshteinData[x][y - 1]) + 1, 
        		levenshteinData[x - 1][y - 1] + deltaDistance);
    }

    private void raiseIfArgumentsAreIncorrect(String firstString, String secondString) {
        if (firstString == null || secondString == null)
            throw new IllegalArgumentException("Given string shouldn't be null");
    }
}
