package main.IO;

public interface IReader {
	public String ReadQuery();
	public String ReadFile(String fullPath, String fileName);
}
