package classLoader;

import java.util.HashMap;
import java.util.Map.Entry;

import bot.Game;
import bot.IGameLoaderFactory;
import bot.IResult;
import bot.ResultState;

public class LoaderGames implements IGameLoaderFactory {
	public HashMap<String, Game> Load()
	{
		ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\target\\classes\\Games\\", "Games.");
		HashMap<String, Game>  games = new HashMap<String, Game> ();
		try {
			for (Entry<String, Game> gameInfo : moduleLoader.loadFabrics().entrySet())
			{
				IResult<String> gameName = gameInfo.getValue().gameName();
				if (gameName.getState() == ResultState.SUCCESS)
					games.put(gameName.getResult(), gameInfo.getValue());
				else
					games.put(gameInfo.getKey(), gameInfo.getValue());
			}
		} catch (IllegalArgumentException | SecurityException e) {
		}
		return games;
	}
}