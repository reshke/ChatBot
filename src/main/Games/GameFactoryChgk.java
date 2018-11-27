package main.Games;

import main.Game;
import main.IGameFactory;

public class GameFactoryChgk implements IGameFactory {

	@Override
	public Game Create() {
		return new CHGK_Game(new PseudoBase());
	}
}
