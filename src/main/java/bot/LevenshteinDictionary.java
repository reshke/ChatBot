package bot;

import java.util.ArrayList;
import java.util.HashMap;

import org.glassfish.grizzly.utils.Pair;

public class LevenshteinDictionary<TKey, TValue> implements FuzzyDictionary<TKey, TValue> {

	private final HashMap<TKey, TValue> mapItems = new HashMap<TKey, TValue>();
	
	@Override
	public void put(TKey key, TValue value) {
		mapItems.put(key, value);
	}
	
	private int getNextLevenshteinStep(int x, 
			int y, 
			int[][] levenshteinData,
			String firstString,
			String secondString) {
		Boolean xIsPositive = x > 0;
		Boolean yIsPositive = y > 0;
		if (xIsPositive) {
			int distance = 1000000;
			if (yIsPositive) {
				distance = levenshteinData[x - 1][y - 1];
				if (firstString.charAt(x - 1) != secondString.charAt(y - 1))
					distance++;
			}
			return Math.min(distance, levenshteinData[x - 1][y] + 1);
		}
		else if (yIsPositive)
			return levenshteinData[x][y - 1] + 1;
		return 0;
	}
	
	private ArrayList<Pair<TKey, TValue>> getStringItemsInDistance(String key, int maxDistance)
	{
		ArrayList<Pair<TKey, TValue>> suitableItems = new ArrayList<Pair<TKey, TValue>>();
		for (TKey nextKey: mapItems.keySet())
		{
			if (getDistanceBetweenStrings(nextKey.toString(), key) <= maxDistance)
				suitableItems.add(new Pair<TKey, TValue>(nextKey, mapItems.get(nextKey)));
		}
		return suitableItems;
	}
	
	private int getDistanceBetweenStrings(String firstString, String secondString) {
		Integer firstStringLength = firstString.length();
		Integer secondStringLength = secondString.length();
		int[][] levenshteinData = new int[firstStringLength + 1][secondStringLength + 1];
		for (int x = 0; x <= firstStringLength; x++) {
			for (int y = 0; y <= secondStringLength; y++) {
				levenshteinData[x][y] = getNextLevenshteinStep(x, y, levenshteinData, firstString, secondString);
			}
		}
		return levenshteinData[firstStringLength][secondStringLength];
	}

	@Override
	public ArrayList<Pair<TKey, TValue>> get(TKey key, int maxDistance) {
		raiseIfArgumentsForGetAreIncorrect(key, maxDistance);
		if (maxDistance == 0 || key.getClass() != String.class)
			return getItemsWithSameKey(key);
		return getStringItemsInDistance(key.toString(), maxDistance);
	}
	
	private ArrayList<Pair<TKey, TValue>> getItemsWithSameKey(TKey key) {
		ArrayList<Pair<TKey, TValue>> suitableItems = new ArrayList<Pair<TKey, TValue>>();
		if (mapItems.containsKey(key))
			suitableItems.add(new  Pair<TKey, TValue>(key, mapItems.get(key)));
		return suitableItems;
	}
	
	private void raiseIfArgumentsForGetAreIncorrect(TKey key, int maxDistance) {
		if (maxDistance < 0)
			throw new IllegalArgumentException("Max distance should be positive number");
	}

	@Override
	public void clear() {
		mapItems.clear();
	}
	
}
