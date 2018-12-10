package Games;

import bot.CommandContainer;
import bot.Game;
import bot.IGameFactory;

public class ChgkGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new CHGK_Game(new PseudoBase(), new CommandContainer());
	}
}