package game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mechanics.Ability;
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
			createAbilitiesHashtable(id);
			
			GameUtils.semQuick.release();

			
		}
		else if (metodo.equalsIgnoreCase("lock")) {
			
			gameInfo.lock((String) session.getAttribute("uuid"));
			int oppID = (int) session.getAttribute("opp_id");
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
				
				calculateAbilities(oppChar1, oppChar2, oppChar3, thisChar1, thisChar2, thisChar3, oppID );
				writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
				
				
				
				updateNatureInGame(session, pw);
				
			}
			
			session.setAttribute("turn", true);
			
		}
		else if (metodo.equalsIgnoreCase("unlock")) {

			response.setContentType("text/html");
			
			String uuid = (String) session.getAttribute("uuid");
			
			calculateAbilities(thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, id);
			writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
			
			checkActiveSkills(pw, id, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, session);
			
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void calculateAbilities(Character thisChar1, Character thisChar2, Character thisChar3, Character oppChar1,
			Character oppChar2, Character oppChar3, int id) {

		ArrayList<String> allAbilitiesUsed = GameUtils.activeAbilitiesUsed.get(id);
		ArrayList<String> allCharsUsedSkill = GameUtils.activeCharsUsedSkill.get(id);
		ArrayList<String> allTargets = GameUtils.activeTargets.get(id);
		ArrayList<String> allAllyEnemy = GameUtils.activeAllyEnemy.get(id);
		ArrayList<String> allAbilitiesID = GameUtils.activeAbilitiesUsed.get(id);
		
		for (int i = 0; i < allAbilitiesUsed.size(); i++) {

			Character c = getCharacterUsed(allCharsUsedSkill.get(i), thisChar1, thisChar2, thisChar3);
			
			Character target = getTarget(allAllyEnemy.get(i), allTargets.get(i), 
					thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);
			
			if (allAbilitiesUsed.get(i).equalsIgnoreCase("0")) {
				c.applyAbility(c.getAbility1(), target);
			}
			else if (allAbilitiesUsed.get(i).equalsIgnoreCase("1")) {
				c.applyAbility(c.getAbility2(), target);
			}
			else if (allAbilitiesUsed.get(i).equalsIgnoreCase("2")) {
				c.applyAbility(c.getAbility3(), target);
			}
			else if (allAbilitiesUsed.get(i).equalsIgnoreCase("3")) {
				c.applyAbility(c.getAbility4(), target);
			}

		}

	
	}



	private void createAbilitiesHashtable(int sessionID) {
		GameUtils.activeAbilitiesUsed.put( sessionID, new ArrayList<String>() );
		GameUtils.activeCharsUsedSkill.put( sessionID, new ArrayList<String>() );
		GameUtils.activeTargets.put( sessionID, new ArrayList<String>() );
		GameUtils.activeAllyEnemy.put( sessionID, new ArrayList<String>() );
		GameUtils.activeAbilitiesID.put( sessionID, new ArrayList<String>() );
	}
	
	@SuppressWarnings("unchecked")
	private void checkActiveSkills(PrintWriter pw, int id, Character this1, Character this2, Character this3,
			Character opp1, Character opp2, Character opp3, HttpSession session) {

		ArrayList<String> activeThisChar1 = (ArrayList<String>) session.getAttribute("activeThisChar1");
		ArrayList<String> activeThisChar2 = (ArrayList<String>) session.getAttribute("activeThisChar2");
		ArrayList<String> activeThisChar3 = (ArrayList<String>) session.getAttribute("activeThisChar3");
		ArrayList<String> activeOppChar1 = (ArrayList<String>) session.getAttribute("activeOppChar1");
		ArrayList<String> activeOppChar2 = (ArrayList<String>) session.getAttribute("activeOppChar2");
		ArrayList<String> activeOppChar3 = (ArrayList<String>) session.getAttribute("activeOppChar3");
		
		ArrayList<String> allAbilitiesUsed = GameUtils.activeAbilitiesUsed.get(id);
		ArrayList<String> allCharsUsedSkill = GameUtils.activeCharsUsedSkill.get(id);
		ArrayList<String> allTargets = GameUtils.activeTargets.get(id);
		ArrayList<String> allAllyEnemy = GameUtils.activeAllyEnemy.get(id);
		ArrayList<String> allAbilitiesID = GameUtils.activeAbilitiesID.get(id);
		
		ArrayList<String> removeStringID = new ArrayList<String>();
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();

		if (allAbilitiesUsed.size()>0) {
			
			for (String s : allAbilitiesUsed) {
				System.out.println("ah " + s);
			}
			
			for (int i = 0; i < allAbilitiesUsed.size(); i++) {
				System.out.println("fez");
				System.out.println("habilidades usadas "+allAbilitiesUsed.get(i));
				Character c = getCharacterUsed(allCharsUsedSkill.get(i), this1, this2, this3);

				boolean delete = false;
				
				Ability a = null; 
		
				if (allAbilitiesUsed.get(i).equalsIgnoreCase("0")) {
					delete = shouldRemoveActive(c.getAbility1());
					a = c.getAbility1();
				}
				else if (allAbilitiesUsed.get(i).equalsIgnoreCase("1")) {
					delete = shouldRemoveActive(c.getAbility2());
					a = c.getAbility2();
				}
				else if (allAbilitiesUsed.get(i).equalsIgnoreCase("2")) {
					delete = shouldRemoveActive(c.getAbility3());
					a = c.getAbility3();
				}
				else if (allAbilitiesUsed.get(i).equalsIgnoreCase("3")) {
					delete = shouldRemoveActive(c.getAbility4());
					a = c.getAbility4();
				}
				
			
				if (delete) {

					removeIndex.add(i);
					
					String s = allAbilitiesID.get(i);
					activeThisChar1.removeIf(name -> name.contains(s));
					activeThisChar2.removeIf(name -> name.contains(s));
					activeThisChar3.removeIf(name -> name.contains(s));
					activeOppChar1.removeIf(name -> name.contains(s));
					activeOppChar2.removeIf(name -> name.contains(s));
					activeOppChar3.removeIf(name -> name.contains(s));

				}
				else {
					String resposta = "";
					
					if (allAllyEnemy.get(i).trim().equalsIgnoreCase("ally")) {
						resposta += "\n<div class='effects_border0 zindex1'>";
						resposta += "\n<img src='ViewAbility?id="+allAbilitiesID.get(i)+"' id='activeSkill"+allAbilitiesID.get(i)+"' onmouseover='seeActiveSkill("+allAbilitiesID.get(i)+")' onmouseleave='hideActiveSkill()'>";
						resposta += "\n<span class='tooltiptext' id='tooltiptext"+allAbilitiesID.get(i)+"'>";

						resposta += "\n<span class='tooltiptextname'>"+"TODO NOME TODO"+"</span>";
						resposta += "\n<span class='tooltiptextdesc'>"+a.getActiveDescription()+"</span>";
						resposta += "\n<span class='tooltiptextduration'>"+a.getActiveDuration()+"TURN LEFT</span>";
						resposta += "\n</span>";
						resposta += "\n</div>";

						
						System.out.println("TARGET ALLY: "+allTargets.get(i));
						if (allTargets.get(i).equalsIgnoreCase("0")) {
							activeThisChar1.add(resposta);
						}
						else if (allTargets.get(i).equalsIgnoreCase("1")) {
							activeThisChar2.add(resposta);
						}
						else if (allTargets.get(i).equalsIgnoreCase("2")) {
							activeThisChar3.add(resposta);
						}
						else {
							System.out.println("ALLY GONE WRONG");
						}
					}
					else if (allAllyEnemy.get(i).trim().equalsIgnoreCase("enemy")) {
						resposta += "\n<div class='effects_border1 zindex0'>";
						resposta += "\n<img src='ViewAbility?id="+allAbilitiesID.get(i)+"' id='activeSkill"+allAbilitiesID.get(i)+"' onmouseover='seeActiveSkillEnemy("+allAbilitiesID.get(i)+")' onmouseleave='hideActiveSkillEnemy()'>";
						resposta += "\n<span class='tooltiptext1' id='tooltiptext"+allAbilitiesID.get(i)+"'>";
					
						resposta += "\n<span class='tooltiptextname'>"+"TODO NOME TODO"+"</span>";
						resposta += "\n<span class='tooltiptextdesc'>"+a.getActiveDescription()+"</span>";
						resposta += "\n<span class='tooltiptextduration'>"+a.getActiveDuration()+"TURN LEFT</span>";
						resposta += "\n</span>";
						resposta += "\n</div>";
					
						if (allTargets.get(i).equalsIgnoreCase("1")) {
							activeOppChar1.add(resposta);
						}
						else if (allTargets.get(i).equalsIgnoreCase("2")) {
							activeOppChar2.add(resposta);
						}
						else if (allTargets.get(i).equalsIgnoreCase("3")) {
							activeOppChar3.add(resposta);
						}
						else {
							System.out.println("ENEMY GONE WRONG");
						}
					}
				}
			}
		}

		removeIfExists(removeIndex, id);
		
		writeActiveSkills(pw, activeThisChar1, activeThisChar2, activeThisChar3, activeOppChar1, activeOppChar2, activeOppChar3, session);
		removeStringID.clear();
	}
	
	private void removeIfExists(ArrayList<Integer> removeIndex, int id) {
		
		if (removeIndex.size()>0) {
			int size = GameUtils.activeAbilitiesUsed.get(id).size();
			
			String[] arrayAbilitiesUsed = new String[size];
			GameUtils.activeAbilitiesUsed.get(id).toArray(arrayAbilitiesUsed);
			String[] arrayCharsUsedSkill = new String[size];
			GameUtils.activeCharsUsedSkill.get(id).toArray(arrayCharsUsedSkill);
			String[] arrayTargets = new String[size];
			GameUtils.activeTargets.get(id).toArray(arrayTargets);
			String[] arrayAllyEnemy = new String[size];
			GameUtils.activeAllyEnemy.get(id).toArray(arrayAllyEnemy);
			String[] arrayAbilitiesID = new String[size];
			GameUtils.activeAbilitiesID.get(id).toArray(arrayAbilitiesID);
		
			for (int i = 0; i < size; i++) {
				for (int rem : removeIndex) {
					if (rem==i) {
						arrayAbilitiesUsed[i] = null;
						arrayCharsUsedSkill[i] = null;
						arrayTargets[i] = null;
						arrayAllyEnemy[i] = null;
						arrayAbilitiesID[i] = null;
					}
				}
			}
			
			ArrayList<String> copyAbilitiesUsed = new ArrayList<String>();
			ArrayList<String> copyCharsUsedSkill = new ArrayList<String>();
			ArrayList<String> copyTargets = new ArrayList<String>();
			ArrayList<String> copyAllyEnemy = new ArrayList<String>();
			ArrayList<String> copyAbilitiesID = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				if (arrayAbilitiesUsed[i]!=null) {
					copyAbilitiesUsed.add(arrayAbilitiesUsed[i]);
					copyCharsUsedSkill.add(arrayCharsUsedSkill[i]);
					copyTargets.add(arrayTargets[i]);
					copyAllyEnemy.add(arrayAllyEnemy[i]);
					copyAbilitiesID.add(arrayAbilitiesID[i]);
				}
			}
			GameUtils.activeAbilitiesUsed.put(id, copyAbilitiesUsed);
			GameUtils.activeAbilitiesUsed.put(id, copyAbilitiesUsed);
			GameUtils.activeCharsUsedSkill.put(id, copyCharsUsedSkill);
			GameUtils.activeTargets.put(id, copyTargets);
			GameUtils.activeAllyEnemy.put(id, copyAllyEnemy);
			GameUtils.activeAbilitiesID.put(id, copyAbilitiesID);
			
		}
	}

	
	
	private void writeActiveSkills(PrintWriter pw, ArrayList<String> activeThisChar1, ArrayList<String> activeThisChar2 , ArrayList<String> activeThisChar3 ,
			ArrayList<String> activeOppChar1, ArrayList<String> activeOppChar2, ArrayList<String> activeOppChar3, HttpSession session) {
		
		pw.write("break");
		for (String s : activeThisChar1) {
			if (s!=null) {
				pw.write(s);
			}
		}
		pw.write("break");
		for (String s : activeThisChar2) {
			if (s!=null) {
				pw.write(s);
			}
		}
		pw.write("break");
		for (String s : activeThisChar3) {
			if (s!=null) {
				pw.write(s);
			}
		}
		pw.write("break");
		for (String s : activeOppChar1) {
			if (s!=null) {
				pw.write(s);
			}
		}
		pw.write("break");
		for (String s : activeOppChar2) {
			if (s!=null) {
				pw.write(s);
			}
		}
		pw.write("break");
		for (String s : activeOppChar3) {
			if (s!=null) {
				pw.write(s);
			}
		}

		session.setAttribute("activeThisChar1", activeThisChar1);
		session.setAttribute("activeThisChar2", activeThisChar2);
		session.setAttribute("activeThisChar3", activeThisChar3);
		session.setAttribute("activeOppChar1", activeOppChar1);
		session.setAttribute("activeOppChar2", activeOppChar2);
		session.setAttribute("activeOppChar3", activeOppChar3);
		
	}

	private boolean shouldRemoveActive(Ability a) {
		
		int id = a.getId();
		
		if (a.getActiveDuration()==null || a.getActiveDuration().get(0).length()==0) {
			return true;
		}
		else if (a.getActiveDuration().get(0).equalsIgnoreCase("permanent")) {
			return false;
		}
		else {
			int duration = Integer.parseInt( a.getActiveDuration().get(0) );
			
			if (duration < 1) {
				Ability restart = new Ability(id);
				ArrayList<String> arr = new ArrayList<String>();
				int x = Integer.parseInt( restart.getActiveDuration().get(0) );
				arr.add(""+x);
				a.setActiveDuration( arr );
				return true;
			}
			else {
				ArrayList<String> arr = new ArrayList<String>();
				int x = Integer.parseInt( a.getActiveDuration().get(0) );
				x--;
				arr.add(""+x);
				a.setActiveDuration( arr );
				return false;
			}
		}		
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
	
		session.setAttribute("activeThisChar1", new ArrayList<String>());
		session.setAttribute("activeThisChar2", new ArrayList<String>());
		session.setAttribute("activeThisChar3", new ArrayList<String>());
		session.setAttribute("activeOppChar1", new ArrayList<String>());
		session.setAttribute("activeOppChar2", new ArrayList<String>());
		session.setAttribute("activeOppChar3", new ArrayList<String>());

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
