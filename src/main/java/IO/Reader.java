package IO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader implements IReader {
	private final Scanner scanner = new Scanner(System.in);
	
	@Override
	public String ReadQuery()
	{ 
		return scanner.nextLine();
	}
	private static File getHelpFile(String fullPath, String fileName){
		//String path = new File("").getAbsolutePath();
		//String fullPath = path + "\\src\\main\\data\\";
		return new File(fullPath, fileName);
	}
	
	public String getHelp(String fullPath, String fileName) {
		FileReader helpFile = null;
		try {
			helpFile = new FileReader(getHelpFile(fullPath, fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder data = new StringBuilder();
		int charCode;
        try {
			while((charCode=helpFile.read())!=-1){
			    data.append((char)charCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        return data.toString();
	}
	
	@Override
	public String ReadFile(String fullPath, String fileName) {
		// TODO Auto-generated method stub
		return getHelp(fullPath, fileName);
	}
}
