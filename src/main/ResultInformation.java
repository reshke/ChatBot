package main;

public class ResultInformation {
	public final String result;
	public final String errorMessage;
	public final ResultState state;
	public ResultInformation(String result, ResultState state)
	{
		if (state == ResultState.SUCCESS) {	
			this.result = result;
			errorMessage = "";
		}
		else {
			errorMessage = result;
			this.result = "incorrect input";
		}
		this.state = state;
	}
	
}
