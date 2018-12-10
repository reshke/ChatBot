package Games;

import bot.Game;
import userDialog.CommandContainer;
import userDialog.IGameFactory;
import userDialog.RandomGenerator;

public class StringGuessGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new StringGuessGame(10, new RandomGenerator(), new CommandContainer());
	}

}