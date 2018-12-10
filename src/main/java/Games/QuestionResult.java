package main.java.Games;

import main.java.bot.IResult;
import main.java.bot.ResultState;

@SuppressWarnings("hiding")
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

		@Override
		public Question getInfo() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}