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
	
	public CHGK_Game(IQuestionContainer questionBase) {
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

	public String getHint() {
		// TODO Auto-generated method stub
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getState() == ResultState.SUCCESS)
			return result.getResult().getQuestionAnswer();
		else
			return result.getError();
	}
	
	@Override
	public String getHint(String[] args) {
		if (args.length != 1)
			return "Incorrect count of arguments";
		return getHint();
	}

	@Override
	public IResult getNextQuestion() {
		// TODO Auto-generated method stub
		return  questionBase.getNextQuestion();
	}

	@Override
	public Boolean postQuery(String query) {
		// TODO Auto-generated method stub
		IResult<Question> result = questionBase.getCurrentQuestion();
//		if (result.getState() == ResultState.SUCCESS)
		return result.getResult().getQuestionAnswer().equals(query);
//		else
//			throw new Exception(result.getError());
	}

	@Override
	public String getQuestionWording() {
		// TODO Auto-generated method stub
		return this.questionBase.getCurrentQuestion().getResult().getQuestionWording();
	}
}
