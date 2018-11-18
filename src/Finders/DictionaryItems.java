package Finders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1232 on 18.11.2018.
 */

// Класс, используемый для хранения предметов словаря в удобный форме

public class DictionaryItems<Value> {
    public final List<DictionaryItem<Value>> equalItems;
    public final List<DictionaryItem<Value>> almostEqualItems;


    public DictionaryItems(List<DictionaryItem<Value>> equalItems, List<DictionaryItem<Value>> almostEqualItems) {
        this.equalItems = equalItems;
        this.almostEqualItems = almostEqualItems;
    }
}
