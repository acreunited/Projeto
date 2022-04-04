package DB;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Connector;

public class InsertIntoBD {
	private final Connection conn;

	public InsertIntoBD() throws SQLException {
		conn = Connector.getConnection();
	}

	/**
	 * Este metodo cria um novo personagem,
	 * 
	 *
	 * @return retorna ID personagem
	 * @throws SQLException
	 */
	public int createCharacter() throws SQLException {
		
		String getID = "select * from CHARACTERS order by characterID DESC LIMIT 1;";
		PreparedStatement stmt_id = conn.prepareStatement(getID);
		int id = 0;
		ResultSet rs_id = stmt_id.executeQuery();
		
		if (rs_id.next()) {
			System.out.println("LastID Character: " + rs_id.getString("characterID"));
			id = Integer.parseInt(rs_id.getString("characterID"))+1;
			System.out.println("newID "+id);
		}
	
		String sql = "INSERT into CHARACTERS (characterID)"
				+ "values (?);";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

	
		return id;
	}
	
	public boolean createBleach(int id) throws SQLException {
		String sql = "INSERT into BLEACH (bleachID, category)"
				+ "values (?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, id);
		pstmt.setString(2, "human");
		pstmt.executeUpdate();

		return true;
	}
	/*
	 * 
	 * characterID int not null, 
    themeID int not null,
    nome varchar(16) not null,
    avatar blob,
    descricao varchar(500) not null,
	 * 
	 * */
	public boolean createThemeCharacter(int characterID, int themeID, String nome, InputStream avatar, String descricao) throws SQLException {
		
		String sql = "INSERT INTO THEME_CHARACTER values (?, ?, ?, ?, ?);";
				
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, characterID);
		pstmt.setInt(2, themeID);
		pstmt.setString(3, nome);
		pstmt.setBinaryStream(4, avatar);
		pstmt.setString(5, descricao);
		pstmt.executeUpdate();

		return true;
	}

}
