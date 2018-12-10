package Games;

import bot.CommandContainer;
import bot.Game;
import bot.IGameFactory;
import bot.RandomGenerator;

public class StringGuessGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new StringGuessGame(10, new RandomGenerator(), new CommandContainer());
	}

}