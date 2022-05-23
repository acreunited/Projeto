package mechanics;

public class test {

	public static void main(String[] args) {
		
		Character c = new Character(10);
		//System.out.println(c.getHp());
		
		System.out.println("Target: "+c.getAbility1().getActiveTarget());
		System.out.println("Text: "+c.getAbility1().getActiveDescription());
		System.out.println("Duration: "+c.getAbility1().getActiveDuration());
	
	}

}
