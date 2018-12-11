package bot;

import java.util.HashMap;

import userDialog.IGameFactory;

public interface IGameLoaderFactory {
	public HashMap<String, IGameFactory> Load();
}