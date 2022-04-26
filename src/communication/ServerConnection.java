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
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import game.Matchmaking;

public class ServerConnection extends Thread {
	
	private Socket socket;
	private Server server;
	private DataInputStream din;
	private DataOutputStream dout;
	private boolean shouldRun;
	private boolean buttonTurnClicked;
	
	
	public ServerConnection(Socket socket, Server server) {
		super("ServerConnectionThread"+server.getPort());
		this.socket = socket;
		this.server = server;
		this.shouldRun = true;
		this.buttonTurnClicked = false;
		
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
					setFirstTurns(player0, !player0);
					
					//state = (!player0) ? "PLAYER0" : "PLAYER1";
					state = "IDLE";
					break;
					
				case "IDLE":
					state = (this.isButtonTurnClicked()) ? "TURNS" : "IDLE";
					break;
					
				case "TURNS":
					Client c1 = this.getFirstPlayer();
					boolean turn1 = c1.getCc().isPlayerTurn();
					Client c2 = this.getSecondPlayer();
					boolean turn2 = c2.getCc().isPlayerTurn();
					
					this.setTurns(!turn1, !turn2);
	
					
					this.setButtonTurnClicked(false);
					state = "IDLE";
					break;
				
				/*case "PLAYER0":
					Client p0 = this.getFirstPlayer();
					
					try {
						System.out.println("SERVER P0: "+p0.getCc().isButtonTurnClicked());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if( p0.getCc().isButtonTurnClicked() ) {
						p0.getCc().setButtonTurnClicked(false);
						setTurns(false);
						state = "PLAYER1";
					}
					else {
						state = "PLAYER0";
					}
					
					break;*/
					
				/*case "PLAYER1":
					Client p1 = this.getFirstPlayer();
					try {
						System.out.println("SERVER P1: "+p1.getCc().isButtonTurnClicked());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if( p1.getCc().isButtonTurnClicked() ) {
						p1.getCc().setButtonTurnClicked(false);
						setTurns(false);
						state = "PLAYER0";
					}
					else {
						state = "PLAYER1";
					}
					break;*/
				
				
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
	
	private void generateRandomNatures(Client c, int number) {
		for (int i = 0; i < number; i++) {
			Random r = new Random();
			int randomInt = r.nextInt(100) + 1;
			if (randomInt <=25) {
				c.getCc().addTaijutsu();
			}
			else if (randomInt <= 50) {
				c.getCc().addEnergy();
			}
			else if (randomInt <=75) {
				c.getCc().addSpirit();
			}
			else {
				c.getCc().addHeart();
			}
			c.getCc().setRandom();
		}
		
	}

	private void setFirstTurns(boolean first, boolean second) {
		Client one = getFirstPlayer();
		Client two = getSecondPlayer();
		
		one.getCc().setPlayerTurn(first);
		two.getCc().setPlayerTurn(second);
		
		if (first) {
			this.generateRandomNatures(one, 1);
			//this.generateRandomNatures(two, 3);
		}
		else {
			//this.generateRandomNatures(one, 3);
			this.generateRandomNatures(two, 1);
		}
		
		
		one.getCc().setTurnsDefined(true);
		two.getCc().setTurnsDefined(true);
	}

	private void setTurns(boolean first, boolean second) {
		Client one = getFirstPlayer();
		Client two = getSecondPlayer();
		
		one.getCc().setPlayerTurn(first);
		two.getCc().setPlayerTurn(second);
		
		if (first) {
			this.generateRandomNatures(one, 3);
			//this.generateRandomNatures(two, 3);
		}
		else {
			//this.generateRandomNatures(one, 3);
			this.generateRandomNatures(two, 3);
		}
		
		one.getCc().setNeedsRefresh(true);
		two.getCc().setNeedsRefresh(true);
		
		
	}
	
	private synchronized int nConections() {
		int count = 0;
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connectionsClient;
		
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
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connectionsClient;
		
		for (Entry<Client, Integer> entry : map.entrySet()) {
			if (entry.getValue()==this.getServer().getPort()) {
				return entry.getKey();
			}
		}
		return null;
	}
	private Client getSecondPlayer() {
		ConcurrentHashMap<Client, Integer> map = Matchmaking.connectionsClient;
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
