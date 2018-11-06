package main.Games;

public final class Question 
{
	private String questionWording;
	private String questionAnswer;
	
	public Question(String questionWording, String questionAnswer)
	{
		this.questionWording = questionWording;
		this.questionAnswer = questionAnswer;
	}
	
	String getQuestionWording()
	{
		return questionWording;
	}
	
	
	String getQuestionAnswer()
	{
		return questionAnswer;
	}
}
