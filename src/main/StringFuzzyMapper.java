package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class StringFuzzyMapper {
	
	private Boolean areTheyClose(String firstLine, String secondLine, int distance)
	{
		return true;
	}

	public ArrayList<String> getCloseItems(String key, Set<String> keySet, int distance) {
		ArrayList<String> approachKeys = new ArrayList<String>();
		for (String item: keySet)
		{
			if (areTheyClose(key, item, distance))
				approachKeys.add(item);
		}
		return approachKeys;
	}
}
