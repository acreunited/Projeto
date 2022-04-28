package users;

import java.io.IOException;
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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");
	

		PreparedStatement stmt = null;
		response.getWriter().write("USER " + username + " PASS " + password);

		session.setAttribute("loggedIn", false);
		session.setAttribute("tipoUser", "guest");
		
		try {
			Class.forName(Connector.drv);

			Connection conn = Connector.getConnection();

			String query = String.format("select pass, userID FROM USERS where username='%s'", username); 
			
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				if (password.equals(rs.getString("pass"))) {
					session.setAttribute("username", username);
					session.setAttribute("loggedIn", true);

					int id = rs.getInt("userID");
					session.setAttribute("userID", id);

					query = String.format("select * from ADMINISTRATOR where administratorID='%d'", id);
					rs = conn.prepareStatement(query).executeQuery();
					if (rs.next()) { // id único, só pode ter 1
						session.setAttribute("tipoUser", "administrador");
						response.sendRedirect("index.jsp");

					} else {
						session.setAttribute("tipoUser", "player");
						response.sendRedirect("index.jsp");
					}

				} else {
					session.setAttribute("username", null);
					
					session.setAttribute("tipoUser", "guest");
					response.sendRedirect("login.jsp");
				}
				
				
			} else {
				for (int i = 0; i < 5; i++) {
					System.out.printf("USERNAME %s nao foi encontrada\n", username);
				}
				session.setAttribute("tipoUser", "guest");
				response.sendRedirect("login.jsp");
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			for (int i = 0; i < 10; i++)
				System.out.println("Erro a encontrar o JDBC");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}