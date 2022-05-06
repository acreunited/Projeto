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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mechanics.Character;


@WebServlet("/InGame")
public class InGame extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	//private static Hashtable<String,  Semaphore> games = new Hashtable<String,  Semaphore>();
	//private static String uuid = "uuidJogoTODO";
	private static boolean oppSurrender = false;
	// proteger esta estrutura de dados jogos com um monitor/semaforo
	public GamesInfo gameInfo;
	
	public InGame() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/plain");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//setting the content type  
		PrintWriter pw = response.getWriter(); 
		  

		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("userID");
		
		if (oppSurrender) {
			session.setAttribute("result", "winner");
		}
		else {
			session.setAttribute("result", "");
		}
		
		String metodo = request.getParameter("metodo");
		
		
		if (metodo.equalsIgnoreCase("create")) {
			
			try {
				GameUtils.semQuick.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(Map.Entry<Queue, Queue> entry : GameUtils.matchQuickFound.entrySet()) {
				Queue key = entry.getKey();
				Queue value = entry.getValue();
				
				if (key.getPlayer()==id) {
					createSetAttributes(session, key, value);
					break;
				}
				else if (value.getPlayer()==id) {
					createSetAttributes(session, value, key);
					break;
				}
			}
			GameUtils.semQuick.release();

			
		}
		else if (metodo.equalsIgnoreCase("lock")) {
	
			gameInfo.lock((String) session.getAttribute("uuid"));
			session.setAttribute("turn", true);
			generateRandomNatures(session, 3);
			updateNatureInGame(session, pw);
			
		}
		else if (metodo.equalsIgnoreCase("unlock")) {
			gameInfo.unlock((String) session.getAttribute("uuid"));
			session.setAttribute("turn", false);
		}
		else if (metodo.equalsIgnoreCase("loser")) {
			session.setAttribute("result", "loser");
			oppSurrender = true;
			gameInfo.unlock((String) session.getAttribute("uuid"));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void createSetAttributes(HttpSession session, Queue player, Queue opp) {
		session.setAttribute("opp_id", opp.getPlayer());
		session.setAttribute("this_id", player.getPlayer());
		session.setAttribute("this_char1", player.getTeam().getChar1());
		session.setAttribute("this_char2", player.getTeam().getChar2());
		session.setAttribute("this_char3", player.getTeam().getChar3());
		session.setAttribute("opp_char1", opp.getTeam().getChar1());
		session.setAttribute("opp_char2", opp.getTeam().getChar2());
		session.setAttribute("opp_char3", opp.getTeam().getChar3());
		session.setAttribute("taijutsu", 0);
		session.setAttribute("heart", 0);
		session.setAttribute("energy", 0);
		session.setAttribute("spirit", 0);
		session.setAttribute("random", 0);
		
		gameInfo = new GamesInfo(player.getPlayer(), opp.getPlayer());
		session.setAttribute("turn", gameInfo.isturn());
		session.setAttribute("uuid", gameInfo.getUuid());
		
	
		
		if ( gameInfo.isturn()) {
			generateRandomNatures(session, 1);
		}

	}


	private void generateRandomNatures(HttpSession session, int number) {
		for (int i = 0; i < number; i++) {
			
			Random r = new Random();
			int randomInt = r.nextInt(100) + 1;
			
			if (randomInt <=25) {
				session.setAttribute("taijutsu", (int) session.getAttribute("taijutsu")+1);
			}
			else if (randomInt <= 50) {
				session.setAttribute("heart", (int) session.getAttribute("heart")+1);
			}
			else if (randomInt <=75) {
				session.setAttribute("energy", (int) session.getAttribute("energy")+1);
			}
			else {
				session.setAttribute("spirit", (int) session.getAttribute("spirit")+1);
			}			
		}
		updateRandom(session);
		
	}
	
	private void updateRandom(HttpSession session) {
		int taijutsu = (int) session.getAttribute("taijutsu");
		int heart = (int) session.getAttribute("heart");
		int energy = (int) session.getAttribute("energy");
		int spirit = (int) session.getAttribute("spirit");
		
		session.setAttribute("random", taijutsu+heart+energy+spirit);
	}
	
	private void updateNatureInGame(HttpSession session, PrintWriter pw) {

		pw.println(" <strong class=\"energy0\">x"+session.getAttribute("taijutsu")+"</strong>");
		pw.println(" <strong class=\"energy1\">x"+session.getAttribute("heart")+"</strong>");
		pw.println(" <strong class=\"energy2\">x"+session.getAttribute("energy")+"</strong>");
		pw.println(" <strong class=\"energy3\">x"+session.getAttribute("spirit")+"</strong>");
		pw.println(" <strong class=\"energy4\">x"+session.getAttribute("random")+"</strong>");

		pw.close();
	}

	private void createCharacters(HttpSession session, HttpServletRequest req) {

		int this_char1 = (int) session.getAttribute("this_char1");
		int this_char2 = (int) session.getAttribute("this_char2");
		int this_char3 = (int) session.getAttribute("this_char3");
		int opp_char1 = (int) session.getAttribute("opp_char1");
		int opp_char2 = (int) session.getAttribute("opp_char2");
		int opp_char3 = (int) session.getAttribute("opp_char3");
		
		req.getServletContext().setAttribute("this_char1", new Character(this_char1));
		req.getServletContext().setAttribute("this_char2", new Character(this_char2));
		req.getServletContext().setAttribute("this_char3", new Character(this_char3));
		req.getServletContext().setAttribute("opp_char1", new Character(opp_char1));
		req.getServletContext().setAttribute("opp_char2", new Character(opp_char2));
		req.getServletContext().setAttribute("opp_char3", new Character(opp_char3));
		
	}
	
	
	
}
