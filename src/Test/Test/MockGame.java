package Test;

import main.Game;
import main.ICommand;
import main.IResult;
import main.Result;

public class MockGame extends Game {
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