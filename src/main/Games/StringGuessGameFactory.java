package main.Games;

import main.Game;
import main.IGameFactory;
import main.RandomGenerator;

public class StringGuessGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new StringGuessGame(10, new RandomGenerator());
	}

}