package main;
import java.util.HashMap;
import java.util.Map;

public class DialogManager implements IDialogManager {
	
	private final Map<Long, IDialogCommon> dialogs = new HashMap<Long, IDialogCommon>();
	
	public void startDialog(Long userId){
		CommonUserDialog dialog = new CommonUserDialog();
		dialogs.put(userId, dialog);
	}
	
	public IResult handleQuery(Long userId, String line) {
		return dialogs.get(userId).handleQuery(line);
	}
}
