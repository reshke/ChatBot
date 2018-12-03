package main;

public interface IBot {
	public String startBot(Long userId);
	public String executeQuery(Long userId, String query);
	public String[] getExecutableCommands(Long userId);
}