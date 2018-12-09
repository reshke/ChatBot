package main;

public class Bot implements IBot{

	private final IDialogManager dialogManager;
	private final IGameLoaderFactory factory;
	
	public Bot(IDialogManager dialogManager, IGameLoaderFactory factory) {
		this.dialogManager = dialogManager;
		this.factory = factory;
	}
	
	
	public String startBot(Long userId) {
		dialogManager.startDialog(userId, this.factory);
		
		return "Welcome! I'm chat bot! help to see help";
	}
	
	public String executeQuery(Long userId, String query){
		IResult<String> result = dialogManager.handleQuery(userId, query);
		ResultState state = result.getState();
		
		if (state == ResultState.SUCCESS)
			return result.getResult();
		else
			return result.getError();
	}


	@Override
	public String[] getExecutableCommands(Long userId) {
		return this.dialogManager.getUserExecutableCommands(userId);
	}


	@Override
	public Boolean hasDialogWith(Long userId) {
		return this.dialogManager.hasDialogWith(userId);
	}
	
}