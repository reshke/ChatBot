package main;

import java.util.ArrayList;
import java.util.HashMap;

import org.glassfish.grizzly.utils.Pair;

public class LevenshteinDictionary<TKey, TValue> implements FuzzyDictionary<TKey, TValue> {

	private final HashMap<TKey, TValue> mapItems = new HashMap<TKey, TValue>();
	
	@Override
	public void put(TKey key, TValue value) {
		mapItems.put(key, value);
	}
	
	private int getNextStep(int x, 
			int y, 
			int[][] levenshteinArray,
			String firstString,
			String secondString) {
		Boolean xIsPositive = x > 0;
		Boolean yIsPositive = y > 0;
		if (xIsPositive) {
			int len = 1000000;
			if (yIsPositive) {
				len = levenshteinArray[x - 1][y - 1];
				if (firstString.charAt(x - 1) != secondString.charAt(y - 1))
					len++;
			}
			return Math.min(len, levenshteinArray[x - 1][y] + 1);
		}
		else if (yIsPositive)
			return levenshteinArray[x][y - 1] + 1;
		return 0;
	}
	
	private int getDistance(TKey firstItem, TKey secondItem)
	{
		if (firstItem.equals(secondItem))
			return 0;
		if (firstItem.getClass() != String.class)
			return 1000000;
		String firstItemString = firstItem.toString();
		String secondItemString = secondItem.toString();
		Integer firstLength = firstItemString.length();
		Integer secondLength = secondItemString.length();
		int[][] levenshteinArray = new int[firstLength + 1][secondLength + 1];
		for (int x = 0; x <= firstLength; x++) {
			for (int y = 0; y <= secondLength; y++) {
				levenshteinArray[x][y] = getNextStep(x, y, levenshteinArray, firstItemString, secondItemString);
			}
		}
		return levenshteinArray[firstLength][secondLength];
	}

	@Override
	public ArrayList<Pair<TKey, TValue>> get(TKey key, int maxDistance) {
		if (maxDistance < 0)
			throw new IllegalArgumentException("Max distance should be positive number");
		ArrayList<Pair<TKey, TValue>> values = new ArrayList<Pair<TKey, TValue>>();
		if (maxDistance == 0 || key.getClass() != String.class)
		{
			if (mapItems.containsKey(key)) {
				values.add(new Pair<TKey, TValue>(key, mapItems.get(key)));
			}
			return values;
		}
		for (TKey nextKey: mapItems.keySet())
		{
			if (getDistance(nextKey, key) <= maxDistance)
				values.add(new Pair<TKey, TValue>(nextKey, mapItems.get(nextKey)));
		}
		return values;
	}

	@Override
	public void clear() {
		mapItems.clear();
	}
	
}
