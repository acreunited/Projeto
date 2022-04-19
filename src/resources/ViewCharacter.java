package resources;

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

import main.Connector;

@WebServlet("/ViewCharacter")
public class ViewCharacter extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;
	
	public ViewCharacter() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//id = missionID
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Connection con = Connector.getConnection();
			PreparedStatement bleach = con.prepareStatement(
					"select * from THEME_CHARACTER where themeID=1 and characterID=?");
			bleach.setInt(1, id);
			ResultSet rs = bleach.executeQuery();

			if (rs.next()) {
				response.setContentType("image/png");
				OutputStream o = response.getOutputStream();
				Blob img = rs.getBlob("avatar");
				byte[] sImage = img.getBytes(1, (int) img.length());
				o.write(sImage, 0, sImage.length);
				o.flush();
				o.close();
			} else {
				System.out.println("Character "+ id + " not found");
			}
			rs.close();
			bleach.close();
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
