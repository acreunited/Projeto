package users;

public class UserInfo {
	
	
	public int getLevel(int xp) {
		if (xp<200) {
			return 1;
		}
		else {
			return 2;
		}
	}
	public String getLevel(String xp) {
		if (Integer.parseInt(xp)<200) {
			return "1";
		}
		else {
			return "2";
		}
	}

}
