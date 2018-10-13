package main;

public class ResultInformation implements IResult {
	private final String result;
	private final String errorMessage;
	private final ResultState state;
	
	public String getResult() {
		return result;
	}
	
	public String getError() {
		return errorMessage;
	}
	
	public ResultState getState() {
		return state;
	}
	
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
