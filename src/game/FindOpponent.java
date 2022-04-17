package game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String battle = request.getParameter("matchType");
		String userID = request.getParameter("userID");
		String char1 = request.getParameter("char1");
		String char2 = request.getParameter("char2");
		String char3 = request.getParameter("char3");
		
		session.setAttribute("this_id", userID);
		session.setAttribute("this_char1", char1);
		session.setAttribute("this_char2", char2);
		session.setAttribute("this_char3", char3);
		
		//Queue.queueQuick.add(new Team(userID, char1, char2, char3));
		Queue playerTeam = new Queue(new Player(userID), new Team(char1, char2, char3));
		//Matchmaking.matchQuick.add( new Queue(new Player(userID), new Team(char1, char2, char3)) );
		
		boolean cancel = false;
		String state = "ENTERING";
		
		while (!cancel) {
			
			switch (state) {
			
			case "ENTERING":
				tryAdd(Matchmaking.matchQuick, playerTeam);
				
				state="QUEUE";
				break;
				
			case "QUEUE":
				//fica a procura de oponentes
				//TODO CANCELAR PROCURA QUANDO CLICA BOTAO
				Queue opp = null;
				while (!oppFound(Matchmaking.matchQuick, playerTeam)) {
					
				}
				removeFromQueue(Matchmaking.matchQuick, Matchmaking.matchQuickRemove);
				
				state = "FOUND";
				break;
				
			case "FOUND":
				//remover jogador e oponente array
				//guardar oponente como inimigo
				//redirect
				
				Queue player_match = null;
				Queue opp_match = null;
				System.out.println(Matchmaking.matchFoundQuick.size());
				for (Map.Entry me : Matchmaking.matchFoundQuick.entrySet()) {
					//fazer set session inimigo
					Queue key = (Queue) me.getKey();
					String playerID_key = key.getPlayer().getPlayerID();
					
					if (playerID_key.equals(userID)) {
						player_match = (Queue) me.getKey();
						opp_match = (Queue) me.getValue();
						
						session.setAttribute("opp_id", opp_match.getPlayer().getPlayerID());
						session.setAttribute("opp_char1", opp_match.getTeam().getChar1());
						session.setAttribute("opp_char2", opp_match.getTeam().getChar2());
						session.setAttribute("opp_char3", opp_match.getTeam().getChar3());
						
						Matchmaking.matchFoundQuick.remove(player_match);
						Matchmaking.matchFoundQuick.remove(opp_match);
						
						response.sendRedirect("battle.jsp");
						break;
					}

					
		          }
			
				
				System.out.println("SIZE: "+Matchmaking.matchFoundQuick.size());

				
				cancel=true;
				break;
				
			}
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void tryAdd(CopyOnWriteArrayList<Queue> arr, Queue queue) {
		
		for (Queue q : arr) {
			if (q.getPlayer().getPlayerID().equalsIgnoreCase(queue.getPlayer().getPlayerID())) {
				arr.remove(q);
			}
		}
		arr.add(queue);		
	}
	
	private boolean oppFound(CopyOnWriteArrayList<Queue> arr, Queue player) {
		
		if (arr.size()<=1) {
			return false;
		}
		
		for (Queue q : arr) {
			if (!q.getPlayer().getPlayerID().equalsIgnoreCase(player.getPlayer().getPlayerID())) {
				Matchmaking.matchFoundQuick.put(player, q);
				
				Matchmaking.matchQuickRemove.add(q);
				Matchmaking.matchQuickRemove.add(player);

				return true;
			}
		}
		return false;
	}
	private void removeFromQueue(CopyOnWriteArrayList<Queue> queue, CopyOnWriteArrayList<Queue> remove) {

		for (Queue q : remove) {
			queue.remove(q);
		}
	}
	
	/*private void tryRemove(ArrayList<Queue> arr, Queue entry) {
		if (arr.size()<=1) {
			return;
		}
		
		for (Queue q : arr) {
			if (q.equals(entry)) {
				arr.remove(entry);
			}
		}
		
	}*/
	
	
}
