package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Bot;
import main.DialogManager;
import main.Commands.CommandHelp;
import main.classLoader.LoaderGames;

class TestBotUserInteaction {

	@Test
	void testBotExecutesHelpCommand() {
		Bot bot = new Bot(new DialogManager(), new LoaderGames());

		bot.startBot(0L);
		CommandHelp<String> command = new CommandHelp<String>("help", "help");

		assertEquals(bot.executeQuery(0L, "help"), command.executeCommand(null));
	}
}

