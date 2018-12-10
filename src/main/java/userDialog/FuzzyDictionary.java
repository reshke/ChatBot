package userDialog;

import java.util.ArrayList;

import org.glassfish.grizzly.utils.Pair;


public interface FuzzyDictionary<TKey, TValue> {
	public void put(TKey key, TValue value);
	public ArrayList<Pair<TKey, TValue>> get(TKey key, int distance);
	public void clear();
}
