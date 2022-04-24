package game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import communication.Client;
import communication.Server;
import communication.ServerConnection;
import main.Connector;

@WebServlet("/FindOpponent")
public class FindOpponent extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	
	public FindOpponent() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		int userID = (int) session.getAttribute("userID");
		String char1 = request.getParameter("char1");
		String char2 = request.getParameter("char2");
		String char3 = request.getParameter("char3");
		
		session.setAttribute("this_id", userID);
		session.setAttribute("this_char1", char1);
		session.setAttribute("this_char2", char2);
		session.setAttribute("this_char3", char3);
		
		Queue playerTeam = new Queue(userID, new Team(char1, char2, char3));
		
		System.out.println(Matchmaking.matchQuick.size());
		
		boolean searching = true;
		String state = "PORT";
		
		while (searching) {
			
			switch (state) {
			
			case "END":
				searching = false;
				break;
				
			case "PORT":
				state = (isPortInUse(3333)) ? "START" : "SERVER";
				break;
				
			case "SERVER":
				
				if(!isPortInUse(3333)) {
					Server server = new Server(3333);
					Matchmaking.allServers.add(server);
				}
				state = "START";
					
				break;
			
			//enter Queue
			case "START":
				Matchmaking.matchQuick.add(playerTeam);
				state = "WAITING";
				break;
				
			case "WAITING":
				state = (Matchmaking.matchQuick.size()>1) ? "PAIRING" : "WAITING";
				break;
				
			case "PAIRING":
				System.out.println("STATE: "+state);
				for (Queue q : Matchmaking.matchQuick) {
					if (q.getPlayer()!=userID) {
						session.setAttribute("opp_id", q.getPlayer());
						session.setAttribute("opp_char1", q.getTeam().getChar1());
						session.setAttribute("opp_char2", q.getTeam().getChar2());
						session.setAttribute("opp_char3", q.getTeam().getChar3());
						
						state = "CLIENT";
					}
				}
				break;
				
			case "CLIENT":
				System.out.println("STATE: "+state);
				Client cliente = new Client(3333, userID);
				session.setAttribute("thread", cliente);

				state = "END";
				break;
			}
		}

		
		//TODO melhorar isto. Não pode ficar assim
		Matchmaking.matchQuick.clear();
		Matchmaking.allClients.clear();
		
		response.sendRedirect("battle.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	


	
	private boolean isPortInUse(int portNr) {

	    try {
	        // Socket try to open a REMOTE port
	        (new Socket("localhost", portNr)).close();
	        // remote port can be opened, this is a listening port on remote machine
	        // this port is in use on the remote machine !
	        return true;
	    } catch(Exception e) {
	        // remote port is closed, nothing is running on
	        return false;
	    }
	}
	
	
	
}
