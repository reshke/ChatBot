package Test;

import main.ICommand;

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
