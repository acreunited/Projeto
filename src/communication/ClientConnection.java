package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {
	
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dout;
	private boolean shouldRun;
	private Client client;
	private boolean buttonTurnClicked;
	private boolean playerTurn;
	private boolean turnsDefined;
	private boolean needsRefresh;
	
	
	public ClientConnection(Socket socket, Client client) {
		super("ClientConnectionThread"+client.getPort());
		
		this.socket = socket; 
		this.client = client;
		this.shouldRun = true;
		this.buttonTurnClicked = false;
		this.turnsDefined = false;
		this.needsRefresh = false;
	}
	public void sendStringToServer(String text) {
		try {
			dout.writeUTF(text);
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
			closeStreams();
		}
	}
	
	public void run() {
		try {
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			
			String state = "WAITING";
			while (isShouldRun()) {
				
				switch (state) {
				
				case "WAITING":
					state = (isPlayerTurn()) ? "MYTURN" : "OPPTURN";
				
					break;
					
				case "MYTURN":
					
					state = (isPlayerTurn()) ? "MYTURN" : "OPPTURN";
					break;
					
				case "OPPTURN":
				
					state = (isPlayerTurn()) ? "MYTURN" : "OPPTURN";
					break;
				}
				
				
				/*try {
					while (din.available()==0) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
							closeStreams();
						}
					}
					String reply = din.readUTF();
					System.out.println(reply);
				} catch (IOException e) {
					e.printStackTrace();
					closeStreams();
				}*/
			}
			System.out.println("Turno foi passado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	public void closeStreams() {
		try {
			din.close();
			dout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public boolean isButtonTurnClicked() {
		return buttonTurnClicked;
	}
	public void setButtonTurnClicked(boolean buttonTurnClicked) {
		this.buttonTurnClicked = buttonTurnClicked;
	}
	public boolean isPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	public boolean isTurnsDefined() {
		return turnsDefined;
	}
	public void setTurnsDefined(boolean turnsDefined) {
		this.turnsDefined = turnsDefined;
	}
	public boolean isNeedsRefresh() {
		return needsRefresh;
	}
	public void setNeedsRefresh(boolean needsRefresh) {
		this.needsRefresh = needsRefresh;
	}


}
