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
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		if (oppSurrender) {
			session.setAttribute("result", "winner");
		}
		else {
			session.setAttribute("result", "");
		}
		
		String metodo = request.getParameter("metodo");
		
		if (metodo.equalsIgnoreCase("create")) {
			gameInfo = new GamesInfo((int)session.getAttribute("userID"), (int)session.getAttribute("opp_id"));
			session.setAttribute("turn", gameInfo.isturn());
		}
		else if (metodo.equalsIgnoreCase("lock")) {
			gameInfo.lock((String) session.getAttribute("uuid"));
			session.setAttribute("turn", true);
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
		
		System.out.println( (boolean) session.getAttribute("turn") );
		//response.sendRedirect("battle.jsp");
		
		//RequestDispatcher reqDispatcher = request.getRequestDispatcher("battle.jsp");
       // reqDispatcher.forward(request, response);
		//return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
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
