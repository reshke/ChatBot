package main.finders;

public interface DictionaryWithAdditionalSearch<Value> {
    public void put(String string, Value value);
    public DictionaryItems<Value> get(String key);
    public void clear();
}
