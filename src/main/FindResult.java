package main;

import main.Commands.CommandHelp;

public class FindResult implements IResult<ICommand> {
	private final ICommand result;
	private final String errorMessage;
	private final ResultState state;
	
	public ICommand getResult() {
		return result;
	}
	
	public String getError() {
		return errorMessage;
	}
	
	public ResultState getState() {
		return state;
	}
	
	public FindResult(ICommand result, ResultState state)
	{
		if (state == ResultState.SUCCESS) {	
			this.result = result;
			errorMessage = "";
		}
		else {
			errorMessage = result.getCommandName();
			this.result = new CommandHelp<String>("help", "help");
		}
		this.state = state;
	}

}
