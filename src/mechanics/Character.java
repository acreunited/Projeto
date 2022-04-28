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
	private int stunnedDuration;
	private boolean isInvul;
	private int invulDuration;
	private int permanentDamageIncrease;
	private int temporaryDamageIncrease;
	private int dd;
	private int dr;
	private int natureGain;
	private int natureLoss;
	private Ability ability1;
	private Ability ability2;
	private Ability ability3;
	private Ability ability4;
	
	public Character(int id) {
		this.id = id;
		this.hp = 100;
		this.isStunned = false;
		this.stunnedDuration = 0;
		this.isInvul = false;
		this.invulDuration = 0;
		this.permanentDamageIncrease = 0;
		this.temporaryDamageIncrease = 0;
		this.dr = 0;
		this.dd = 0;
		this.natureGain = 0;
		this.natureLoss = 0;
		this.ability1 = new Ability( this.getCharacterAbilities(this.id)[0] );
		this.ability2 = new Ability( this.getCharacterAbilities(this.id)[1] );
		this.ability3 = new Ability( this.getCharacterAbilities(this.id)[2] );
		this.ability4 = new Ability( this.getCharacterAbilities(this.id)[3] );
	}
	
	public void applyAbility(Ability a, Character c) {
		//damage
		int damage = doDamage(a, c);
		if (damage>0) {
			c.setHp( c.getHp()-damage );
			
		}
		
		//stun
		c.setStunnedDuration( a.getStunDuration() );
		
		//become invulnerable
		c.setInvulDuration( a.getBecomeInvulDuration() );
		
		//TODO DD e DR - quando duração acaba, tirar DR ou DD mas só da habilidade
		
		//gain DD
		int dd = a.getGainDD()[0];
		if (dd>0) {
			c.setDd( c.getDd() + dd );
		}
		
		//gain DR
		int dr = a.getDR()[0];
		if (dr>0) {
			c.setDr( c.getDr() + dr );
		}
		
		//gainHP
		int hp = a.getGainHP()[0];
		if (hp > 0) {
			c.setHp(c.getHp() + a.getGainHP()[0]);
		}
		
		//gain nature
		int natureGain = a.getGainNature()[0];
		if (natureGain>0) {
			c.setNatureGain( c.getNatureGain() + natureGain );
		}
		
		//lose nature
		int natureLoss = a.getRemoveNature()[0];
		if (natureLoss > 0) {
			c.setNatureLoss( c.getNatureLoss() + natureLoss );
		}
			
	}

	
	private int doDamage(Ability a, Character c) {
		int damage = a.getDamage()[0];
		
		int permanent = this.getPermanentDamageIncrease();
		int temporary = this.getTemporaryDamageIncrease();
		int damageIncreaseUse = a.getDamageIncreasePerUse();
		if (damageIncreaseUse>0) {
			damageIncreaseUse = damageIncreaseUse*a.getnTimesUsed();
		}
		
		damage += permanent + temporary + damageIncreaseUse;
		
		int damagePerEnemyHPLost = this.getAbilityDamageEnemyHP(a, c);
		if (damagePerEnemyHPLost>0) {
			damage += damagePerEnemyHPLost;
		}
		int damagePerSelfHPLost = this.getAbilityDamageSelfHP(a);
		if (damagePerSelfHPLost>0) {
			damage += damagePerSelfHPLost;
		}

		return damage;
	}
	private int getAbilityDamageEnemyHP(Ability a, Character c) {
		int damagePerEnemyHPLost = 0;
		
		int enemyHP_damage = a.getDamagePerEnemyHPLost()[0];
		int enemyHP_hp = a.getDamagePerHPLost()[1];
		if (enemyHP_damage<0 || enemyHP_hp<0) {
			return -1;
		}
		
		int currentEnemyHP = c.getHp();
		int currentEnemyHP_lost = 100-currentEnemyHP;
		if (currentEnemyHP_lost>=enemyHP_hp) {
			do {
				damagePerEnemyHPLost += enemyHP_damage;
				currentEnemyHP_lost -= enemyHP_hp;
			}
			while (currentEnemyHP_lost-enemyHP_hp>0);
		}
		return damagePerEnemyHPLost;
	}
	private int getAbilityDamageSelfHP(Ability a) {
		int damagePerSelfHPLost = 0;
		
		int selfHP_damage = a.getDamagePerHPLost()[0];
		int selfHP_hp = a.getDamagePerHPLost()[1];
		if (selfHP_damage<0 || selfHP_hp<0) {
			return -1;
		}
		int currentHP_lost = 100-this.getHp();
		if(currentHP_lost >= selfHP_hp) {
			do {
				damagePerSelfHPLost += selfHP_damage;
				currentHP_lost -= selfHP_hp;
			}
			while(currentHP_lost-selfHP_hp > 0);
		}
		return damagePerSelfHPLost;
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

	//nao pode passar dos 100 TODO
	public void setHp(int hp) {
		this.hp = (hp<=100) ? hp : 100;
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

	public int getTemporaryDamageIncrease() {
		return temporaryDamageIncrease;
	}

	public void setTemporaryDamageIncrease(int temporaryDamageIncrease) {
		this.temporaryDamageIncrease = temporaryDamageIncrease;
	}

	public int getStunnedDuration() {
		return stunnedDuration;
	}

	public void setStunnedDuration(int stunnedDuration) {
		this.stunnedDuration = stunnedDuration;
		this.setStunned( (stunnedDuration>0) ? true : false );
	}

	public int getInvulDuration() {
		return invulDuration;
	}

	public void setInvulDuration(int invulDuration) {
		this.invulDuration = invulDuration;
		this.setInvul( (invulDuration>0) ? true : false );
	}

	public int getNatureGain() {
		return natureGain;
	}

	public void setNatureGain(int natureGain) {
		this.natureGain = natureGain;
	}

	public int getNatureLoss() {
		return natureLoss;
	}

	public void setNatureLoss(int natureLoss) {
		this.natureLoss = natureLoss;
	}

}
