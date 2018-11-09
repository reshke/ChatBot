package main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import main.IO.IReader;



public class GamesHelper implements IHelper {

	private final String GUESS_STRING_GAME_FILE_PATH = "stringGuessHelp.txt";
	private final String NUM_GAME_FILE_PATH = "numGameHelp.txt";
	private final String CHKG_GAME_FILE_PATH = "chgkHelp.txt";
	
	
	class Path<T1, T2> {
		private T1 first;
		private T2 second;
		
		Path(T1 first, T2 second){
			this.first = first;
			this.second = second;
		}
	}
	
	private final Map<TypeGame, Path<String, String>> gameHelpPaths;
	private final IReader reader;
	
	private void addFilePathToGameType(String fileName, TypeGame gameType) {
		Path<String, String> path = new Path<String, String>(new File("").getAbsolutePath() + "\\src\\main\\data\\",
				fileName);
		gameHelpPaths.put(gameType, path);
	}
	
	public GamesHelper(IReader reader){
		this.reader = reader;
		gameHelpPaths = new HashMap<TypeGame, Path<String, String>>();
		addFileNamesForGames();
	}
	
	private void addFileNamesForGames() {
		addFilePathToGameType(GUESS_STRING_GAME_FILE_PATH, TypeGame.GUESS_STRING);
		addFilePathToGameType(NUM_GAME_FILE_PATH, TypeGame.NUM_GAME);
		addFilePathToGameType(CHKG_GAME_FILE_PATH, TypeGame.CHGK_Game);
	}

	@Override
	public String getHelp(TypeGame typeGame) {
		Path<String, String> gameHelpPath =  gameHelpPaths.get(typeGame);
		return reader.ReadFile(gameHelpPath.first, gameHelpPath.second);
	}
	
}
