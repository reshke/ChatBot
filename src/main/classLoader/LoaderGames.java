package main.classLoader;

import java.util.HashMap;

import kotlin.Pair;
import main.Game;
import main.IGameLoaderFactory;
import main.IResult;
import main.ResultState;

public class LoaderGames implements IGameLoaderFactory {
	public HashMap<String, Game> Load()
	{
		ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\bin\\main\\Games\\", "main.Games.");
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