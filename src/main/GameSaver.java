package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GameSaver implements IGameSaver {
	private final String path;
	
	public GameSaver(String path){
		this.path = path;
	}
	
	@Override
	public void saveGame(byte[] data, String name) {
		File file = new File(this.path + name);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(file);
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public Game LoadGame(String name) {

		File file = new File(this.path + name);
		StringBuilder data = new StringBuilder();
		
		FileReader fr;
		try {
			fr = new FileReader(file);
			
			int i;
		
			while ((i=fr.read()) != -1) 
			      System.out.print((char) i);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		ObjectMapper objectMapper= new ObjectMapper();
		Game game = null;
		try {
			game = objectMapper.readValue(data.toString(), Game.class);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return game;
	}

}
