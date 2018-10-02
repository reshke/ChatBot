
public class Bot {

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot!");
		DialogManager dialogManager = new DialogManager();
		dialogManager.StartGameDialog(0);
		while (true) {
			String query = reader.ReadQuery();
			
			String answer = dialogManager.HandleQuery(0, query).Result;
			// Answer should work with bot dialog.
			writer.WriteLine(answer);
		}
	}
}
