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

	
	public FindOpponent() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		String metodo = request.getParameter("metodo");
		int id = (int) session.getAttribute("userID");
		
		
		String char1 = (String) request.getParameter("char1");
		String char2 = (String) request.getParameter("char2");
		String char3 = (String) request.getParameter("char3");

	

		if (metodo.equalsIgnoreCase("enterQueue")) {
			
			Queue queue = new Queue(id, new Team(char1, char2, char3));
			try {
				GameUtils.semQuick.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			GameUtils.matchQuick.add(queue);
			GameUtils.semQuick.release();
		}
		
		else if (metodo.equalsIgnoreCase("searchingOpp")) {
	
			String state = "START";
			boolean search = true;
			
			while(search) {
				switch (state) {
				
				case "END":
					//System.out.println("matchQuick "+GameUtils.matchQuick.size());
					//System.out.println("matchQuickFound "+GameUtils.matchQuickFound.size());
					search = false;
					break;
				
				case "START":
				
					state = (GameUtils.matchQuick.size()!=1) ? "PAIRING" : "START";
					break;
				
				case "PAIRING":
				
					try {
						GameUtils.semQuick.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Queue thisQueue = getThisQueue(id);
					
					//se for null é porque já tiraram = já tem oponente definido
					if (thisQueue!=null) {
						for (Queue q : GameUtils.matchQuick) {
							if (q.getPlayer()!=id) {
								GameUtils.matchQuickFound.put(thisQueue, q);
								GameUtils.matchQuick.remove(thisQueue);
								GameUtils.matchQuick.remove(q);
								break;
							}
						}
					}
					
					GameUtils.semQuick.release();
					//.remove(id);
					state = "END";
					break;
				}
			}
			
		}
		
		System.out.println("SIZE"+GameUtils.matchQuickFound.size());
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private Queue getThisQueue(int id) {
		for (Queue q : GameUtils.matchQuick) {
			if (q.getPlayer()==id) {
				return q;
			}
		}
		return null;
	}
	



	
	
	
}
