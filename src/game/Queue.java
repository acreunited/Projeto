package game;

public class Queue {
	
	private int player;
	private Team team;

	public Queue(int player, Team team) {
		this.player = player;
		this.team = team;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
