package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.java.Commands.CommandHelp;
import main.java.bot.Bot;
import main.java.bot.DialogManager;
import main.java.classLoader.LoaderGames;
class TestBotUserInteaction {
	@Test
	void testBotExecutesHelpCommand() {
		Bot bot = new Bot(new DialogManager(), new LoaderGames());
		bot.startBot(0L);
		CommandHelp<String> command = new CommandHelp<String>("help", "help");
		assertEquals(bot.executeQuery(0L, "help"), command.executeCommand(null));
	}

	@Test
	void testBotReturnsExecutableCommandsWell() {
		Bot bot = new Bot(new DialogManager(), new LoaderGames());
		bot.startBot(0L);
		String[] commands = bot.getExecutableCommands(0L);
		assertArrayEquals(new String[] {"help", "gamesList", "savesList"}, commands);
	}

	@Test
	void testBotReturnsExecutableCommandsInGameDialogWell() {
		Bot bot = new Bot(new DialogManager(), new LoaderGames());
		bot.startBot(0L);
		bot.executeQuery(0L, "switch guessGame");
		String[] commands = bot.getExecutableCommands(0L);
		assertArrayEquals(new String[] {"help", "gamesList", "gamehelp","save", "load", "savesList"}, commands);
	}
}