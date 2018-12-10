package Games;

import bot.IResult;

public interface IQuestionContainer 
{
	IResult<Question> getCurrentQuestion();
	Boolean switchQuestion();
}
