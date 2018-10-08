package main.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.ICommand;

public class CommandGamesList implements ICommand {

	private final String name;
	
	public CommandGamesList(String name) {
		this.name = name;
	}
	
	private File getHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "gamesList.txt");
	}
	
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		// TODO Auto-generated method stub
		
		FileReader gamesListFile = null;
		try {
			gamesListFile = new FileReader(getHelpFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int c;
        try {
			while((c=gamesListFile.read())!=-1){
			    data.append((char)c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        return data.toString();
	}

}
