import java.util.HashMap;
import java.util.Map;

public class DialogManager {
	
	private Map<Integer, Dialog> dialogs = new HashMap<Integer, Dialog>();
	
	public void StartDialog(int userId){
		Dialog dialog = new Dialog();
		dialog.StartDialog();
		dialogs.put(userId, dialog);
	}
}
