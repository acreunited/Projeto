package resources;

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
import users.UserInfo;

@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	
	public ViewProfile() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		System.out.println("user " +username);

		try {
			Connection con = Connector.getConnection();
			
			PreparedStatement rank = con.prepareStatement("select * from USERS order by xp DESC;");
			ResultSet rs_rank = rank.executeQuery();
			int count = 0;
			while (rs_rank.next()) {
				count++;
				if (rs_rank.getString("username").equalsIgnoreCase(username)) {
					break;
				}
			}
			String profile_rank = ""+count;
			
			PreparedStatement user = con.prepareStatement(
					"select * from USERS where USERS.username=?");
			user.setString(1, username);
			ResultSet rs = user.executeQuery();
		
			if (rs.next()) {
				
				String id = rs.getString("userID");
				String xp = rs.getString("xp");
				String wins = rs.getString("nWins");
				String losses = rs.getString("nLosses");
				String streak = rs.getString("streak");
				String date = rs.getString("registerDate");
				String hLevel = rs.getString("highestLevel");
				String hStreak = rs.getString("highestStreak");
				String level = UserInfo.getLevel(xp);
				
				session.setAttribute("profile_username", username);
				session.setAttribute("profile_id", id);
				session.setAttribute("profile_xp", xp);
				session.setAttribute("profile_wins", wins);
				session.setAttribute("profile_losses", losses);
				session.setAttribute("profile_streak", streak);
				session.setAttribute("profile_registerDate", date);
				session.setAttribute("profile_hLevel", hLevel);
				session.setAttribute("profile_hStreak", hStreak);
				session.setAttribute("profile_level", level);
				session.setAttribute("profile_rank", profile_rank);
				
				response.sendRedirect("profile.jsp");
			} else {
				System.out.println("Player "+ username + "Not Found");
			}
			rs.close();
			user.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
