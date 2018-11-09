package main;

public class Result implements IResult {
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
	
	public Result(String resultInformation, ResultState resultState)
	{
		if (resultState == ResultState.SUCCESS) {	
			this.result = resultInformation;
			errorMessage = null;
		}
		else {
			errorMessage = resultInformation;
			this.result = null;
		}
		this.state = resultState;
	}
}
