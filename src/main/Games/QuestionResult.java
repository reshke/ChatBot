package main.Games;

import main.IResult;
import main.ResultState;

class QuestionResult<Question> implements IResult<Question>
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