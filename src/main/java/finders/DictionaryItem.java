package main.java.finders;

import java.io.Serializable;

public class DictionaryItem<Value> implements Serializable {    
	private static final long serialVersionUID = -1310132961918162249L;
	public final String key;
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