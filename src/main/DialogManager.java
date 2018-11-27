package main;
import java.util.HashMap;
import java.util.Map;

import main.classLoader.LoaderGames;

public class DialogManager implements IDialogManager {
	
	private final Map<Long, IDialogCommon> dialogs = new HashMap<Long, IDialogCommon>();
	
	public void startDialog(Long userId){
		
		CommonUserDialog dialog = new CommonUserDialog((new LoaderGames()).Load());
		dialogs.put(userId, dialog);
	}
	
	public IResult<String> handleQuery(Long userId, String line) {
		return dialogs.get(userId).handleQuery(line);
	}
}
