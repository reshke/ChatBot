package main.classLoader;
import java.io.File;
import main.classLoader.IModule;

public class ModuleEngine {
	public static void loadClass(String args[]) {
		String modulePath = args[0];
		String requiredModuleName = args[1];
		ModuleLoader c = new ModuleLoader();
	    File dir = new File(modulePath);
	    String[] modules = dir.list();
	    
	    for (String module: modules) {
	      try {
	        String moduleName = module.split(".class")[0];
	        if (moduleName.equals(requiredModuleName))
	        {
		        Class<?> clazz = c.findClass(moduleName);
		        IModule execute = (IModule) clazz.newInstance(); 
		        
		        execute.load();
		        execute.run();
		        execute.unload();
	        }
	      } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	      } catch (InstantiationException e) {
	        e.printStackTrace();
	      } catch (IllegalAccessException e) {
	        e.printStackTrace();
	      }
    } 
  }
}