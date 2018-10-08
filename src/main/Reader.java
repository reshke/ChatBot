package main;
import java.util.Scanner;

public class Reader implements IReader {
	private final Scanner scanner = new Scanner(System.in);
	
	@Override
	public String ReadQuery()
	{ 
		return scanner.nextLine();
	}
}
