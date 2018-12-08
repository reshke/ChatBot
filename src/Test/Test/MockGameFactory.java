package Test;

import main.CommandContainer;
import main.Game;
import main.IGameFactory;

public class MockGameFactory implements IGameFactory
{	
	public Game Create()
	{
		return new MockGame(new CommandContainer());
	}
}

