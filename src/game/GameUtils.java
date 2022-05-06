package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GameUtils {
	
	public static Hashtable<String,  Semaphore> games = new Hashtable<String,  Semaphore>();
	public static Hashtable<String,  Integer> gamesWinner = new Hashtable<String, Integer>();
	
	//fechar jogo quando ambos os jogadores sairem
	public static Hashtable<String, Integer> gamesFinish = new Hashtable<String, Integer>();
	
	public static List<Queue> matchQuick = Collections.synchronizedList(new ArrayList<Queue>());
	public static Hashtable<Queue,  Queue> matchQuickFound = new Hashtable<Queue,  Queue>();

	
	public static Semaphore semQuick = new Semaphore(1);
	public static Semaphore semQuickRemove = new Semaphore(1);

}
