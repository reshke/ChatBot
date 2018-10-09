package main;

import main.IO.IReader;
import main.IO.IWriter;

public class Bot {

	private final IReader reader;
	private final IWriter writer;
	private final DialogManager dialogManager;
	
	public Bot(IReader reader, IWriter writer, DialogManager dialogManager) {
		this.reader = reader;
		this.writer = writer;
		this.dialogManager = dialogManager;
	}
	
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot! help to see help");
		
		dialogManager.StartDialog(0);
	}
	
	public void ExecuteQuery(){
		String query = reader.ReadQuery();
		ResultInformation result = dialogManager.handleQuery(0, query);
		
		if (result.state == ResultState.SUCCESS)
			writer.WriteLine(result.result);
		if (result.state == ResultState.UNKNOWN)
			writer.WriteLine(" do not understand");
		if (result.state == ResultState.WRONG_ARGUMENTS)
			writer.WriteLine(result.errorMessage);
	}
}
