package main.Games;

import main.CommandContainer;
import main.IGameFactory;
import main.RandomGenerator;

public class NumGameFactory implements IGameFactory {

	@Override
	public NumGame Create() {
		return new NumGame(new RandomGenerator(), new CommandContainer());
	}

}