package main;
import java.util.HashMap;
import java.util.Map;

public class DialogManager implements IDialogManager {
	
	private final Map<Long, IDialogCommon> dialogs = new HashMap<Long, IDialogCommon>();
	
	public void startDialog(Long userId, IGameLoaderFactory factory){
		CommonUserDialog dialog = new CommonUserDialog(factory.Load());
		dialogs.put(userId, dialog);
	}
	
	public IResult<String> handleQuery(Long userId, String line) {
		return dialogs.get(userId).handleQuery(line);
	}

	@Override
	public String[] getUserExecutableCommands(Long userId) {
		return dialogs.get(userId).getCurrentUserExecutableCommands();
	}
}