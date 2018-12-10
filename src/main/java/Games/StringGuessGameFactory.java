package main.java.Games;

import main.java.bot.CommandContainer;
import main.java.bot.Game;
import main.java.bot.IGameFactory;
import main.java.bot.RandomGenerator;

public class StringGuessGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new StringGuessGame(10, new RandomGenerator(), new CommandContainer());
	}

}