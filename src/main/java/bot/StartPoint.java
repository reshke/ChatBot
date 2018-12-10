package bot;

import IO.IReader;
import IO.IWriter;
import IO.Reader;
import IO.Writer;
import classLoader.LoaderGames;


public class StartPoint{
	public static void main(String[] args) {
		Bot bot = new Bot(new DialogManager(), new LoaderGames());
		IReader reader = new Reader();
		IWriter writer = new Writer();
		
		writer.WriteLine(bot.startBot(0L));
		
		while (true)
			writer.WriteLine(bot.executeQuery(0L, reader.ReadQuery()));
	}
}