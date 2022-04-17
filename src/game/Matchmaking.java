package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class Matchmaking {
	
	public static CopyOnWriteArrayList<Queue> matchQuick = new CopyOnWriteArrayList<>();
	public static CopyOnWriteArrayList<Queue> matchQuickRemove = new CopyOnWriteArrayList<>();
	public static ConcurrentHashMap<Queue, Queue> matchFoundQuick = new ConcurrentHashMap<>();
	
	public static boolean search = true;
	
	
}
