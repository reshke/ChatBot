package main.java.bot;

import main.java.IO.IReader;
import main.java.IO.IWriter;
import main.java.IO.Reader;
import main.java.IO.Writer;
import main.java.classLoader.LoaderGames;


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