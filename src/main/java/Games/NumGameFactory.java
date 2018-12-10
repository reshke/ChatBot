package Games;

import bot.CommandContainer;
import bot.IGameFactory;
import bot.RandomGenerator;

public class NumGameFactory implements IGameFactory {

	@Override
	public NumGame Create() {
		return new NumGame(new RandomGenerator(), new CommandContainer());
	}

}