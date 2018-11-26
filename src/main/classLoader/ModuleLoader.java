package main.classLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import main.Game;

public class ModuleLoader extends ClassLoader {
	protected final String pathbin;
	public ModuleLoader(String pathbin)
	{
		this.pathbin = pathbin;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public Class<? extends Game> findClass(String name) throws ClassNotFoundException {
        byte[] b;
		try {
			b = loadClassFromFile(name);

	        return (Class<? extends Game>) defineClass(name, b, 0, b.length);
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		throw new ClassNotFoundException();
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