package main;

public class Result implements IResult<String> {
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
	
	public String getInfo() {
		return this.state == ResultState.SUCCESS ? result : this.errorMessage;
	}
	
	public Result(String string) { 
		errorMessage = null;
		result = string;
		this.state = ResultState.SUCCESS;
	}
	
	public Result(String string, ResultState resultState) {
		
		if (resultState == ResultState.SUCCESS)
		{
			errorMessage = null;
			result = string;
		}
		else
		{		
			errorMessage = string;
			result = null;
		}
		this.state = resultState;
		
	}
}
