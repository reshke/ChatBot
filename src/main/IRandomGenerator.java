package main;

public interface IRandomGenerator {
	public String generateRandomString(int lenght);
	public Boolean generateRandomBoolean();
	public Integer generateRandomInt(int bound);
	public String generateRandomInteger(int length, Boolean differentDigits);
}
