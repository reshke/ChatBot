package main.Games;

import main.IResult;
import main.ResultState;

public class PseudoBase implements IQuestionContainer {
	private class QuestionResult<Question> implements IResult<Question>
	{
		private Question question;
		
		public QuestionResult(Question question)
		{
			this.question = question;
		}

		@Override
		public Question getResult() {
			return question;
		}

		@Override
		public String getError() {
			return "some error";
		}

		@Override
		public ResultState getState() {
			return ResultState.SUCCESS;
		}
		
	}
	
	private Question question = new Question("2 + 2 = ????", "5");

	@Override
	public IResult<Question> getCurrentQuestion() {
		return new QuestionResult<Question>(this.question);
	}

	@Override
	public IResult<Question> getNextQuestion() {
		return this.getCurrentQuestion();
	}
	
}
