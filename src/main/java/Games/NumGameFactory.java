package Games;

import userDialog.CommandContainer;
import userDialog.IGameFactory;
import userDialog.RandomGenerator;

public class NumGameFactory implements IGameFactory {

	@Override
	public NumGame Create() {
		return new NumGame(new RandomGenerator(), new CommandContainer());
	}

}