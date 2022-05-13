package game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mechanics.Character;


@WebServlet("/InGame")
public class InGame extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;

	public GamesInfo gameInfo;
	
	public InGame() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/plain");
		
		response.setCharacterEncoding("UTF-8");
		
		//setting the content type  
		PrintWriter pw = response.getWriter(); 
		
		HttpSession session = request.getSession();
		Character thisChar1 = (Character) session.getAttribute("this_char1_game");
		Character thisChar2 = (Character) session.getAttribute("this_char2_game");
		Character thisChar3 = (Character) session.getAttribute("this_char3_game");
		Character oppChar1 = (Character) session.getAttribute("opp_char1_game");
		Character oppChar2 = (Character) session.getAttribute("opp_char2_game");
		Character oppChar3 = (Character) session.getAttribute("opp_char3_game");

		int id = (int) session.getAttribute("userID");
		
		String metodo = request.getParameter("metodo");
		
		
		if (metodo.equalsIgnoreCase("create")) {
			
			try {
				GameUtils.semQuick.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(Map.Entry<Queue, Queue> entry : GameUtils.matchQuickFound.entrySet()) {
				Queue key = entry.getKey();
				Queue value = entry.getValue();
				
				if (key.getPlayer()==id) {
					createSetAttributes(session, key, value);
					createCharacters(session);
					break;
				}
				else if (value.getPlayer()==id) {
					createSetAttributes(session, value, key);
					createCharacters(session);
					break;
				}
			}
			GameUtils.semQuick.release();

			
		}
		else if (metodo.equalsIgnoreCase("lock")) {
			
			gameInfo.lock((String) session.getAttribute("uuid"));
			
			//winner
			if (GameUtils.gamesWinner.get((String) session.getAttribute("uuid"))!=null) {
				if (GameUtils.gamesWinner.get((String) session.getAttribute("uuid"))==id) {
					response.setContentType("text/plain");
					pw.println("winner");					
				}
				else if (GameUtils.gamesWinner.get((String) session.getAttribute("uuid"))==(int) session.getAttribute("opp_id")) {
					response.setContentType("text/plain");
					pw.println("loser");
				}
			}
			else {
				response.setContentType("text/html");
				generateRandomNatures(session, 3);
				String uuid = (String) session.getAttribute("uuid");	
				
				calculateAbilities(oppChar1, oppChar2, oppChar3, thisChar1, thisChar2, thisChar3, uuid);
				writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
				
				updateNatureInGame(session, pw);
				
			}
			
			session.setAttribute("turn", true);
			
		}
		else if (metodo.equalsIgnoreCase("unlock")) {

			response.setContentType("text/html");
			
			String uuid = (String) session.getAttribute("uuid");	
			calculateAbilities(thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, uuid);
			
			writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
			
			session.setAttribute("turn", false);

			gameInfo.unlock((String) session.getAttribute("uuid"));
			
		}
		
		else if (metodo.equalsIgnoreCase("loser")) {
			session.setAttribute("result", "loser");

			GameUtils.gamesWinner.put((String) session.getAttribute("uuid"), (int) session.getAttribute("opp_id"));
			gameInfo.unlock((String) session.getAttribute("uuid"));
		}
		else if(metodo.equalsIgnoreCase("remove")) {
			removeGame((String) session.getAttribute("uuid"), id);
		}
		
		pw.close();

	}
	
	private void calculateAbilities(Character thisChar1, Character thisChar2, Character thisChar3, Character oppChar1,
			Character oppChar2, Character oppChar3, String uuid) {
		
		
		String[] allAbilitiesUsed = GameUtils.allAbilitiesUsed.get(uuid);
		String[] allCharsUsedSkill = GameUtils.allCharsUsedSkill.get(uuid);
		String[] allTargets = GameUtils.allTargets.get(uuid);
		String[] allAllyEnemy = GameUtils.allAllyEnemy.get(uuid);
		String[] allAbilitiesID = GameUtils.allAbilitiesID.get(uuid);
		
		for (int i = 0; i < allAbilitiesUsed.length; i++) {
			Character c = getCharacterUsed(allCharsUsedSkill[i], thisChar1, thisChar2, thisChar3);
			
			Character target = getTarget(allAllyEnemy[i], allTargets[i], 
					thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
			
			if (allAbilitiesUsed[i].equalsIgnoreCase("0")) {
				c.applyAbility(c.getAbility1(), target);
			}
			else if (allAbilitiesUsed[i].equalsIgnoreCase("1")) {
				c.applyAbility(c.getAbility2(), target);
			}
			else if (allAbilitiesUsed[i].equalsIgnoreCase("2")) {
				c.applyAbility(c.getAbility3(), target);
			}
			else if (allAbilitiesUsed[i].equalsIgnoreCase("3")) {
				c.applyAbility(c.getAbility4(), target);
			}

		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	private void writeResponse(PrintWriter pw, Character this1,Character this2,Character this3,
			Character opp1,Character opp2,Character opp3) {
		
			pw.println("<div id=\"bar_00\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_00\" class=\"mc_char_card_lifetext\">");
			pw.println(this1.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id=\"bar_01\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_01\" class=\"mc_char_card_lifetext\">");
			pw.println(this2.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id=\"bar_02\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_02\" class=\"mc_char_card_lifetext\">");
			pw.println(this2.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id=\"bar_10\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_10\" class=\"mc_char_card_lifetext\">");
			pw.println(opp1.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id=\"bar_10\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_10\" class=\"mc_char_card_lifetext\">");
			pw.println(opp2.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id=\"bar_10\" style=\" background-color: #3BDF3F; width: 100%\"></div>");	
			pw.println("<div id=\"bar_text_10\" class=\"mc_char_card_lifetext\">");
			pw.println(opp3.getHp()+"/100</div>");
		
	}
	
	private Character getTarget(String allyEnemy, String pos, 
			Character thisChar1, Character thisChar2, Character thisChar3, Character oppChar1, Character oppChar2, Character oppChar3) {
		
		Character target = null; 
		
		switch (pos) {
		case "1":
			target = (allyEnemy.equalsIgnoreCase("ally")) ? thisChar1 : oppChar1;
			break;
		case "2":
			target = (allyEnemy.equalsIgnoreCase("ally")) ? thisChar2 : oppChar2;
			break;
		case "3":
			target = (allyEnemy.equalsIgnoreCase("ally")) ? thisChar3 : oppChar3;
			break;
	
		}
		
		
		return target;
	}

	private Character getCharacterUsed(String pos, Character this1, Character this2, Character this3) {
		
		Character c = null;
		
		switch (pos) {
		case "0":
			c = this1;
			break;
		case "1":
			c = this2;
			break;
		case "2":
			c = this3;
			break;
		}
		return c;
	}
	
	private void removeGame(String uuid, int id) {
		try {
			GameUtils.semQuickRemove.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (GameUtils.gamesFinish.get(uuid)>=1) {
			GameUtils.games.remove(uuid);
			
			for(Map.Entry<Queue, Queue> entry : GameUtils.matchQuickFound.entrySet()) {
				Queue key = entry.getKey();
				Queue value = entry.getValue();
				
				if (key.getPlayer()==id || value.getPlayer()==id) {
					GameUtils.matchQuickFound.remove(key);
					break;
				}
			}
			GameUtils.gamesFinish.remove(uuid);
			GameUtils.gamesWinner.remove(uuid);
		}
		else {
			GameUtils.gamesFinish.replace(uuid, GameUtils.gamesFinish.get(uuid)+1);
		}
		
		GameUtils.semQuickRemove.release();
		
	}
	
	private void createSetAttributes(HttpSession session, Queue player, Queue opp) {
		session.setAttribute("opp_id", opp.getPlayer());
		session.setAttribute("this_id", player.getPlayer());
		session.setAttribute("this_char1", player.getTeam().getChar1());
		session.setAttribute("this_char2", player.getTeam().getChar2());
		session.setAttribute("this_char3", player.getTeam().getChar3());
		session.setAttribute("opp_char1", opp.getTeam().getChar1());
		session.setAttribute("opp_char2", opp.getTeam().getChar2());
		session.setAttribute("opp_char3", opp.getTeam().getChar3());
		session.setAttribute("taijutsu", 0);
		session.setAttribute("heart", 0);
		session.setAttribute("energy", 0);
		session.setAttribute("spirit", 0);
		session.setAttribute("random", 0);
		
		gameInfo = new GamesInfo(player.getPlayer(), opp.getPlayer());
		session.setAttribute("turn", gameInfo.isturn());
		session.setAttribute("uuid", gameInfo.getUuid());
		
		if ( gameInfo.isturn()) {
			generateRandomNatures(session, 1);
		}

	}


	private void generateRandomNatures(HttpSession session, int number) {
		for (int i = 0; i < number; i++) {
			
			Random r = new Random();
			int randomInt = r.nextInt(100) + 1;
			
			if (randomInt <=25) {
				session.setAttribute("taijutsu", (int) session.getAttribute("taijutsu")+1);
			}
			else if (randomInt <= 50) {
				session.setAttribute("heart", (int) session.getAttribute("heart")+1);
			}
			else if (randomInt <=75) {
				session.setAttribute("energy", (int) session.getAttribute("energy")+1);
			}
			else {
				session.setAttribute("spirit", (int) session.getAttribute("spirit")+1);
			}			
		}
		updateRandom(session);
		
	}
	
	private void updateRandom(HttpSession session) {
		int taijutsu = (int) session.getAttribute("taijutsu");
		int heart = (int) session.getAttribute("heart");
		int energy = (int) session.getAttribute("energy");
		int spirit = (int) session.getAttribute("spirit");
		
		session.setAttribute("random", taijutsu+heart+energy+spirit);
	}
	
	private void updateNatureInGame(HttpSession session, PrintWriter pw) {
		pw.println("break");
		pw.println(" <strong class=\"energy0\">x"+session.getAttribute("taijutsu")+"</strong>");
		pw.println(" <strong class=\"energy1\">x"+session.getAttribute("heart")+"</strong>");
		pw.println(" <strong class=\"energy2\">x"+session.getAttribute("energy")+"</strong>");
		pw.println(" <strong class=\"energy3\">x"+session.getAttribute("spirit")+"</strong>");
		pw.println(" <strong class=\"energy4\">x"+session.getAttribute("random")+"</strong>");


	}

	private void createCharacters(HttpSession session) {

		int this_char1 = Integer.parseInt( (String) session.getAttribute("this_char1") );
		int this_char2 = Integer.parseInt( (String) session.getAttribute("this_char2") );
		int this_char3 = Integer.parseInt( (String) session.getAttribute("this_char3") );
		int opp_char1 = Integer.parseInt( (String) session.getAttribute("opp_char1") );
		int opp_char2 = Integer.parseInt( (String) session.getAttribute("opp_char2") );
		int opp_char3 = Integer.parseInt( (String) session.getAttribute("opp_char3") );
		
		session.setAttribute("this_char1_game", new Character(this_char1));
		session.setAttribute("this_char2_game", new Character(this_char2));
		session.setAttribute("this_char3_game", new Character(this_char3));
		session.setAttribute("opp_char1_game", new Character(opp_char1));
		session.setAttribute("opp_char2_game", new Character(opp_char2));
		session.setAttribute("opp_char3_game", new Character(opp_char3));
		
	}
	
	
	
}
