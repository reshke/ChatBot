package main;

import main.IO.Reader;
import main.IO.Writer;


public class StartPoint{
	public static void main(String[] args) {
		Bot bot = new Bot(new Reader(), new Writer(), new DialogManager());
		bot.startBot();
		
		while (true)
			bot.executeQuery();
	}
}
