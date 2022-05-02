package mechanics;

public class Ability {
	
	private int id;
	private int nTimesUsed;
	//private String activeDescription; TODO form
	private String targetClick;
	private int cooldown;
	private boolean ignoresInvul;
	private int stunDuration;
	private int becomeInvulDuration;
	private int damageIncreasePerUse;
	private int[] damage; //0-number 1-duration
	//private int damageDuration;
	private boolean destroysDD;
	private int permanentDamageIncrease;
	private int[] removeNature;
	//private int removeNatureDuration;
	private int[] gainNature;
	//private int gainNatureDuration;
	private int[] gainDD;
	private int[] gainDR;
	private int[] damagePerHPLost; //0-damage 1 -hpLost
	private int[] gainHP;//0-hp 1-duration
	private int[] damagePerEnemyHPLost;
	private int[] temporaryDamageIncrease;
	
	public Ability(int id) {
		this.id = id;
		this.nTimesUsed = 0;
		this.targetClick = ReadAbilitiesXML.getTargetClick(this.id);
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
		
	}

	public void doDamage(Character c) {
		
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

	public int getPermanentDamageIncrease() {
		return permanentDamageIncrease;
	}

	public void setPermanentDamageIncrease(int permanentDamageIncrease) {
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


	
	
}