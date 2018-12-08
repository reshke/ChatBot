package main.finders;

import java.io.Serializable;
import java.util.List;

public class DictionaryItems<Value> implements Serializable {
	private static final long serialVersionUID = 2158288795232113960L;
	public final List<DictionaryItem<Value>> equalItems;
    public final List<DictionaryItem<Value>> almostEqualItems;


    public DictionaryItems(List<DictionaryItem<Value>> equalItems, List<DictionaryItem<Value>> almostEqualItems) {
        this.equalItems = equalItems;
        this.almostEqualItems = almostEqualItems;
    }
}
