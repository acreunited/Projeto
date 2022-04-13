package characters;

public final class Ability {
	
	public static void ZengetsuSlash(Character c) {
		c.incrementalDamage(20, 5);
	}
	public static void GetsugaTenshou(Character c) {
		c.damage(40);
		c.stun(1);
	}
	public static void ZangetsuFusion(Character c) {
		c.bePiercing(3);
		c.gainDefense(15);
	}
	public static void ZangetsuDefense(Character c) {
		c.beInvulnerable(1);
	}

}
