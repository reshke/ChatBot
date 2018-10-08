package main;
import java.util.HashMap;
import java.util.Map;

import main.Commands.CommonUserDialog;

public class DialogManager {
	
	private Map<Integer, IDialog> dialogs = new HashMap<Integer, IDialog>();
	
	public void StartGameDialog(int userId){
		CommonUserDialog dialog = new CommonUserDialog();
		dialogs.put(userId, dialog);
	}
	
	public ResultInformation HandleQuery(int userId, String line) {
		return dialogs.get(userId).HandleQuery(line);
	}
}
