package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	
	//private Socket s;
	//private DataInputStream din;
	//private DataOutputStream dout;
	//private boolean shouldRun;
	private ServerSocket ss;
	public ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
	private int port;

	public Server(int port) {
		this.port = port;
		
			try {
				ss = new ServerSocket(port);
				//while para correr mesmo quando recebe conexão
				//while (shouldRun) {
				Socket s = ss.accept();
				ServerConnection sc = new ServerConnection(s, this);
				sc.start();
				connections.add(sc);
				//}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	
	}
	
	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	public ArrayList<ServerConnection> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<ServerConnection> connections) {
		this.connections = connections;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
