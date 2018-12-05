package main;

public interface IGameSaver {
	public void saveGame(byte[] data, String name);
	public Game LoadGame(String name);
}
