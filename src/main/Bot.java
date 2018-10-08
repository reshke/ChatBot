package main;

import main.IO.Reader;
import main.IO.Writer;

public class Bot {

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot! help to see help");
		DialogManager dialogManager = new DialogManager();
		dialogManager.StartGameDialog(0);
		while (true) {
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
}
