package main;

import main.IO.IReader;
import main.IO.IWriter;

public class Bot {

	private final IReader reader;
	private final IWriter writer;
	private final IDialogManager dialogManager;
	
	public Bot(IReader reader, IWriter writer, IDialogManager dialogManager) {
		this.reader = reader;
		this.writer = writer;
		this.dialogManager = dialogManager;
	}
	
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot! help to see help");
		
		dialogManager.startDialog(0L);
	}
	
	public void ExecuteQuery(){
		String query = reader.ReadQuery();
		IResult<String> result = dialogManager.handleQuery(0L, query);
		ResultState state = result.getState();
		
		if (state == ResultState.SUCCESS)
			writer.WriteLine(result.getResult());
		if (state == ResultState.UNKNOWN)
			writer.WriteLine(" do not understand");
		if (state == ResultState.WRONG_ARGUMENTS)
			writer.WriteLine(result.getError());
	}
}
