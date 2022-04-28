package mechanics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Connector;

public class Character {
	
	private int id;
	private int hp;
	private boolean isStunned;
	private boolean isInvul;
	private int permanentDamageIncrease;
	private int dd;
	private int dr;
	private Ability ability1;
	private Ability ability2;
	private Ability ability3;
	private Ability ability4;
	
	public Character(int id) {
		this.id = id;
		this.hp = 100;
		this.isStunned = false;
		this.isInvul = false;
		this.permanentDamageIncrease = 0;
		this.dr = 0;
		this.dd = 0;
		this.ability1 = new Ability( this.getCharacterAbilities(this.id)[0] );
		this.ability2 = new Ability( this.getCharacterAbilities(this.id)[1] );
		this.ability3 = new Ability( this.getCharacterAbilities(this.id)[2] );
		this.ability4 = new Ability( this.getCharacterAbilities(this.id)[3] );
	}
	
	public void applyAbility1(Character c) {
		//TODO ver o que habilidade faz
		//afetar c
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isStunned() {
		return isStunned;
	}

	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}

	public boolean isInvul() {
		return isInvul;
	}

	public void setInvul(boolean isInvul) {
		this.isInvul = isInvul;
	}

	public int getPermanentDamageIncrease() {
		return permanentDamageIncrease;
	}

	public void setPermanentDamageIncrease(int permanentDamageIncrease) {
		this.permanentDamageIncrease = permanentDamageIncrease;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}
	public Ability getAbility1() {
		return ability1;
	}


	public Ability getAbility2() {
		return ability2;
	}


	public Ability getAbility3() {
		return ability3;
	}


	public Ability getAbility4() {
		return ability4;
	}
	private int[] getCharacterAbilities(int id) {
		PreparedStatement stmt = null;
		int[] abilitiesID = new int[4];
		
		try {
			Class.forName(Connector.drv);

			Connection conn = Connector.getConnection();

			String query = String.format("select abilityID from ABILITY where characterID="+id); 
			
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			
			while (rs.next()) {
				
				abilitiesID[count] = Integer.parseInt( rs.getString("abilityID") );
				count++;
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			for (int i = 0; i < 10; i++)
				System.out.println("Erro a encontrar o JDBC");
			e.printStackTrace();
		}
		return abilitiesID;
	}

}
