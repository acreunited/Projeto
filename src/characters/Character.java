package characters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	
	public void ability1() {
		incrementalDamage(20, 5);
	}
	public void ability2() {
		damage(40);
		stun(1);
	}
	public void ability3() {
		bePiercing(3);
		gainDefense(15);
	}
	public void ability4() {
		beInvulnerable(1);
	}
	
	
	public void applyAbility(String abilityName) {
	
		Method metodo;
		try {
			metodo = this.getClass().getMethod(abilityName);
			try {
				metodo.invoke(this);
			} catch (IllegalAccessException e) {
		
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
			
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
		
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
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
