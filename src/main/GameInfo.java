//package main;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//
//import main.classLoader.ModuleLoader;
//
//public final class GameInfo {
//	protected final Game game;
//	protected final HashMap<String, String> commands;
//	protected final ModuleLoader moduleLoader = new ModuleLoader(System.getProperty("user.dir") + "\\bin\\");
//	
//	
//	public GameInfo(String gameName) throws ReflectiveOperationException
//	{
//		try 
//		{
//			game = this.moduleLoader.findClass(gameName).newInstance();
//			this.commands = game.getCommands();
//		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			throw e;
//		}
//	}
//	
//	public IResult<String> executeQuery(String args[])
//	{
//		String methoodName = this.commands.get(args[0]);
//		a =  Method.invoke(game, args).;
//	}
//	
//
//	public IResult<String> getHelp(String args) {
//		return new Result("no help", ResultState.UNSUPPORTED_OPERATION);
//	}
//}
