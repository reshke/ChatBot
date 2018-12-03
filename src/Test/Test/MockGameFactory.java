package Test;

import main.Game;
import main.IGameFactory;

public class MockGameFactory implements IGameFactory
{	
	public Game Create()
	{
		return new MockGame();
	}
}

