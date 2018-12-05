package main.Commands;

import java.util.function.Supplier;

import main.ICommand;

public class SaveAndPauseGameCommand implements ICommand<String> {
	protected final Supplier<String> supplier;
	public SaveAndPauseGameCommand(Supplier<String> supplier){
		this.supplier = supplier;
	}

	@Override
	public String getKey() {
		return "pause";
	}

	@Override
	public String getCommandName() {
		return "pause";
	}

	@Override
	public String executeCommand(String[] args) {
		return supplier.get();
	}
}
