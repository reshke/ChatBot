package main;
import java.util.HashMap;
import java.util.Map;

import main.Commands.CommandHelp;

public class DialogManager implements IDialogManager {
	
	private final Map<Long, IDialogCommon> dialogs = new HashMap<Long, IDialogCommon>();
	
	public void startDialog(Long userId, IGameLoaderFactory factory){
		@SuppressWarnings("unchecked")
		CommonUserDialog dialog = 
				new CommonUserDialog(factory.Load(),
						new CommandContainer(new ICommand[] { new CommandHelp<String>("help", "help")}),
						new GameSaver(System.getProperty("user.dir") + "\\out\\production\\ChatBot\\main\\data\\"), userId);
		dialogs.put(userId, dialog);
	}
	
	public IResult<String> handleQuery(Long userId, String line) {
		IDialogCommon dialog = dialogs.get(userId);
		if (dialog == null)
			return new Result("Dialog was not started!");
		return dialog.handleQuery(line);
	}

	@Override
	public String[] getUserExecutableCommands(Long userId) {
		return dialogs.get(userId).getCurrentUserExecutableCommands();
	}

	@Override
	public Boolean hasDialogWith(Long userId) {
		return this.dialogs.get(userId) != null;
	}
}