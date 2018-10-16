package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Bot;
import main.DialogManager;
import main.Commands.CommandHelp;
import main.IO.IReader;
import main.IO.IWriter;

class MockReader implements IReader{
	private String data;
	public MockReader(String data) {
		this.data = data;
	}

	@Override
	public String ReadQuery() {
		// TODO Auto-generated method stub
		return data;
	}
	
}

class MockWriter implements IWriter {
	public String lastQuery;
	@Override
	public void WriteLine(String line) {
		// TODO Auto-generated method stub
		lastQuery = line;
	}
}

class TestBotUserInteaction {

	@Test
	void testBotExecutesHelpCommand() {
		MockWriter writer =new MockWriter();
		Bot bot = new Bot(new MockReader("help"), writer, new DialogManager());
		
		bot.StartBot();
		bot.ExecuteQuery();
		ICommandHelp command = new CommandHelp("help");
		
		assertEquals(writer.lastQuery, command.executeCommand(null));
	}
	

}

