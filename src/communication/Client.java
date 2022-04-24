package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private ClientConnection cc;
	private int port;
	private int id;
	
	/*public static void main(String[] args) {
		new Client();
	}*/
	


	public Client(int port, int id) {
		this.port = port;
		this.id = id;
		
		try {
			Socket s = new Socket("localhost", this.port);
			cc = new ClientConnection(s, this);
			cc.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//listenforInput();
	}

	public void listenforInput() {
		Scanner console = new Scanner(System.in);
		
		while (true) {
			while(!console.hasNextLine()) {
				try {
					Thread.sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String input = console.nextLine();
			
			if (input.toLowerCase().equals("quit")) {
				break;
			}
			cc.sendStringToServer(input);
			
		}
		cc.closeStreams();
	}
	
	public ClientConnection getCc() {
		return cc;
	}

	public void setCc(ClientConnection cc) {
		this.cc = cc;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
