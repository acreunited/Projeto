package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.MathContext;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import game.Matchmaking;

public class ServerConnection extends Thread {
	
	private Socket socket;
	private Server server;
	private DataInputStream din;
	private DataOutputStream dout;
	private boolean shouldRun;
	private boolean buttonTurnClicked;
	private boolean turnsDefined;
	
	public ServerConnection(Socket socket, Server server) {
		super("ServerConnectionThread"+server.getPort());
		this.socket = socket;
		this.server = server;
		this.shouldRun = true;
		this.buttonTurnClicked = false;
		this.turnsDefined = false;
	}
	


	public void run() {
		try {
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			
			String state = "WAITING";
			while (isShouldRun()) {
				
				switch (state) {
				
				//waiting for the 2 players to enter
				case "WAITING":
					state = (nConections()==2) ? "START" : "WAITING";
					break;
					
				//give turns to the players
				case "START":
					boolean player0 = Math.random() < 0.5;
					setTurns(player0);
					//ServerConnection conect0 = this.server.getConnections().get(0);
					//ServerConnection conect1 = this.server.getConnections().get(1);
					state = "READING";
					break;
				
				case "READING":
					break;
				
				
				//esperar que jogador termine o turno
				/*case "WAITING":
					state = (isButtonTurnClicked()) ? "TURN" : "WAITING";
					break;
					
				case "TURN":
					System.out.println("BUTTON WAS CLICKED");
					setButtonTurnClicked(false);
					state = "WAITING";
					break;*/
				}
				
				
				/*while (din.available()==0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String textIn = din.readUTF();
				sendStringToAllClients(textIn);
				*/
			}
			
			din.close();
			dout.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void setTurns(boolean first) {
		Client one = getFirstPlayer();
		Client two = getSecondPlayer();
		
		one.getCc().setPlayerTurn(first);
		two.getCc().setPlayerTurn(!first);
		
		one.getCc().setTurnsDefined(true);
		two.getCc().setTurnsDefined(true);
	}
	
	private synchronized int nConections() {
		int count = 0;
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connections;
		
		for (Entry<Client, Integer> entry : map.entrySet()) {
		    //Integer list = entry.getValue();
		    // Do things with the list
			if (entry.getValue()==this.getServer().getPort()) {
				count++;
			}
		}
		
		return count;
	}
	private Client getFirstPlayer() {
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connections;
		
		for (Entry<Client, Integer> entry : map.entrySet()) {
			if (entry.getValue()==this.getServer().getPort()) {
				return entry.getKey();
			}
		}
		return null;
	}
	private Client getSecondPlayer() {
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connections;
		Client[] clients = new Client[2];
		int count = 0;
		
		for (Entry<Client, Integer> entry : map.entrySet()) {
			if (entry.getValue()==this.getServer().getPort()) {
				clients[count] = entry.getKey();
				count++;
			}
		}
		return clients[1];
	}
	
	
	
	
	
	
	
	/*
	public void sendStringToClient(String text) {
		try {
			dout.writeUTF(text);
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendStringToAllClients(String text) {
		for(int i = 0; i<server.connections.size(); i++) {
			ServerConnection sc = server.connections.get(i);
			sc.sendStringToClient(text);
		}
	}*/
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public DataInputStream getDin() {
		return din;
	}

	public void setDin(DataInputStream din) {
		this.din = din;
	}

	public DataOutputStream getDout() {
		return dout;
	}

	public void setDout(DataOutputStream dout) {
		this.dout = dout;
	}

	public boolean isShouldRun() {
		return shouldRun;
	}

	public void setShouldRun(boolean shouldRun) {
		this.shouldRun = shouldRun;
	}


	public boolean isButtonTurnClicked() {
		return buttonTurnClicked;
	}


	public void setButtonTurnClicked(boolean buttonTurnClicked) {
		this.buttonTurnClicked = buttonTurnClicked;
	}



}
