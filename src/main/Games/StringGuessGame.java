package main.Games;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.GameState;
import main.IGame;
import main.RandomGenerator;


public class StringGuessGame implements IGame {
	public GameState gameState;// = GameState.NotStarted;
	private final String dataString;
	private final int dataStringLenght;
	private final int onesCount[];
	private final RandomGenerator random;
	
	private void calculatePrefixSums() {
		for (int i = 0; i < dataStringLenght; i++){
			onesCount[i + 1] = onesCount[i] + (dataString.charAt(i) == '1' ? 1 : 0);
		}
	}

	public StringGuessGame(int lenght) {
		gameState = GameState.NotStarted;
		random = new RandomGenerator();
		dataString = random.GenerateRandomString(lenght);
		dataStringLenght = lenght;
		onesCount = new int[lenght + 1];
		calculatePrefixSums();
	}
	
	
	
	@Override
	public IGame StartGame() {
		gameState = GameState.Running;
		// TOD O Auto-generated method stub
		return null;
	}

	@Override
	public void EndGame() {
		// TODO Auto-generated method stub
		if (gameState == GameState.Over)
			throw new UnsupportedOperationException("Game was already ended!");
			
		gameState = GameState.Over;
	}

	@Override
	public void PauseGame() {
		// TODO Auto-generated method stub
		gameState = GameState.Paused;
	}

	@Override
	public Boolean GuessAnswer(String query) {
		// TODO Auto-generated method stub
		return dataString.equals(query);
	}

	@Override
	public int PostQuery(int leftBound, int rightBound) {
		// TODO Auto-generated method stub
		
		if (rightBound < leftBound || leftBound < 1 || rightBound > dataString.length())
			throw new IllegalArgumentException("Query borders should satisfy following conditionts:\n"
					+ " 1 <= leftBorber <= rightBorder <= string length ");
		
		if (random.GenerateRandomBoolean())
			return onesCount[rightBound] - onesCount[leftBound - 1];
		else
			return random.GenerateRandomInt(rightBound - leftBound + 1);
		
	}
	

	private static File GetHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "stringGuessHelp.txt");
	}
	
	@Override
	public String GetHelp() {
		// TODO Auto-generated method stub
		FileReader helpFile = null;
		try {
			helpFile = new FileReader(GetHelpFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int c;
        try {
			while((c=helpFile.read())!=-1){
			    data.append((char)c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        return data.toString();
	}

}
