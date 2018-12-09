package main;

public interface IGameSaver {
	public void saveGame(Game game, Long userId, String name);
	public Game LoadGame(String gameName, Long userId, String name);
	public void registerGame(String name, Class<? extends Game> _class);
}
