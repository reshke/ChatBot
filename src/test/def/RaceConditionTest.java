package def;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.channels.OverlappingFileLockException;

import org.junit.jupiter.api.Test;

import Games.NumGameFactory;
import IO.GameSaver;

class RaceConditionTest {
	Boolean cond;

	@Test
	void test() throws InterruptedException {
		int maxn = 300;
		Long userId = 12L;
		String name = "mySave";
		cond = false;
		
		GameSaver saver = new GameSaver(System.getProperty("user.dir") + "\\out\\production\\main\\data\\");
		Thread[] threads = new Thread[maxn];
		
		for (int i = 0; i < maxn;i ++){
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						saver.saveGame(new NumGameFactory().Create(), userId, name);
					}
					catch (OverlappingFileLockException e)
					{
						cond = true;
					}
				}
			}, i + "thread");
		}
		
		
		for (int i = 0; i < maxn; i ++)
			threads[i].start();
		
		for (int i = 0; i < maxn; i ++)
			threads[i].join();
		
		assertTrue(cond);
	}

}
