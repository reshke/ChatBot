package main;
import java.util.HashMap;
import java.util.Map;

public class DialogManager implements IDialogManager {
	
	private final Map<Integer, IDialogCommon> dialogs = new HashMap<Integer, IDialogCommon>();
	
	public void StartDialog(int userId){
		CommonUserDialog dialog = new CommonUserDialog();
		dialogs.put(userId, dialog);
	}
	
	public IResult handleQuery(int userId, String line) {
		return dialogs.get(userId).handleQuery(line);
	}
}
