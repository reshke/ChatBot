package main.Games;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.GameState;
import main.IGame;
import main.RandomGenerator;


public class StringGuessGame implements IGame {
	private GameState gameState;// = GameState.NotStarted;
	private final String dataString;
	private final int dataStringLenght;
	private final int onesCount[];
	private final RandomGenerator random;
	
	private void calculatePrefixSums() {
		for (int i = 0; i < dataStringLenght; i++){
			onesCount[i + 1] = onesCount[i] + (dataString.charAt(i) == '1' ? 1 : 0);
		}
	}

	public GameState getGameState() {
		return gameState;
	}
	
	public StringGuessGame(int lenght) {
		if (lenght <= 0)
			throw new IllegalArgumentException("Length of line should be positive number!");
		if (lenght > 100000)
			throw new IllegalArgumentException("Length of line is too big!");
		
		gameState = GameState.NOT_STARTED;
		random = new RandomGenerator();
		dataString = random.generateRandomString(lenght);
		dataStringLenght = lenght;
		onesCount = new int[lenght + 1];
		calculatePrefixSums();
	}
	
	
	@Override
	public IGame startGame() {
		gameState = GameState.RUNNING;
		return null;
	}

	@Override
	public void endGame() {
		if (gameState == GameState.OVER)
			throw new UnsupportedOperationException("Game was already ended!");
		gameState = GameState.OVER;
	}

	@Override
	public void pauseGame() {
		gameState = GameState.PAUSED;
	}

	@Override
	public Boolean guessAnswer(String query) {
		return dataString.equals(query);
	}

	@Override
	public int postQuery(int leftBound, int rightBound) {
		if (rightBound < leftBound || leftBound < 1 || rightBound > dataString.length())
			throw new IllegalArgumentException("Query borders should satisfy following conditionts:\n"
					+ " 1 <= leftBorber <= rightBorder <= string length ");
		
		if (random.generateRandomBoolean())
			return onesCount[rightBound] - onesCount[leftBound - 1];
		else
			return random.generateRandomInt(rightBound - leftBound + 1);
		
	}
	

	private static File getHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "stringGuessHelp.txt");
	}
	
	@Override
	public String getHelp() {
		FileReader helpFile = null;
		try {
			helpFile = new FileReader(getHelpFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int charCode;
        try {
			while((charCode=helpFile.read())!=-1){
			    data.append((char)charCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        return data.toString();
	}

}
