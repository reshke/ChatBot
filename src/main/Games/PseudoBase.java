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
			// TODO Auto-generated method stub
			return question;
		}

		@Override
		public String getError() {
			// TODO Auto-generated method stub
			return "some error";
		}

		@Override
		public ResultState getState() {
			// TODO Auto-generated method stub
			return ResultState.SUCCESS;
		}
		
	}
	
	private Question question = new Question("2 + 2 = ????", "5");

	@Override
	public IResult<Question> getCurrentQuestion() {
		// TODO Auto-generated method stub
		return new QuestionResult<Question>(this.question);
	}

	@Override
	public IResult<Question> getNextQuestion() {
		// TODO Auto-generated method stub
		return this.getCurrentQuestion();
	}
	
}
