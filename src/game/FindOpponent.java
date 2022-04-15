package game;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		System.out.println(battle);
		/*String id = request.getParameter("id");
		String char1 = request.getParameter("char1");
		String char2 = request.getParameter("char2");
		String char3 = request.getParameter("char3");
		
		Queue.queueQuick.add(new Team(id, char1, char2, char3));

		session.setAttribute("this_id", id);
		//session.setAttribute(name, value);
		
		System.out.println("Player "+id+" searching");
		
		for (Team t : Queue.queueQuick) {
			System.out.println(
					"Player "+t.getId()+" "+ "Char1 "+t.getChar1()+" "+ "Char2 "+t.getChar2()+" "+ "Char3 "+t.getChar3());
		}*/
		
		
		
		/*for (String s : Queue.queueQuick) {
			if(!s.equals(id)) {
				session.setAttribute("opp_id", s);
				response.sendRedirect("battle.jsp");
				break;
			}
			//System.out.println("Queue: "+s);
		}*/
		//response.sendRedirect("selection.jsp");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}


}
