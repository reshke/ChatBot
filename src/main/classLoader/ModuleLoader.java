package main.classLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import main.Game;

public class ModuleLoader extends ClassLoader {
	private final String pathbin;
	public ModuleLoader(String pathbin)
	{
		this.pathbin = pathbin;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b;
		try {
			b = loadClassFromFile(name);

	        return defineClass("main.Games." + name, b, 0, b.length);
	        
		} catch (FileNotFoundException e) {
			
		}
        catch (LinkageError e) {}
		throw new ClassNotFoundException();
    }
    
    public Game[] loadGames()
    {
    	ArrayList<Game> result = new ArrayList<>();

        File dir = new File(pathbin);
        String[] modules = dir.list();

        for (String module: modules) {
            try {
                String moduleName = module.split(".class")[0];
                Class currentClass = findClass(moduleName);
                try  {
                	result.add((Game) currentClass.newInstance());
                }
                catch (ClassCastException e) {}
            }
            catch (IllegalAccessException | ClassNotFoundException | InstantiationException e)
            {

            }
        }

        return result.toArray(new Game[0]);
    }
 
    private byte[] loadClassFromFile(String fileName) throws FileNotFoundException  {
        InputStream inputStream = new FileInputStream(new File(pathbin + fileName + ".class"));
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}