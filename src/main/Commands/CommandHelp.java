package main.Commands;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.ICommand;

public class CommandHelp implements ICommand {
	
	private final String name;
	
	public CommandHelp(String name) {
		this.name = name;
	}
	
	private File getHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "help.txt");
	}
	
	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String executeCommand(String[] args) {
		FileReader helpFile = null;
		try {
			helpFile = new FileReader(getHelpFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int c;
        try {
			while((c=helpFile.read())!=-1){
			    data.append((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        return data.toString();
	}

}
