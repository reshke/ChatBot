package main.java.Games;

import main.java.bot.CommandContainer;
import main.java.bot.IGameFactory;
import main.java.bot.RandomGenerator;

public class NumGameFactory implements IGameFactory {

	@Override
	public NumGame Create() {
		return new NumGame(new RandomGenerator(), new CommandContainer());
	}

}