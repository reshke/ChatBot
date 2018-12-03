package Test;

import main.Game;
import main.ICommand;
import main.IGameFactory;
import main.IResult;
import main.Result;

class MockCommand implements ICommand<String>
{

	@Override
	public String getKey() {
		return "mockOne";
	}

	@Override
	public String getCommandName() {
		return "mockOne";
	}

	@Override
	public String executeCommand(String[] args) {
		return "some result";
	}
	
}

public class MockGameFactory implements IGameFactory
{
	private class MockGame extends Game {
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
	
	public Game Create()
	{
		return new MockGame();
	}
}

