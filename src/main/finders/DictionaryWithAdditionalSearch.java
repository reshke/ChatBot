package main.finders;

public interface DictionaryWithAdditionalSearch<Value> {
    public void put(String key, Value value);
    public DictionaryItems<Value> get(String key);
}
