package mechanics;

import java.util.ArrayList;

public class Ability {
	
	private int id;
	private int nTimesUsed;
	private String name;
	private ArrayList<String> activeTarget;
	private ArrayList<String> activeDescription;
	private ArrayList<String> activeDuration;	
	private String targetClick;
	private int cooldown;
	private boolean ignoresInvul;
	private int stunDuration;
	private int becomeInvulDuration;
	private int damageIncreasePerUse;
	private int[] damage; //0-number 1-duration
	//private int damageDuration;
	private boolean destroysDD;
	private int[] permanentDamageIncrease;
	private int[] removeNature;
	//private int removeNatureDuration;
	private int[] gainNature;
	//private int gainNatureDuration;
	private int[] gainDD;
	private int[] gainDR;
	private String gainDRTarget;
	private int[] damagePerHPLost; //0-damage 1 -hpLost
	private int[] gainHP;//0-hp 1-duration
	private int[] damagePerEnemyHPLost;
	private int[] temporaryDamageIncrease;
	private String[] temporaryDamageIncreaseTarget;
	private int currentTemporaryDamage;
	private int currentDD;
	private int currentCooldown;
	private int nTaijutsu;
	private int nHeart;
	private int nEnergy;
	private int nSpirit;
	private int nRandom;
	
	

	public Ability(int id) {
		this.currentCooldown = 0;
		this.currentTemporaryDamage = 0;
		this.currentDD = 0;
		this.id = id;
		this.nTimesUsed = 0;
		this.gainDRTarget = ReadAbilitiesXML.gainDRTarget(id);
		this.temporaryDamageIncreaseTarget = ReadAbilitiesXML.temporaryDamageIncreaseTargetWhich(id);
		this.name = ReadAbilitiesXML.getName(id);
		this.targetClick = ReadAbilitiesXML.getTargetClick(id);
		this.cooldown = ReadAbilitiesXML.getCooldown(id);
		this.ignoresInvul = ReadAbilitiesXML.ignoresInvul(id);
		this.stunDuration = ReadAbilitiesXML.stunDuration(id);
		this.becomeInvulDuration = ReadAbilitiesXML.becomeInvulnerable(id);
		this.damageIncreasePerUse = ReadAbilitiesXML.damageIncreasePerUse(id);
		this.destroysDD = ReadAbilitiesXML.destroyDD(id);
		this.permanentDamageIncrease = ReadAbilitiesXML.permanentDamageIncrease(id);
		this.damage = ReadAbilitiesXML.damage(id);
		this.removeNature = ReadAbilitiesXML.removeNature(id);
		this.gainNature = ReadAbilitiesXML.gainNature(id);
		this.gainDD = ReadAbilitiesXML.gainDD(id);
		this.gainDR = ReadAbilitiesXML.gainDR(id);
		this.damagePerHPLost = ReadAbilitiesXML.moreDamagePerHPLost(id);
		this.gainHP = ReadAbilitiesXML.gainHP(id);
		this.damagePerEnemyHPLost = ReadAbilitiesXML.moreDamageEnemyHPLost(id);
		this.temporaryDamageIncrease = ReadAbilitiesXML.temporaryDamageIncrease(id);
		this.activeTarget = ReadAbilitiesXML.getActiveTarget(id);
		this.activeDescription = ReadAbilitiesXML.getActiveDescription(id);
		this.activeDuration = ReadAbilitiesXML.getActiveDuration(id);
		this.nTaijutsu = ReadAbilitiesXML.getTaijutsu(id);
		this.nHeart = ReadAbilitiesXML.getHeart(id);
		this.nEnergy = ReadAbilitiesXML.getEnergy(id);
		this.nSpirit = ReadAbilitiesXML.getSpirit(id);
		this.nRandom = ReadAbilitiesXML.getRandom(id);
	}

	
	public int affectDD(int damage) {
		//System.out.println( "dd "+this.getCurrentDD());
		//System.out.println("math: "+ (damage - this.getCurrentDD()) );
		int retorno = damage - this.getCurrentDD();
		
		this.setCurrentDD( this.getCurrentDD()-damage );
		
		int[] update = new int[2];
		update[0] = (this.getGainDD()[0]-damage > 0) ? this.getGainDD()[0]-damage : 0;
		update[1] = this.getGainDD()[1];
		this.setGainDD(update);
		
		return retorno;
	}
	
	public int getnTaijutsu() {
		return nTaijutsu;
	}


	public void setnTaijutsu(int nTaijutsu) {
		this.nTaijutsu = nTaijutsu;
	}


	public int getnHeart() {
		return nHeart;
	}


	public void setnHeart(int nHeart) {
		this.nHeart = nHeart;
	}


	public int getnEnergy() {
		return nEnergy;
	}


	public void setnEnergy(int nEnergy) {
		this.nEnergy = nEnergy;
	}


	public int getnSpirit() {
		return nSpirit;
	}


	public void setnSpirit(int nSpirit) {
		this.nSpirit = nSpirit;
	}


	public int getnRandom() {
		return nRandom;
	}


