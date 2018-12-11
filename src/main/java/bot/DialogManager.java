package bot;
import java.util.HashMap;
import java.util.Map;

import Commands.CommandHelp;
import IO.GameSaver;
import userDialog.CommandContainer;
import userDialog.CommonUserDialog;
import userDialog.ICommand;
import userDialog.ICommonDialogFactory;
import userDialog.Result;

public class DialogManager implements IDialogManager {
	private final ICommonDialogFactory dialogFactory;
	private final Map<Long, IDialogCommon> dialogs = new HashMap<Long, IDialogCommon>();
	
	public DialogManager(ICommonDialogFactory dialogFactory) {
		this.dialogFactory = dialogFactory;
	}
	
	public void startDialog(Long userId, IGameLoaderFactory factory){
		@SuppressWarnings("unchecked")
		IDialogCommon dialog = this.dialogFactory.createDialog(factory, userId);
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