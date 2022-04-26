package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import communication.Client;
import communication.Server;


public class Matchmaking {
	
	public static CopyOnWriteArrayList<Queue> matchQuick = new CopyOnWriteArrayList<>();
	public static CopyOnWriteArrayList<Queue> matchQuickRemove = new CopyOnWriteArrayList<>();
	public static ConcurrentHashMap<Queue, Queue> matchFoundQuick = new ConcurrentHashMap<>();
	
	public static  ArrayList<Server> allServers = new ArrayList<>();
	public static CopyOnWriteArrayList<Client> allClients = new CopyOnWriteArrayList<>();
	
	public static ConcurrentHashMap<Client, Integer> connectionsClient = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<Server, Integer> connectionsServer = new ConcurrentHashMap<>();
	
	
}
