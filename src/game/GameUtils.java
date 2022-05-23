package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GameUtils {

	public static Hashtable<String,  Semaphore> games = new Hashtable<String,  Semaphore>();
	public static Hashtable<String,  Integer> gamesWinner = new Hashtable<String, Integer>();
	
//	public static Hashtable<String, String[]> allAbilitiesUsed = new Hashtable<String, String[]>();
//	public static Hashtable<String, String[]> allCharsUsedSkill = new Hashtable<String, String[]>();
//	public static Hashtable<String, String[]> allTargets = new Hashtable<String, String[]>();
//	public static Hashtable<String, String[]> allAllyEnemy = new Hashtable<String, String[]>();
//	public static Hashtable<String, String[]> allAbilitiesID = new Hashtable<String, String[]>();
//	
	//activeSkills
	public static Hashtable<Integer, ArrayList<String>> activeAbilitiesUsed = new Hashtable<Integer, ArrayList<String>>();
	public static Hashtable<Integer, ArrayList<String>> activeCharsUsedSkill = new Hashtable<Integer, ArrayList<String>>();
	public static Hashtable<Integer, ArrayList<String>> activeTargets = new Hashtable<Integer, ArrayList<String>>();
	public static Hashtable<Integer, ArrayList<String>> activeAllyEnemy = new Hashtable<Integer, ArrayList<String>>();
	public static Hashtable<Integer, ArrayList<String>> activeAbilitiesID = new Hashtable<Integer, ArrayList<String>>();
	
	//fechar jogo quando ambos os jogadores sairem
	public static Hashtable<String, Integer> gamesFinish = new Hashtable<String, Integer>();
	
	public static List<Queue> matchQuick = Collections.synchronizedList(new ArrayList<Queue>());
	public static Hashtable<Queue,  Queue> matchQuickFound = new Hashtable<Queue,  Queue>();

	
	public static Semaphore semQuick = new Semaphore(1);
	public static Semaphore semQuickRemove = new Semaphore(1);

}
