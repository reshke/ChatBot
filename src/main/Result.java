package main;

public class Result<TValue> implements IResult<TValue> {
	private final TValue result;
	private final String errorMessage;
	private final ResultState state;
	
	public TValue getResult() {
		return result;
	}
	
	public String getError() {
		return errorMessage;
	}
	
	public ResultState getState() {
		return state;
	}
	
	public Result(TValue resultInformation, ResultState resultState)
	{
		this.result = resultInformation;
		errorMessage = null;
		this.state = resultState;
	}

	@SuppressWarnings("unchecked")
	public Result(String string, ResultState resultState) {
		
		if (resultState == ResultState.SUCCESS)
		{
			errorMessage = null;
			result = (TValue) string;
		}
		else
		{		
			errorMessage = string;
			result = null;
		}
		this.state = resultState;
		
	}
}
