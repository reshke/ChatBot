package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GameSaver implements IGameSaver {
	private final String path;
	private Map<String, Class<? extends Game>> biection = new HashMap<String, Class<? extends Game>>();
	
	public GameSaver(String path){
		this.path = path;
	}
	
	@Override
	public void saveGame(Game game, String name) {
		try
        {    
            FileOutputStream file = new FileOutputStream(this.path + name); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            
            game.save();
            out.writeObject(game); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
        	ex.printStackTrace();
        } 
	}

	@Override
	public Game LoadGame(String gameName, String name) {
		try{    
			FileInputStream file = new FileInputStream(this.path + name); 
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

}
