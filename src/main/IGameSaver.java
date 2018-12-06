package main;

public interface IGameSaver {
	public void saveGame(Game game, String name);
	public Game LoadGame(String gameName, String name);
	public void registerGame(String name, Class<? extends Game> _class);
}
