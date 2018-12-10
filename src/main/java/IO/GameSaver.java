package IO;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import bot.Game;
import bot.IResult;
import bot.ResultState;
import userDialog.IGameSaver;
import userDialog.Result;

public class GameSaver implements IGameSaver {
	private final String path;
	private Map<String, Class<? extends Game>> biection = new HashMap<String, Class<? extends Game>>();
	
	public GameSaver(String path){
		this.path = path;
	}
	
	public String saveGame(Game game, Long userId, String name) {
		try
        {  
		   File f = new File(this.path +userId);
			if (!f.exists())
				f.mkdir();
			
            FileOutputStream file = new FileOutputStream(this.path + userId + "\\" + name); 
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            game.save();
            out.writeObject(game); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
        	ex.printStackTrace();
        	return "Your game was saved unsucessfully!";
        } 

		return "Your game was saved sucessfully!";
	}
	

	private String getSaveName(Long userId, Game game) {
		return userId + "\\" + game.gameName().getInfo();
	}
	
	
	@Override
	public IResult<String> saveGame(Game game, Long userId, String[] args) {
		if (args.length > 2)
			return new Result("Count of args is not correct!", ResultState.WRONG_ARGUMENTS);
		
		if (game == null)
			return new Result("Game is not chosen!");
		
		if (args.length == 2)
			return new Result(this.saveGame(game, userId, args[1]));
			
		String res = this.saveGame(game, userId, this.getSaveName(userId, game));
		
		return new Result(res);	
	}
	
	
	@Override
	public Game LoadGame(String gameName, Long userId, String name) {
		try{    
			FileInputStream file = new FileInputStream(this.path + userId + "\\" + name); 
			ObjectInputStream in = new ObjectInputStream(file); 
	        Game game = (Game)in.readObject();
	        game.load();
	        in.close(); 
	        file.close(); 
	            
	        return game;
	   }catch(IOException ex){
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		
		return null; 
	}

	@Override
	public void registerGame(String name, Class<? extends Game> _class) {
		this.biection.put(name, _class);
	}

	@Override
	public String getSavesList(Long userId) {
		StringBuilder answer = new StringBuilder();
		File files = new File(this.path + "\\" + userId);
		if (!files.exists())
			return "There are no saves for now";
		for (File f : files.listFiles()){
			if (f.isFile()) {
				Path path = FileSystems.getDefault().getPath(f.getPath());
			    BasicFileAttributes attrs = null;
				try {
					attrs = Files.readAttributes(path, BasicFileAttributes.class);
					answer.append(f.getName());
					answer.append(" ");
					DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
					String cTime = df.format(attrs.creationTime().toMillis());
					answer.append(cTime);
					answer.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return answer.toString();
	}
}
