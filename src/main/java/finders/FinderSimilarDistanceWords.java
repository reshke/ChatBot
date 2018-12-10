package finders;

import java.util.ArrayList;
import java.util.List;


public class FinderSimilarDistanceWords implements FinderSimilarLines {

	private static final long serialVersionUID = -3125957620875442478L;
	private final List<FinderLinesDifference> finders;
    private final int maxEqualDistance;
    private final int maxAlmostEqualDistance;

    public FinderSimilarDistanceWords(FinderLinesDifference finder,
                                      int maxEqualDistance,
                                      int maxAlmostEqualDistance)
    {
    	raiseIfDistancesAreIncorrect(maxEqualDistance, maxAlmostEqualDistance);
    	raiseIfFinderIsIncorrect(finder);
        
        finders = new ArrayList<FinderLinesDifference>();
        finders.add(finder);
        this.maxEqualDistance = maxEqualDistance;
        this.maxAlmostEqualDistance = maxAlmostEqualDistance;
    }

    public FinderSimilarDistanceWords(List<FinderLinesDifference> finders,
                                      int maxEqualDistance,
                                      int maxAlmostEqualDistance)
    {
    	raiseIfDistancesAreIncorrect(maxEqualDistance, maxAlmostEqualDistance);
    	this.finders = finders;
        this.maxEqualDistance = maxEqualDistance;
        this.maxAlmostEqualDistance = maxAlmostEqualDistance;
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
    
    private EqualState getEqualRationInOneFinder(FinderLinesDifference finder,
            String firstItem,
            String secondItem) {
    	int linesDifference = finder.getDifference(firstItem, secondItem);
    	return linesDifference <= maxEqualDistance
    			? EqualState.equal
    			: linesDifference <= maxAlmostEqualDistance
    				? EqualState.almostEqual
    				: EqualState.notEqual;
    }
    
    private void raiseIfDistancesAreIncorrect(int maxEqualDistance, int maxAlmostEqualDistance) {
    	if (maxEqualDistance < 0)
    		throw new IllegalArgumentException("Distance can not be negative number");
    	if (maxEqualDistance > maxAlmostEqualDistance)
    		throw new IllegalArgumentException("Max equal distance can not be less, than maxAlmostEqualDistance");
    }
    
    private void raiseIfFinderIsIncorrect(FinderLinesDifference finder) {
    	if (finder == null)
    		throw new NullPointerException("Finder can not be null");
    	
    }
}
