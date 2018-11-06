package main.Games;

import main.IResult;

public interface IQuestionContainer 
{
	IResult<Question> getCurrentQuestion();
	IResult<Question> getNextQuestion();
}
