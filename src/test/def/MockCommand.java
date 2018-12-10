package def;
import userDialog.ICommand;

public class MockCommand implements ICommand<String>
{

	@Override
	public String getKey() {
		return "mockOne";
	}

	@Override
	public String getCommandName() {
		return "mockOne";
	}

	@Override
	public String executeCommand(String[] args) {
		return "some result";
	}
	
}