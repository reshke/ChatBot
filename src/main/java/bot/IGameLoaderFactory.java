package main.java.bot;

import java.util.HashMap;

public interface IGameLoaderFactory {
	public HashMap<String, Game> Load();
}