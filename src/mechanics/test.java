package mechanics;

public class test {

	public static void main(String[] args) {
		
		Character c = new Character(4);
		//System.out.println(c.getHp());
		
		System.out.println(c.getAbility1().getDamage()[0]);
		
		Character c2 = new Character(8);
		//System.out.println(c2.getDd());
		//System.out.println(c2.getDr());
		System.out.println(c2.getHp());
		//System.out.println(c2.getInvulDuration());
		//System.out.println(c2.getNatureGain());
		//System.out.println(c2.getNatureLoss());
		//System.out.println(c2.getStunnedDuration());
		//System.out.println(c2.getPermanentDamageIncrease());
		//System.out.println(c2.getTemporaryDamageIncrease());
		//System.out.println(c2.isStunned());
		System.out.println("--------------------------------------------------");
		c.applyAbility(c.getAbility1(), c2);
//		System.out.println(c2.getDd());
//		System.out.println(c2.getDr());
		System.out.println(c2.getHp());
//		System.out.println(c2.getInvulDuration());
//		System.out.println(c2.getNatureGain());
//		System.out.println(c2.getNatureLoss());
//		System.out.println(c2.getStunnedDuration());
//		System.out.println(c2.getPermanentDamageIncrease());
//		System.out.println(c2.getTemporaryDamageIncrease());
//		System.out.println(c2.isStunned());
	
	}

}
