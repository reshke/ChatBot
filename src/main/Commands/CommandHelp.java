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
	
	private File GetHelpFile(){
		String path = new File("").getAbsolutePath();
		String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, "help.txt");
	}
	
	@Override
	public String GetCommandName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String ExecuteCommand(String[] args) {
		// TODO Auto-generated method stub
		
		FileReader helpFile = null;
		try {
			helpFile = new FileReader(GetHelpFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int c;
        try {
			while((c=helpFile.read())!=-1){
			    data.append((char)c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        return data.toString();
	}

}
