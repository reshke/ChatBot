package main;

import main.IO.IReader;
import main.IO.IWriter;
import main.IO.Reader;
import main.IO.Writer;
import main.classLoader.LoaderGames;


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