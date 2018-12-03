//package Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//import main.Bot;
//import main.DialogManager;
//import main.Commands.CommandHelp;
//import main.IO.IReader;
//import main.IO.IWriter;
//
//class MockReader implements IReader{
//	private String data;
//	public MockReader(String data) {
//		this.data = data;
//	}
//
//	@Override
//	public String ReadQuery() {
//		return data;
//	}
//
//	@Override
//	public String ReadFile(String fullPath, String fileName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
//
//class MockWriter implements IWriter {
//	public String lastQuery;
//	@Override
//	public void WriteLine(String line) {
//		// TODO Auto-generated method stub
//		lastQuery = line;
//	}
//}
//
//class TestBotUserInteaction {
//
//	@Test
//	void testBotExecutesHelpCommand() {
//		MockWriter writer =new MockWriter();
//		Bot bot = new Bot(new MockReader("help"), writer, new DialogManager());
//
//		bot.startBot();
//		bot.executeQuery();
//		CommandHelp<String> command = new CommandHelp<String>("help", "help");
//
//		assertEquals(writer.lastQuery, command.executeCommand(null));
//	}
//}
//
