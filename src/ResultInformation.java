
public class ResultInformation {
	public final String Result;
	public final String ErrorMessage;
	public final ResultState State;
	public ResultInformation(String result, ResultState state)
	{
		if (state == ResultState.Success) {	
			Result = result;
			ErrorMessage = "";
		}
		else {
			ErrorMessage = result;
			Result = "";
		}
		State = state;
	}
	
}
