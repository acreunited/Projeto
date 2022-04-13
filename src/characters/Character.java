package characters;

import java.util.ArrayList;

public class Character {
	
	int life = 100;
	int stun = 0;
	int piercing = 0;
	int defense = 0;
	int invulnerable = 0;
	int countDamage = 0;
	String name;
	String description;
	ArrayList<String> abilities = new ArrayList<String>();
	
	public Character(String name, String description) {
		this.name = name;
		this.description = description;

	}
	
	public void applyAbility(Character c, String abilityName) {
		//Ability.abilityName(c);
		
		if (abilityName.equals("ZangetsuSlash")) {
			 Ability.ZengetsuSlash(c);
		}
	}
	
	public void addAbility(String abilityName) {
		abilities.add(abilityName);
	}
	
	public boolean isDead() {
		return life <= 0;
	}
	public void stun(int value) {
		stun = value;
	}
	public void damage(int value) {
		life-=value;
	}
	public void bePiercing(int value) {
		piercing-=value;
	}
	public void incrementalDamage(int value, int step) {
		damage(value+step*countDamage);
		countDamage++;
	}
	
	public void next() {
		if (stun>0)
			stun--;
		if (piercing>0)
			piercing--;
		if (invulnerable>0)
			invulnerable--;
	}
	public void gainDefense(int value) {
		defense+=value;
	}
	public void beInvulnerable(int value) {
		invulnerable+=value;
	}
}
