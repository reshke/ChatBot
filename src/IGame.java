/**
 * 
 */

/**
 * @author reshke && Chishov
 *
 */
public interface IGame {
	public IGame StartGame();
	//public IMessage GetAnswer();
	
	public void EndGame();
	public void PauseGame();
	public Boolean GuessAnswer(String query);
	public int PostQuery(int leftBound, int rightBound);
}
