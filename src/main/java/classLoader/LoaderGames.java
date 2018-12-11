package classLoader;

import java.util.HashMap;
import java.util.Map.Entry;

import bot.Game;
import bot.IGameLoaderFactory;
import bot.IResult;
import bot.ResultState;
import userDialog.IGameFactory;

public class LoaderGames implements IGameLoaderFactory {
	public HashMap<String, IGameFactory> Load()
	{
		ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\target\\classes\\Games\\", "Games.");
		HashMap<String, IGameFactory>  games = new HashMap<String, IGameFactory> ();
		try {
			for (Entry<String, IGameFactory> gameInfo : moduleLoader.loadFabrics().entrySet())
			{
				IResult<String> gameName = gameInfo.getValue().Create().gameName();
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