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

import legacy.Client;
import main.Connector;

@WebServlet("/InGame")
public class InGame extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	private static Hashtable<String,  Semaphore> games = new Hashtable<String,  Semaphore>();
	private static String uuid = "uuidJogoTODO";
	private static boolean oppSurrender = false;
	// proteger esta estrutura de dados jogos com um monitor/semaforo
	
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
			createSemaphore(session);
		}
		else if (metodo.equalsIgnoreCase("lock")) {
			lock(session, 3);
		}
		else if (metodo.equalsIgnoreCase("unlock")) {
			unlock(session);
		}
		else if (metodo.equalsIgnoreCase("loser")) {
			session.setAttribute("result", "loser");
			oppSurrender = true;
			unlock(session);
		}
		
		
		response.sendRedirect("battle.jsp");
		
		//RequestDispatcher reqDispatcher = request.getRequestDispatcher("battle.jsp");
       // reqDispatcher.forward(request, response);
		//return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private  void createSemaphore(HttpSession session) {
		 Enumeration<String> e = games.keys();
		 boolean exists = false;
		 if (e.hasMoreElements()) {
			 exists = true;
	     }
		
		 if (!exists) {
			 games.put(uuid, new Semaphore(1));
			 session.setAttribute("turn", true);
			 lock(session, 1);
			 
		 }
		 else {
			 session.setAttribute("turn", false);
		 }

	}
	
	private void lock(HttpSession session, int natures) {
		try {
			games.get(uuid).acquire();
			session.setAttribute("turn", true);
			generateRandomNatures(session, natures);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void unlock(HttpSession session) {
		games.get(uuid).release();
		session.setAttribute("turn", false);
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


	
	
	
}
