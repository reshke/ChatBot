package main.Games;

import main.Game;
import main.ICHGKGame;
import main.IResult;
import main.ResultState;
import main.TypeGame;

public class CHGK_Game extends Game implements ICHGKGame {
	private final IQuestionContainer questionBase;
	
	public CHGK_Game(IQuestionContainer questionBase) {
		this.questionBase = questionBase;
	}
	
	@Override
	public TypeGame getTypeGame() {
		return TypeGame.CHGK_Game;
	}

	public String getHint() {
		IResult<Question> result = questionBase.getCurrentQuestion();
		return result.getState() == ResultState.SUCCESS ? result.getResult().getQuestionAnswer() : result.getError();
	}
	
	@Override
	public String getHint(String[] args) {
		if (args.length != 1)
			return "Incorrect count of arguments";
		return getHint();
	}


	@Override
	public Boolean postQuery(String query) {
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getResult().getQuestionAnswer().equals(query)) {
			this.questionBase.switchQuestion();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getQuestionWording() {
		return this.questionBase.getCurrentQuestion().getResult().getQuestionWording();
	}
}
