package main.finders;

/**
 * Created by 1232 on 18.11.2018.
 */

// Вариация интерфейса поиска расстоянии между строками, использующий алгоритм Левенштейна

public class LevenshteinDifference implements FinderLinesDifference {

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
        Boolean xIsPositive = x > 0;
        Boolean yIsPositive = y > 0;
        if (xIsPositive) {
            int distance = 1000000;
            if (yIsPositive) {
                distance = levenshteinData[x - 1][y - 1];
                if (secondString.charAt(y - 1) == firstString.charAt(x - 1)) {
                    return Math.min(distance, levenshteinData[x - 1][y] + 1);
                }
                ++distance;
            }
            return Math.min(distance, levenshteinData[x - 1][y] + 1);
        } else {
            return yIsPositive ? (levenshteinData[x][y - 1] + 1) : 0;
        }
    }

    private void raiseIfArgumentsAreIncorrect(String firstString, String secondString) {
        if (firstString == null || secondString == null)
            throw new IllegalArgumentException("Given string shouldn't be null");
    }
}
