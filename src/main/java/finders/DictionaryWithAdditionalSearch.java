package main.java.finders;

import java.io.Serializable;

public interface DictionaryWithAdditionalSearch<Value> extends Serializable{
    public Value put(String string, Value value);
    public DictionaryItems<Value> get(String key);
    public void clear();
    public String[] getKeyArray();
}
