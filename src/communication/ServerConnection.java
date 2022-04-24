package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
				
				//esperar que jogador termine o turno
				case "WAITING":
					state = (isButtonTurnClicked()) ? "TURN" : "WAITING";
					break;
					
				case "TURN":
					System.out.println("BUTTON WAS CLICKED");
					setButtonTurnClicked(false);
					state = "WAITING";
					break;
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
	}
	
	
	
	
	
	
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
