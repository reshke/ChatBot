package main.finders;


public class DictionaryItem<Value> {    public final String key;
    public final Value value;
    public DictionaryItem(String key, Value value)
    {
        RaiseIfGivenKeyIsIncorrect(key);
        this.key = key;
        this.value = value;
    }

    public void RaiseIfGivenKeyIsIncorrect(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key string can't be null!");
    }
}