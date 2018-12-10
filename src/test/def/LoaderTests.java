package def;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bot.Game;
import classLoader.ModuleLoader;


public class LoaderTests {
	private final ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\target\\test-classes\\def\\", "def.");

//	@Before
//	public void before() throws IOException
//	{
//
//		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\target\\classes\\Test\\MockGame.class");
//		String data = "package Test;\r\n" + 
//				"\r\n" + 
//				"import main.Game;\r\n" + 
//				"import main.ICommand;\r\n" + 
//				"import main.IResult;\r\n" + 
//				"import main.Result;\r\n" + 
//				"\r\n" + 
//				"class MockCommand implements ICommand<String>\r\n" + 
//				"{\r\n" + 
//				"\r\n" + 
//				"	@Override\r\n" + 
//				"	public String getKey() {\r\n" + 
//				"		return \"mockOne\";\r\n" + 
//				"	}\r\n" + 
//				"\r\n" + 
//				"	@Override\r\n" + 
//				"	public String getCommandName() {\r\n" + 
//				"		return \"mockOne\";\r\n" + 
//				"	}\r\n" + 
//				"\r\n" + 
//				"	@Override\r\n" + 
//				"	public String executeCommand(String[] args) {\r\n" + 
//				"		return \"some result\";\r\n" + 
//				"	}\r\n" + 
//				"	\r\n" + 
//				"}\r\n" + 
//				"\r\n" + 
//				"public class MockGame extends Game {\r\n" + 
//				"	@Override\r\n" + 
//				"	public ICommand<String>[] get_commands() {\r\n" + 
//				"		return new MockCommand[] { new MockCommand()};\r\n" + 
//				"	}\r\n" + 
//				"	\r\n" + 
//				"	@Override \r\n" + 
//				"	public IResult<String> getHelp(){return new Result(\"mockHelp\");}\r\n" + 
//				"	\r\n" + 
//				"\r\n" + 
//				"	@Override\r\n" + 
//				"	public IResult<String> gameName() { return new Result(\"mockGame\"); }\r\n" + 
//				"	\r\n" + 
//				"	@Override\r\n" + 
//				"	public IResult<String> getGameDescriptor() { return new Result(\"mockmock\"); }\r\n" + 
//				"}\r\n" + 
//				"";
//		fos.write(data.getBytes());
//		fos.flush();
//		fos.close();
//	}

	@Test
	public void test_moduleLoader_load_class_correct()
	{
		byte[] data;
		try {
			data = this.moduleLoader.loadClassFromFile("MockGame");
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_moduleLoader_finds_game_correct()
	{
		try {
			@SuppressWarnings({ "unused", "rawtypes" })
			Class game = this.moduleLoader.findClass("MockGame");
			assertTrue(true);
		} catch (ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_game_execute_help_command_well()
	{
		try {
			@SuppressWarnings("deprecation")
			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
			assertTrue(game.getHelp().getResult().equals("mockHelp"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_game_execute_getName_command_well()
	{
		try {
			@SuppressWarnings("deprecation")
			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
			assertTrue(game.gameName().getResult().equals("mockGame"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_game_execute_getGameDescriptor_command_well()
	{
		try {
			@SuppressWarnings("deprecation")
			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
			assertTrue(game.getGameDescriptor().getResult().equals("mockmock"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_game_execute_command_well()
	{
		try {
			@SuppressWarnings("deprecation")
			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
			assertTrue(game.executeQuery(new String[] {"mockOne"}).getResult().equals("some result"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void test_game_execute_gamehelp_command_well()
	{
		try {
			@SuppressWarnings("deprecation")
			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
			assertTrue(game.executeQuery(new String[] {"gamehelp"}).getResult().equals("mockHelp"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			fail();
		}
	}


//	@After
//	public void after()
//	{
//		(new File(System.getProperty("user.dir") + "\\bin\\main\\Games\\MockGame.class")).delete();
//	}
}