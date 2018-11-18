package Finders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1232 on 18.11.2018.
 */

// Класс. реализующий поиск похожих строк, используя расстояние между строками

public class FinderSimilarDistanceWords implements FinderSimilarLines {
    private final List<FinderLinesDifference> finders;
    private final int maxEqualDistance;
    private final int maxAlmostEqualDistance;

    public FinderSimilarDistanceWords(FinderLinesDifference finder,
                                      int maxEqualDistance,
                                      int maxAlmostEqualDistance)
    {
        finders = new ArrayList<FinderLinesDifference>();
        finders.add(finder);
        this.maxEqualDistance = maxEqualDistance;
        this.maxAlmostEqualDistance = maxAlmostEqualDistance;
    }

    public FinderSimilarDistanceWords(List<FinderLinesDifference> finders,
                                      int maxEqualDistance,
                                      int maxAlmostEqualDistance)
    {
        this.finders = finders;
        this.maxEqualDistance = maxEqualDistance;
        this.maxAlmostEqualDistance = maxAlmostEqualDistance;
    }

    private EqualState getEqualRationInOneFinder(FinderLinesDifference finder,
                                                 String firstItem,
                                                 String secondItem) {
        int linesDifference = finder.getDifference(firstItem, secondItem);
        return linesDifference <= maxEqualDistance
                ? EqualState.equal
                : linesDifference <= maxAlmostEqualDistance
                ? EqualState.almostEqual
                : EqualState.equal;
    }

    @Override
    public EqualState getEqualRatio(String firstLine, String secondLine) {
        EqualState currentState = EqualState.notEqual;
        for (FinderLinesDifference finder: finders) {
            EqualState nextState = getEqualRationInOneFinder(finder, firstLine, secondLine);
            currentState = currentState.max(nextState);
            if (currentState == EqualState.equal)
                return currentState;
        }
        return currentState;
    }


}
