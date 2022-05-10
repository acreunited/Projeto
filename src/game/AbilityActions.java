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

import mechanics.Ability;
import mechanics.Character;


@WebServlet("/AbilityActions")
public class AbilityActions extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;

	public GamesInfo gameInfo;
	
	public AbilityActions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//setting the content type  
		PrintWriter pw = response.getWriter(); 

		HttpSession session = request.getSession();
		
		Character thisChar1 = (Character) session.getAttribute("this_char1_game");
		Character thisChar2 = (Character) session.getAttribute("this_char2_game");
		Character thisChar3 = (Character) session.getAttribute("this_char3_game");
		Character oppChar1 = (Character) session.getAttribute("opp_char1_game");
		Character oppChar2 = (Character) session.getAttribute("opp_char2_game");
		Character oppChar3 = (Character) session.getAttribute("opp_char3_game");

		int abilityPos = Integer.parseInt( request.getParameter("abilityPos") );
		int selfChar = Integer.parseInt( request.getParameter("selfChar") );
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("seeTarget")) {

			Ability a = getSelectedAbility(abilityPos, selfChar, thisChar1, thisChar2, thisChar3);
			
			
			if (a!=null) {
				pw.write(a.getTargetClick());
			}
			else {
				System.out.println("HABILIDADE NULL");
			}
		
		}
		
		
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	private Ability getSelectedAbility(int abilityPos, int selfChar, Character thisChar1,Character thisChar2,Character thisChar3) {
		Character c = null;
		Ability a = null;
		
		if (selfChar==0) {
			c = thisChar1;
		}
		else if (selfChar==1) {
			c = thisChar2;
		}
		else if (selfChar==2) {
			c = thisChar3;
		}
		
		if (abilityPos==0) {
			a = c.getAbility1();
		}
		else if (abilityPos==1) {
			a = c.getAbility2();
		}
		else if (abilityPos==2) {
			a = c.getAbility3();
		}
		else if (abilityPos==3) {
			a = c.getAbility4();
		}
		return a;
	}
	
	
	
}
