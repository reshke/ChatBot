
public class Bot {

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot!");
		DialogManager dialogManager = new DialogManager();
		dialogManager.StartGameDialog(0);
		while (true) {
			String query = reader.ReadQuery();
			ResultInformation result = dialogManager.HandleQuery(0, query);
			
			if (result.State == ResultState.Success)
				writer.WriteLine(result.Result);
			if (result.State == ResultState.Unknowm)
				writer.WriteLine("i`m dont understand");
			if (result.State == ResultState.WrongArguments)
				writer.WriteLine("incorrect input");
		
			// Answer should work with bot dialog.
		}
	}
}
