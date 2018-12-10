package main.java.bot;

public interface IGameSaver {
	public IResult<String> saveGame(Game game, Long userId, String[] args);
	public Game LoadGame(String gameName, Long userId, String name);
	public void registerGame(String name, Class<? extends Game> _class);
	public String getSavesList(Long userId);
}
