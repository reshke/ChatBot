package main.java.Games;

import main.java.bot.IResult;

public interface IQuestionContainer 
{
	IResult<Question> getCurrentQuestion();
	Boolean switchQuestion();
}
