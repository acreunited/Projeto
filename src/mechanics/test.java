package mechanics;

public class test {

	public static void main(String[] args) {
		
		Character c = new Character(4);
		//System.out.println(c.getHp());
		
		System.out.println(c.getAbility1().getCooldown());
		System.out.println(c.getAbility2().getCooldown());
		System.out.println(c.getAbility3().getCooldown());
		System.out.println(c.getAbility4().getCooldown());
		
		
		System.out.println(c.getAbility1().getBecomeInvulDuration());
		System.out.println(c.getAbility2().getTargetClick());
		System.out.println(c.getAbility3().getDamageIncreasePerUse());
		System.out.println(c.getAbility4().getPermanentDamageIncrease());
	
	}

}
