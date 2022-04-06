package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Connector;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String confirm_password = request.getParameter("CONFIRM_PASSWORD");
		String email = request.getParameter("EMAIL");
		int id = 0;

		try {
			Class.forName(Connector.drv);
			Connection conn = Connector.getConnection();
			System.out.printf("%s %s %s %s\n", username, password, confirm_password, email);
			
			String names = "select username from USERS;";
			PreparedStatement stmt_names = conn.prepareStatement(names);
			ResultSet rs_names = stmt_names.executeQuery();
			
			//boolean exists = false;
			while (rs_names.next()) {
				if (rs_names.getString("username").equalsIgnoreCase(username)){
					//exists=true;
					//sendFailMessage(request, response, "Username Already Exists");
					session.setAttribute("usernameExists", "true");
					response.sendRedirect("register.jsp");
					return;
				}
			}
			session.setAttribute("usernameExists", "false");
			rs_names.close();
			//if (exists)
			
			String lastID_query = "select userID from USERS order by userID DESC LIMIT 1;";
			PreparedStatement stmt_id = conn.prepareStatement(lastID_query);
			ResultSet rs_id = stmt_id.executeQuery();
			
			if (rs_id.next()) {
				System.out.println(rs_id.getString("userID"));
				id = Integer.parseInt(rs_id.getString("userID"))+1;
			}
			
			rs_id.close();
		
			Date date = new Date();
			//java.util.Date date = new java.util.Date();
			//java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Blob avatar = null;
			
			String query = "insert into USERS ("
					+ "userID, username, pass, registerDate, xp, avatar, email, nWins, nLosses, "
					+ "selectionBackground, battleBackground, streak, highestStreak, highestLevel, estado) "
					+ "values ((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setDate(4, sqlDate);
			stmt.setInt(5, 0);
			stmt.setBlob(6, avatar);
			stmt.setString(7, email);
			stmt.setInt(8, 0);
			stmt.setInt(9, 0);
			stmt.setBlob(10, avatar);
			stmt.setBlob(11, avatar);
			stmt.setInt(12, 0);
			stmt.setInt(13, 0);
			stmt.setInt(14, 1);
			stmt.setDate(15, sqlDate);
			stmt.executeUpdate();
			
			
			String insertMember = "insert into MEMBERS ("
					+ "membersID) "
					+ "values ((?))";
			
			PreparedStatement stmt_member = conn.prepareStatement(insertMember);
			stmt_member.setInt(1,  id);
			stmt_member.executeUpdate();
			

			response.getWriter().append("Register Sucessfull" + username);
			
			response.sendRedirect("verificationEmail.jsp");
			
			stmt.close();
			stmt_member.close();
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

	private void sendFailMessage(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		if (message.equals("") || message == null) {
			message = "Error in Form";
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("error", message);

		//out.println("<div class='container modal'><h3>" + message + "</h3></div>");

	
		
		RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
		
		rd.include(request, response);
		return;
	}
}