	public void setnRandom(int nRandom) {
		this.nRandom = nRandom;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	
	public int getId() {
		return id;
	}

	public String getTargetClick() {
		return targetClick;
	}

	public void setTargetClick(String targetClick) {
		this.targetClick = targetClick;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getnTimesUsed() {
		return nTimesUsed;
	}

	public void setnTimesUsed(int nTimesUsed) {
		this.nTimesUsed = nTimesUsed;
	}

	public boolean isIgnoresInvul() {
		return ignoresInvul;
	}

	public void setIgnoresInvul(boolean ignoresInvul) {
		this.ignoresInvul = ignoresInvul;
	}

	public int getStunDuration() {
		return stunDuration;
	}

	public void setStunDuration(int stunDuration) {
		this.stunDuration = stunDuration;
	}

	public int getBecomeInvulDuration() {
		return becomeInvulDuration;
	}

	public void setBecomeInvulDuration(int becomeInvulDuration) {
		this.becomeInvulDuration = becomeInvulDuration;
	}

	public int getDamageIncreasePerUse() {
		return damageIncreasePerUse;
	}

	public void setDamageIncreasePerUse(int damageIncreasePerUse) {
		this.damageIncreasePerUse = damageIncreasePerUse;
	}

	public int[] getDamage() {
		return damage;
	}

	public void setDamage(int[] damage) {
		this.damage = damage;
	}

	public boolean isDestroysDD() {
		return destroysDD;
	}

	public void setDestroysDD(boolean destroysDD) {
		this.destroysDD = destroysDD;
	}

	public int[] getPermanentDamageIncrease() {
		return permanentDamageIncrease;
	}

	public void setPermanentDamageIncrease(int[] permanentDamageIncrease) {
		this.permanentDamageIncrease = permanentDamageIncrease;
	}

	public int[] getRemoveNature() {
		return removeNature;
	}

	public void setRemoveNature(int[] removeNature) {
		this.removeNature = removeNature;
	}

	public int[] getGainNature() {
		return gainNature;
	}

	public void setGainNature(int[] gainNature) {
		this.gainNature = gainNature;
	}

	public int[] getGainDD() {
		return gainDD;
	}

	public void setGainDD(int[] gainDD) {
		this.gainDD = gainDD;
	}

	public int[] getDR() {
		return gainDR;
	}

	public void setDR(int[] dr) {
		this.gainDR = dr;
	}

	public int[] getDamagePerHPLost() {
		return damagePerHPLost;
	}

	public void setDamagePerHPLost(int[] damagePerHPLost) {
		this.damagePerHPLost = damagePerHPLost;
	}

	public int[] getGainHP() {
		return gainHP;
	}

	public void setGainHP(int[] gainHP) {
		this.gainHP = gainHP;
	}

	public int[] getDamagePerEnemyHPLost() {
		return damagePerEnemyHPLost;
	}

	public void setDamagePerEnemyHPLost(int[] damagePerEnemyHPLost) {
		this.damagePerEnemyHPLost = damagePerEnemyHPLost;
	}

	public int[] getTemporaryDamageIncrease() {
		return temporaryDamageIncrease;
	}

	public void setTemporaryDamageIncrease(int[] temporaryDamageIncrease) {
		this.temporaryDamageIncrease = temporaryDamageIncrease;
	}
	public ArrayList<String> getActiveTarget() {
		return activeTarget;
	}

	public void setActiveTarget(ArrayList<String> activeTarget) {
		this.activeTarget = activeTarget;
	}

	public ArrayList<String> getActiveDescription() {
		return activeDescription;
	}

	public void setActiveDescription(ArrayList<String> activeDescription) {
		this.activeDescription = activeDescription;
	}

	public ArrayList<String> getActiveDuration() {
		return activeDuration;
	}

	public void setActiveDuration(ArrayList<String> activeDuration) {
		this.activeDuration = activeDuration;
	}

	public int[] getGainDR() {
		return gainDR;
	}

	public void setGainDR(int[] gainDR) {
		this.gainDR = gainDR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentTemporaryDamage() {
		return currentTemporaryDamage;
	}

	public void setCurrentTemporaryDamage(int currentTemporaryDamage) {
		this.currentTemporaryDamage = (currentTemporaryDamage > 0) ? currentTemporaryDamage : 0;
	}

	public String[] getTemporaryDamageIncreaseTarget() {
		return temporaryDamageIncreaseTarget;
	}

	public void setTemporaryDamageIncreaseTarget(String[] temporaryDamageIncreaseTarget) {
		this.temporaryDamageIncreaseTarget = temporaryDamageIncreaseTarget;
	}

	public String getGainDRTarget() {
		return gainDRTarget;
	}

	public void setGainDRTarget(String gainDRTarget) {
		this.gainDRTarget = gainDRTarget;
	}

	public int getCurrentDD() {
		return currentDD;
	}

	public void setCurrentDD(int currentDD) {
		this.currentDD = (currentDD>0) ? currentDD : 0;
	}


	public int getCurrentCooldown() {
		return currentCooldown;
	}


	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = (currentCooldown>0) ? currentCooldown : 0;
	}


	
	
}
