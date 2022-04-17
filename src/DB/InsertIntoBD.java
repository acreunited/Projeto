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
	
//	abilityID int not null,
//    energyID int not null,
//    quantity int not null,
	public boolean createAbilityNature(int abilityID, int taijutsu, int heart, int energy, int spirit, int random) throws SQLException {
		

		createAbilityE1(abilityID, taijutsu);
		createAbilityE2(abilityID, heart);
		createAbilityE3(abilityID, energy);
		createAbilityE4(abilityID, spirit);
		createAbilityE5(abilityID, random);
		
		return true;
	}
	public boolean createAbilityE1(int abilityID, int taijutsu) throws SQLException {
		String sql = "INSERT INTO ABILITY_E1 values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, 0);
		pstmt.setInt(3, taijutsu);
		pstmt.executeUpdate();
		return true;
	}
	public boolean createAbilityE2(int abilityID, int heart) throws SQLException {
		String sql = "INSERT INTO ABILITY_E2 values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, 1);
		pstmt.setInt(3, heart);
		pstmt.executeUpdate();
		return true;
	}
	public boolean createAbilityE3(int abilityID, int energy) throws SQLException {
		String sql = "INSERT INTO ABILITY_E3 values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, 2);
		pstmt.setInt(3, energy);
		pstmt.executeUpdate();
		return true;
	}
	public boolean createAbilityE4(int abilityID, int spirit) throws SQLException {
		String sql = "INSERT INTO ABILITY_E4 values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, 3);
		pstmt.setInt(3, spirit);
		pstmt.executeUpdate();
		return true;
	}
	public boolean createAbilityE5(int abilityID, int random) throws SQLException {
		String sql = "INSERT INTO ABILITY_E5 values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, 4);
		pstmt.setInt(3, random);
		pstmt.executeUpdate();
		return true;
	}
	
	public int createAbility(int cooldown, int characterID) throws SQLException {
		
		String getID = "select * from ABILITY order by abilityID DESC LIMIT 1;";
		PreparedStatement stmt_id = conn.prepareStatement(getID);
		int id = 0;
		ResultSet rs_id = stmt_id.executeQuery();
		
		if (rs_id.next()) {
			System.out.println("LastID Ability: " + rs_id.getString("abilityID"));
			id = Integer.parseInt(rs_id.getString("abilityID"))+1;
			System.out.println("newID "+id);
		}
	
		
		String sql = "INSERT INTO ABILITY values (?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, id);
		pstmt.setInt(2, cooldown);
		pstmt.setInt(3, characterID);
		
		pstmt.executeUpdate();
		
		return id;
	}
	
	
	/*
	 * abilityID int not null, 
    themeID int not null,
    nome varchar(32) not null,
    avatar blob,
    descricao varchar(500) not null,
	 * 
	 * */
	public boolean createAbilityTheme(int abilityID, int themeID, String nome, InputStream avatar, String descricao) throws SQLException {

		String sql = "INSERT INTO THEME_ABILITY values (?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, abilityID);
		pstmt.setInt(2, themeID);
		pstmt.setString(3, nome);
		pstmt.setBinaryStream(4, avatar);
		pstmt.setString(5, descricao);
		pstmt.executeUpdate();

		return true;
		
	}
	
	
	/*
	 * missionID int not null,
    nome varchar(32) not null,
    descricao varchar(5000),
    image mediumblob,
    minimumLevel int,    
    characterID int not null,
	 * 
	 * */	
	public boolean createMission(String nome, String descricao, InputStream image, int lvl, int characterID) throws SQLException {
		
		String getID = "select * from MISSION order by missionID DESC LIMIT 1;";
		PreparedStatement stmt_id = conn.prepareStatement(getID);
		int id = 0;
		ResultSet rs_id = stmt_id.executeQuery();
		
		if (rs_id.next()) {
			System.out.println("LastID Mission: " + rs_id.getString("missionID"));
			id = Integer.parseInt(rs_id.getString("missionID"))+1;
			System.out.println("newID Mission "+id);
		}
		
		String sql = "INSERT INTO MISSION values (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, id);
		pstmt.setString(2, nome);
		pstmt.setString(3, descricao);
		pstmt.setBinaryStream(4, image);
		pstmt.setInt(5, lvl);
		pstmt.setInt(6, characterID);
		pstmt.executeUpdate();

		return true;
	}

}
