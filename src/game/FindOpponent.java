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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import legacy.MatchmakingLegacy;
import main.Connector;

@WebServlet("/FindOpponent")
public class FindOpponent extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	private static Hashtable<String,  Semaphore> games = new Hashtable<String,  Semaphore>();
	private static String uuid = "uuidJogoTODO";
	// proteger esta estrutura de dados jogos com um monitor/semaforo
	
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
		
		/*session.setAttribute("opp_id", q.getPlayer());
		session.setAttribute("opp_char1", q.getTeam().getChar1());
		session.setAttribute("opp_char2", q.getTeam().getChar2());
		session.setAttribute("opp_char3", q.getTeam().getChar3());*/
		
		Queue playerTeam = new Queue(userID, new Team(char1, char2, char3));
		
		boolean searching = true;
		String state = "START";
		
		while (searching) {
			
			switch (state) {
			
			case "END":
				
				searching = false;
				break;
			
			case "START":
				//criar Semaphore
				
				//createSemaphore(session);
				addQuick(playerTeam);
				state = "QUEUE";
				break;
				
			case "QUEUE":
				
				state = (Matchmaking.matchQuick.size()<=1) ? "QUEUE" : "SEARCHING";
				break;
				
			case "SEARCHING":
				
				for (Queue q : Matchmaking.matchQuick) {
					if (q.getPlayer()!=userID) {
						session.setAttribute("opp_id", q.getPlayer());
						session.setAttribute("opp_char1", q.getTeam().getChar1());
						session.setAttribute("opp_char2", q.getTeam().getChar2());
						session.setAttribute("opp_char3", q.getTeam().getChar3());
						
						state = "END";
					}
				}
				break;
			}
		}
		//System.out.println("SERVLET: "+session.getAttribute("turn"));
		games.clear();
		Matchmaking.matchQuick.clear();
		response.sendRedirect("loadingBattle.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	
	private  void addQuick(Queue q) {
		Matchmaking.matchQuick.add(q);
	}
	


	
	
	
}
