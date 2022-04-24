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
	
	
	public ClientConnection(Socket socket, Client client) {
		this.socket = socket; 
		this.client = client;
		this.shouldRun = true;
		this.buttonTurnClicked = false;
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
					state = (isButtonTurnClicked()) ? "ENDTURN" : "WAITING";
					break;
					
				case "ENDTURN":
					System.out.println("PLAYER "+this.getClient().getId()+" ENDED HIS TURN");
					setButtonTurnClicked(false);
					state = "WAITING";
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
		System.out.println("mudar bptao");
		this.buttonTurnClicked = buttonTurnClicked;
	}


}
