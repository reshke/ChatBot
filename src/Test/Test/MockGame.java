package Test;

import main.Game;
import main.ICommand;
import main.ICommandContainer;
import main.IResult;
import main.Result;

public class MockGame extends Game {
		/**
	 * 
	 */
	private static final long serialVersionUID = -293615648916928687L;

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