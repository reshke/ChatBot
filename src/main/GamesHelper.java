package main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import main.IO.IReader;



public class GamesHelper implements IHelper {

	class Path<T1, T2> {
		private T1 first;
		private T2 second;
		
		Path(T1 first, T2 second){
			this.first = first;
			this.second = second;
		}
	}
	
	private final Map<TypeGame, Path<String, String>> biection;
	private final IReader reader;
	
	private void updateBiection(String fileName, TypeGame gameType) {
		Path<String, String> path = new Path<String, String>(new File("").getAbsolutePath() + "\\src\\main\\data\\",
				fileName);
		biection.put(gameType, path);
	}
	
	public GamesHelper(IReader reader){
		this.reader = reader;
		biection = new HashMap<TypeGame, Path<String, String>>();
		
		updateBiection("stringGuessHelp.txt", TypeGame.GUESS_STRING);
		updateBiection("numGameHelp.txt", TypeGame.NUM_GAME);
		updateBiection("chgkHelp.txt", TypeGame.CHGK_Game);
	
	}

	@Override
	public String getHelp(TypeGame typeGame) {
		// TODO Auto-generated method stub

		 Path<String, String> res =  biection.get(typeGame);
		 return reader.ReadFile(res.first, res.second);
	}
	
}
