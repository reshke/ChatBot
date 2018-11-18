package main.finders;

import java.util.List;

public class DictionaryItems<Value> {
    public final List<DictionaryItem<Value>> equalItems;
    public final List<DictionaryItem<Value>> almostEqualItems;


    public DictionaryItems(List<DictionaryItem<Value>> equalItems, List<DictionaryItem<Value>> almostEqualItems) {
        this.equalItems = equalItems;
        this.almostEqualItems = almostEqualItems;
    }
}
