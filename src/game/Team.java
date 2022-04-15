package game;

public class Team {
	
	private String id, char1, char2, char3;
	
	public Team(String id, String char1, String char2, String char3) {
		this.id = id;
		this.char1 = char1;
		this.char2 = char2;
		this.char3 = char3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChar1() {
		return char1;
	}

	public void setChar1(String char1) {
		this.char1 = char1;
	}

	public String getChar2() {
		return char2;
	}

	public void setChar2(String char2) {
		this.char2 = char2;
	}

	public String getChar3() {
		return char3;
	}

	public void setChar3(String char3) {
		this.char3 = char3;
	}

}
