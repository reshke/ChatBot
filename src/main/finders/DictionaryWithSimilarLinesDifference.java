package main.finders;

import java.util.ArrayList;
import java.util.List;

public class DictionaryWithSimilarLinesDifference<Value> implements DictionaryWithAdditionalSearch<Value> {
    private final List<DictionaryItem<Value>> items = new ArrayList<>();
    private final FinderSimilarLines finder;

    public DictionaryWithSimilarLinesDifference(FinderSimilarLines finder) {
    	raiseIfFinderIsIncorrect(finder);
        this.finder = finder;
    }

    public void put(String key, Value value) {
    	raiseIfGivenStringIsIncorrect(key);
    	removeItemIfKeyIsAdded(key);
    	DictionaryItem<Value> item = new DictionaryItem<Value>(key, value);
    	items.add(item);
    }

    public DictionaryItems<Value> get(String key) {
    	raiseIfGivenStringIsIncorrect(key);
        List<DictionaryItem<Value>> equalItems = new ArrayList<>();
        List<DictionaryItem<Value>> almostEqualItems = new ArrayList<>();
        for (DictionaryItem<Value> item: items) {
            EqualState state = finder.getEqualRatio(item.key, key);
            switch (state) {
                case notEqual:
                    break;
                case almostEqual:
                    almostEqualItems.add(item);
                    break;
                case equal:
                    equalItems.add(item);
                    break;
            }
        }
        return new DictionaryItems<Value>(equalItems, almostEqualItems);
    }
    
    public void clear() {
    	items.clear();
    }
    
    private void raiseIfFinderIsIncorrect(FinderSimilarLines finder) {
    	if (finder == null)
    		throw new NullPointerException("Finder can't be null");
    }
    
    private boolean removeItemIfKeyIsAdded(String key) {
    	for (int index = 0; index < items.size(); index++) {
    		if (items.get(index).key == key) {
    			items.remove(index);
    			return true;
    		}
    	}
    	return false;
    }
    
    
    private void raiseIfGivenStringIsIncorrect(String givenString) {
    	if (givenString == null)
    		throw new NullPointerException("Dictionary cannot contain or handle null instead of a string!");
    }
}
