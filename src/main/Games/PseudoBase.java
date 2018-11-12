package main.Games;

import main.IResult;
import main.ResultState;

public class PseudoBase implements IQuestionContainer {
	
	private Question question = new Question("2 + 2 = ????", "5");
	private Question secondQuestion = new Question("how are u?", "fine");
	private Question currentQuestion;
	private int type;

	
	public PseudoBase() {
		this.currentQuestion = this.question;
		type = 1;
	}

	@Override
	public IResult<Question> getCurrentQuestion() {
		return new QuestionResult<Question>(this.currentQuestion);
	}

	@Override
	public Boolean switchQuestion() {
		if (type == 1)
		{
			type = 2;
			this.currentQuestion = this.secondQuestion;
		}
		else
		{
			type = 1;
			this.currentQuestion = this.question;
		}
		return true;
	}
}
