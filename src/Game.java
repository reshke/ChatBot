import java.awt.List;
import java.lang.reflect.Array;
import java.util.Random;


public class Game implements IGame {
	public GameState gameState;// = GameState.NotStarted;
	private final String dataString;
	private final int dataStringLenght;
	private final int bound = 20;
	private final int onesCount[];
	private final Random random = new Random();
	
	private void calculatePrefixSums() {
		for (int i = 0; i < dataStringLenght; i++){
			onesCount[i + 1] = onesCount[i] + (dataString.charAt(i) == '1' ? 1 : 0);
		}
	}
	
	public Game(int lenght) {
		gameState = GameState.NotStarted;
		dataString = (new StringGenerator()).GenerateRandomString(lenght);
		dataStringLenght = lenght;
		onesCount = new int[lenght + 1];
		
	}
	
	/*
	public Game() {
		int lenght = random.nextInt(bound);
		Game(lenght);
	}*/
	
	@Override
	public IGame StartGame() {
		gameState = GameState.Running;
		// TOD O Auto-generated method stub
		return null;
	}

	@Override
	public void EndGame() {
		// TODO Auto-generated method stub
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
		return dataString == query;
	}

	@Override
	public int PostQuery(int leftBound, int rightBound) {
		// TODO Auto-generated method stub
		if (random.nextBoolean())
			return onesCount[rightBound] - onesCount[leftBound - 1];
		else
			return random.nextInt(rightBound - leftBound + 1);
		
	}

}
