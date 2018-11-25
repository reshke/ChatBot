package main.classLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ModuleLoader extends ClassLoader {
	 
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b;
		try {
			b = loadClassFromFile(name);

	        return defineClass(name, b, 0, b.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ClassNotFoundException();
    }
 
    private byte[] loadClassFromFile(String fileName) throws FileNotFoundException  {
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\rockl\\Desktop\\java\\ChatBot\\bin\\" + fileName + ".class"));
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