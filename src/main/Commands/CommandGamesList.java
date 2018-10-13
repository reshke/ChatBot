package main.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.ICommand;

public class CommandGamesList<TValue> implements ICommand<TValue> {

	private final String name;
	private final TValue key;
	
	public CommandGamesList(TValue key, String name) {
		this.name = name;
		this.key = key;
	}
	
	private File getHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "gamesList.txt");
	}
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		FileReader gamesListFile = null;
		try {
			gamesListFile = new FileReader(getHelpFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int c;
        try {
			while((c=gamesListFile.read())!=-1){
			    data.append((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        return data.toString();
	}

	@Override
	public TValue getKey() {
		return key;
	}

}
