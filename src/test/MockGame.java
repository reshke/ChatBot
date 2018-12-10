package test;

import main.java.bot.CommandContainer;
import main.java.bot.Game;
import main.java.bot.ICommand;
import main.java.bot.ICommandContainer;
import main.java.bot.IResult;
import main.java.bot.Result;

public class MockGame extends Game {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2486604632422819745L;
	public MockGame()
	{
		super(new CommandContainer());
	}
	public MockGame(ICommandContainer container) {
		super(container);
	}

	@Override
	public ICommand<String>[] get_commands() {
		return new MockCommand[] { new MockCommand()};
	}
	
	@Override 
	public IResult<String> getHelp(){return new Result("mockHelp");}
	

	@Override
	public IResult<String> gameName() { return new Result("mockGame"); }
	
	@Override
	public IResult<String> getGameDescriptor() { return new Result("mockmock"); }
}
