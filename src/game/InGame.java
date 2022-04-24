package game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import communication.Client;


@WebServlet("/InGame")
public class InGame extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	
	public InGame() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		int userID = (int) session.getAttribute("userID");

		Client este = null;
		for (Client c : Matchmaking.allClients) {
			if (c.getId()==userID) {
				este = c;
			}
		}
		if (este!=null) {
			este.getCc().setButtonTurnClicked(true);
		}
		
		response.sendRedirect("battle.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	
	
}
