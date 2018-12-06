package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameSaver implements IGameSaver {
	private final String path;
	private Map<String, Class<? extends Game>> biection = new HashMap<String, Class<? extends Game>>();
	
	public GameSaver(String path){
		this.path = path;
	}
	
	@Override
	public void saveGame(Game game, String name) {

		ObjectMapper mapper = new ObjectMapper();
		byte[] src;
		try {
			src = mapper.writeValueAsBytes(game);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			return;
		}
		
		File file = new File(this.path + name);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(file);
			writer.write(src);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public Game LoadGame(String gameName, String name) {

		File file = new File(this.path + name);
		StringBuilder data = new StringBuilder();
		
		FileReader fr;
		try {
			fr = new FileReader(file);
			
			int i;
		
			while ((i=fr.read()) != -1) {
				System.out.print((char) i);
				data.append((char) i);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		
		ObjectMapper objectMapper= new ObjectMapper();
		Game game = null;
		try {
			game = objectMapper.readValue(data.toString(), biection.get(gameName));
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return game;
	}

	@Override
	public void registerGame(String name, Class<? extends Game> _class) {
		this.biection.put(name, _class);
	}

}
