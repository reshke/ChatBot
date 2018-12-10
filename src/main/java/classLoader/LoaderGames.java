package main.java.classLoader;

import java.util.HashMap;

import kotlin.Pair;
import main.java.bot.Game;
import main.java.bot.IGameLoaderFactory;
import main.java.bot.IResult;
import main.java.bot.ResultState;

public class LoaderGames implements IGameLoaderFactory {
	public HashMap<String, Game> Load()
	{
		ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\target\\classes\\main\\java\\Games\\", "main.java.Games.");
		HashMap<String, Game>  games = new HashMap<String, Game> ();
		try {
			for (Pair<String, Game> gameInfo : moduleLoader.loadFabrics())
			{
				IResult<String> gameName = gameInfo.component2().gameName();
				if (gameName.getState() == ResultState.SUCCESS)
					games.put(gameName.getResult(), gameInfo.component2());
				else
					games.put(gameInfo.component1(), gameInfo.component2());
			}
		} catch (IllegalArgumentException | SecurityException e) {
		}
		return games;
	}
}