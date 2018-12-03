package main.Games;

import main.Game;
import main.IGameFactory;
import main.RandomGenerator;

public class NumGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new NumGame(new RandomGenerator());
	}

}