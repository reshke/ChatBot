package main.Games;

import main.GameState;
import main.ICHGKGame;
import main.IGame;
import main.IResult;
import main.ResultState;
import main.TypeGame;

public class CHGK_Game implements ICHGKGame {
	private GameState gameState;
	private final IQuestionContainer questionBase;
	
	public CHGK_Game(IQuestionContainer questionBase) 
	{
		this.questionBase = questionBase;
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
	public TypeGame getTypeGame() {
		// TODO Auto-generated method stub
		return TypeGame.CHGK_Game;
	}

	@Override
	public Boolean guessAnswer(String query) {
		// TODO Auto-generated method stub
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getState() == ResultState.SUCCESS)
			return result.getResult().getQuestionAnswer().equals(query);
		else
			return false;
	}

	@Override
	public String getHint(int val) {
		// TODO Auto-generated method stub
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getState() == ResultState.SUCCESS)
			return result.getResult().getQuestionAnswer();
		else
			return result.getError();
	}

	@Override
	public IResult GetNextQuestion() {
		// TODO Auto-generated method stub
		return  questionBase.getNextQuestion();
	}

	@Override
	public String postQuery(String query) {
		// TODO Auto-generated method stub
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getState() == ResultState.SUCCESS)
			return result.getResult().getQuestionAnswer() == query ? "Correct " : "Incorrect";
		else
			return result.getError();
	}

}
