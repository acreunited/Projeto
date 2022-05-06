package game;

import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class GamesInfo {
	
	private String uuid;
	private int player1;
	private int player2;
	private boolean turn;
	private int loser;
	private int winner;
	private int nPlayersLeft;

	public GamesInfo(int player1, int player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		this.loser = -1;
		this.winner = -1;
		this.nPlayersLeft = 0;
		
		String uuidCheck = this.player2+"-"+this.player1;
	
		if (this.uuidExists(uuidCheck)) {
			this.uuid = uuidCheck;
			this.turn = false;
		}
		else {
			this.uuid =  this.player1+"-"+this.player2;
			this.turn = true;
			createSemaphore();
			lock(this.uuid);
		}


	}
	
	private boolean uuidExists(String uuid) {
		Set<String> keys = GameUtils.games.keySet();
		for(String key: keys) {
			if (uuid.equalsIgnoreCase(key)) {
				return true;
			}
		}

		return false;
	}
	     
	public void createSemaphore() {
		GameUtils.games.putIfAbsent(this.getUuid(),new Semaphore(1));

	}
	
	public void lock(String gameID) {
		try {
			GameUtils.games.get(gameID).acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void unlock(String gameID) {
		GameUtils.games.get(gameID).release();
	}
	
	public void removeSemaphore(String gameID) {
		GameUtils.games.remove(gameID);
	}
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getPlayer1() {
		return player1;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	public boolean isturn() {
		return turn;
	}

	public void setturn(boolean turn) {
		this.turn = turn;
	}

	public int getLoser() {
		return this.loser;
	}

	public void setLoser(int player) {
		this.loser = player;
		this.winner = (player==player1) ? player2 : player1;
	}

	public int getWinner() {
		return this.winner;
	}

	public void setWinner(int player) {
		this.winner = player;
		this.loser = (player==player1) ? player2 : player1;
	}

	public int getnPlayersLeft() {
		return nPlayersLeft;
	}

	public void setnPlayersLeft(int nPlayersLeft) {
		this.nPlayersLeft = nPlayersLeft;
	}

}
