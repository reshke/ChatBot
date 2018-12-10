package bot;

public interface IBot {
	public String startBot(Long userId);
	public String executeQuery(Long userId, String query);
	public Boolean hasDialogWith(Long userId);
	public String[] getExecutableCommands(Long userId);
}