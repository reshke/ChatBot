package Test;
//package Test;
//
//import static org.junit.Assert.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import main.classLoader.ModuleLoader;
//
//public class LoaderTests {
//	private final ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\bin\\main\\Games\\");
//	private int len;
//	
//	
//	@Before
//	public void before() throws IOException
//	{
//		
//		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\bin\\main\\Games\\MockGame.class");
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
//		len = data.length();
//		fos.flush();
//		fos.close();
//	}
//	
//	@Test
//	public void test_moduleLoader_load_class_correct()
//	{
//		byte[] data;
//		try {
//			data = this.moduleLoader.loadClassFromFile("MockGame");
//
//			assertTrue(data.length == len);
//		} catch (FileNotFoundException e) {
//			fail();
//		}
//	}
//	
////	@Test
////	public void test_moduleLoader_finds_game_correct()
////	{
////		try {
////			Class<?> game = this.moduleLoader.findClass("MockGame");
////
////			assertTrue(true);
////		} catch (ClassNotFoundException e) {
////			fail();
////		}
////	}
////	
////	@Test
////	public void test_game_execute_help_command_well()
////	{
////		try {
////
////			Game game = (Game) this.moduleLoader.findClass("MockGame").newInstance();
////
////			assertTrue(game.getHelp().getResult().equals("mockHelp"));
////		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
////			fail();
////		}
////
////	}
////	
////	@After
////	public void after()
////	{	
////		File file = new File("\\bin\\main\\Games\\MockGame.class");
////		if (file.delete())
////		{
////			System.out.println("good");
////		}
////	}
//}
