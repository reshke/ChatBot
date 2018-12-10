package Commands;

import java.io.Serializable;
import java.util.function.Function;

import bot.IResult;
import bot.ResultState;
import userDialog.ICommand;

public final class Command implements ICommand<String>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7527642818158797991L;
	protected final String key;
	protected Function<String[], IResult<String>> function;
	
	public Command(String key, Function<String[], IResult<String>> function)
	{
		this.key = key;
		this.function = function;
	}
	
	@Override
	public String getKey() {return key;}

	@Override
	public java.lang.String getCommandName() {return key;}

	@Override
	public String executeCommand(java.lang.String[] args)  {
		IResult<String> result = this.function.apply(args);
		return result.getState() == ResultState.SUCCESS ? result.getResult() : result.getError();
	}
}
