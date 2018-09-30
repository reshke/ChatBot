
public class Bot {

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	
	public void StartBot() {
		writer.WriteLine("Welcome! I'm chat bot!");
		while (true) {
			String query = reader.ReadQuery();
			String answer = "Answer";
			// Answer should work with bot dialog.
			writer.WriteLine(answer);
		}
	}
}
