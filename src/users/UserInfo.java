package users;

public class UserInfo {
	
	
	public static int getLevel(int xp) {
		if (xp<200) {
			return 1;
		}
		else {
			return 2;
		}
	}
	public static String getLevel(String xp) {
		if (Integer.parseInt(xp)<200) {
			return "1";
		}
		else {
			return "2";
		}
	}
	
	public static String getWinPercentage(String w, String l) {
		
		int wins = Integer.parseInt(w);
		int losses = Integer.parseInt(l);
		
		if (wins==0) {
			return "0";
		}
		
		float result = (wins*100)/(wins+losses);
		
		return ""+result;
	}

}
