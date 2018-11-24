package main.classLoader;
import java.io.File;
import main.classLoader.IModule;

public class ModuleEngine {
	public static void runModule(String modulePath, String requiredModuleName) {
	    ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());
	    File dir = new File(modulePath);
	    String[] modules = dir.list();
	    
	    for (String module: modules) {
	      try {
	        String moduleName = module.split(".class")[0];
	        if (moduleName.equals(requiredModuleName))
	        {
		        Class<?> clazz = loader.loadClass(moduleName);
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