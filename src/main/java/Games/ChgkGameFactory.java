package main.java.Games;

import main.java.bot.CommandContainer;
import main.java.bot.Game;
import main.java.bot.IGameFactory;

public class ChgkGameFactory implements IGameFactory {

	@Override
	public Game Create() {
		return new CHGK_Game(new PseudoBase(), new CommandContainer());
	}
}