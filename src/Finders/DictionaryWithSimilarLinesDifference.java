package Finders;

import java.util.ArrayList;
import java.util.List;

public class DictionaryWithSimilarLinesDifference<Value> implements DictionaryWithAdditionalSearch<Value> {
    private final List<DictionaryItem<Value>> items = new ArrayList<>();
    private final FinderSimilarLines finder;

    public DictionaryWithSimilarLinesDifference(FinderSimilarLines finder) {
        this.finder = finder;
    }

    public void put(String key, Value value) {
        DictionaryItem<Value> item = new DictionaryItem<Value>(key, value);
        items.add(item);
    }

    public DictionaryItems<Value> get(String key) {
        List<DictionaryItem<Value>> equalItem = new ArrayList<>();
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
                    equalItem.add(item);
                    break;
            }
        }
        return new DictionaryItems<Value>(equalItem, almostEqualItems);
    }

}
