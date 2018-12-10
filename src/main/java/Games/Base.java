 package main.java.Games;

 import main.java.bot.IResult;

public class Base implements IQuestionContainer {
	
	//private static final Gson gson = new Gson();
	private Question currentQuestion;
	
	public Base() {
	}

	@Override
	public IResult<Question> getCurrentQuestion() {
		return new QuestionResult<Question>(this.currentQuestion);
	}


	@Override
	public Boolean switchQuestion() {
		return null;
	}

}
