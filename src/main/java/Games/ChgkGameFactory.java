package Games;

import bot.Game;
import userDialog.CommandContainer;
import userDialog.IGameFactory;

public class ChgkGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new CHGK_Game(new PseudoBase(), new CommandContainer());
	}
}