package main;

public class StringFuzzyMapper {
	public boolean fuzzyMapStrings(String value, String anotherValue) {
		int number_of_mistakes = Math.abs(value.length() - anotherValue.length());
		
		for (int i = 0; i < Math.min(value.length(), anotherValue.length()); i++){
			if (value.charAt(i) != anotherValue.charAt(i))
				number_of_mistakes += 1;
		}
		return number_of_mistakes < 2;
	}
}
