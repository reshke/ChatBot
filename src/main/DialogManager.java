package main;
import java.util.HashMap;
import java.util.Map;

public class DialogManager {
	
	private final Map<Integer, IDialog> dialogs = new HashMap<Integer, IDialog>();
	
	public void StartGameDialog(int userId){
		CommonUserDialog dialog = new CommonUserDialog();
		dialogs.put(userId, dialog);
	}
	
	public ResultInformation handleQuery(int userId, String line) {
		return dialogs.get(userId).handleQuery(line);
	}
}
