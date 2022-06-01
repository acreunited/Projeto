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
			
			createAbilitiesHashtable(id, (int) session.getAttribute("opp_id") );
			
			GameUtils.semQuick.release();

			
		}
		else if (metodo.equalsIgnoreCase("lock")) {
			
			String uuid = (String) session.getAttribute("uuid");
			
			gameInfo.lock(uuid);
			int oppID = (int) session.getAttribute("opp_id");
			//winner
			if (GameUtils.gamesWinner.get(uuid)!=null) {
				if (GameUtils.gamesWinner.get(uuid)==id) {
					response.setContentType("text/plain");
					pw.println("winner");					
				}
				else if (GameUtils.gamesWinner.get(uuid)==oppID) {
					response.setContentType("text/plain");
					pw.println("loser");
				}
			}
			else {
				response.setContentType("text/html");
				
				reduceCooldown(thisChar1, thisChar2, thisChar3);
				
				calculateAbilities(
						oppChar1, oppChar2, oppChar3, thisChar1, thisChar2, thisChar3, 
						GameUtils.enemy_activeAbilitiesUsed.get(oppID), GameUtils.enemy_activeCharsUsedSkill.get(oppID), 
						GameUtils.enemy_activeTargets.get(oppID), GameUtils.enemy_activeAllyEnemy.get(oppID), 
						GameUtils.enemy_activeAbilitiesID.get(oppID)
						);
				
				int gain1 = thisChar1.getNatureGain();
				int gain2 = thisChar2.getNatureGain();
				int gain3 = thisChar3.getNatureGain();
				int loss1 = thisChar1.getNatureLoss();
				int loss2 = thisChar2.getNatureLoss();
				int loss3 = thisChar3.getNatureLoss();
				generateRandomNatures(session, 3 + gain1+gain2+gain3 -loss1-loss2-loss3);
				
				writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);

				checkActiveSkills(
						pw, oppID, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, session,
						GameUtils.enemy_activeAbilitiesUsed, GameUtils.enemy_activeCharsUsedSkill, 
						GameUtils.enemy_activeTargets, GameUtils.enemy_activeAllyEnemy, GameUtils.enemy_activeAbilitiesID, false
						);

				updateNatureInGame(session, pw);
				writeIfCharIsStunned(pw, thisChar1, thisChar2, thisChar3);
				
			}
			
			session.setAttribute("turn", true);
			
		}
		else if (metodo.equalsIgnoreCase("unlock")) {

			response.setContentType("text/html");
			
			String uuid = (String) session.getAttribute("uuid");
			
			calculateAbilities(
					thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, 
					GameUtils.activeAbilitiesUsed.get(id), GameUtils.activeCharsUsedSkill.get(id), GameUtils.activeTargets.get(id), 
					GameUtils.activeAllyEnemy.get(id), GameUtils.activeAbilitiesID.get(id)
					);
			
			writeResponse(pw, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3);

			checkActiveSkills(
					pw, id, thisChar1, thisChar2, thisChar3, oppChar1, oppChar2, oppChar3, session,
					GameUtils.activeAbilitiesUsed, GameUtils.activeCharsUsedSkill, GameUtils.activeTargets, 
					GameUtils.activeAllyEnemy, GameUtils.activeAbilitiesID, true
					);
			
			session.setAttribute("turn", false);

			gameInfo.unlock(uuid);
			
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

	private void reduceCooldown(Character thisChar1, Character thisChar2, Character thisChar3) {
		reduceCooldownChar(thisChar1);
		reduceCooldownChar(thisChar2);
		reduceCooldownChar(thisChar3);
	}
	private void reduceCooldownChar(Character c) {
		c.getAbility1().setCurrentCooldown( c.getAbility1().getCurrentCooldown()-1 );
		c.getAbility2().setCurrentCooldown( c.getAbility2().getCurrentCooldown()-1 );
		c.getAbility3().setCurrentCooldown( c.getAbility3().getCurrentCooldown()-1 );
		c.getAbility4().setCurrentCooldown( c.getAbility4().getCurrentCooldown()-1 );
	}
	
	private void calculateAbilities(Character thisChar1, Character thisChar2, Character thisChar3, Character oppChar1,
			Character oppChar2, Character oppChar3, ArrayList<String> allAbilitiesUsed, ArrayList<String> allCharsUsedSkill, 
			ArrayList<String> allTargets, ArrayList<String> allAllyEnemy, ArrayList<String> allAbilitiesID) {

		
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



	private void createAbilitiesHashtable(int sessionID, int oppID) {
		GameUtils.activeAbilitiesUsed.put( sessionID, new ArrayList<String>() );
		GameUtils.activeCharsUsedSkill.put( sessionID, new ArrayList<String>() );
		GameUtils.activeTargets.put( sessionID, new ArrayList<String>() );
		GameUtils.activeAllyEnemy.put( sessionID, new ArrayList<String>() );
		GameUtils.activeAbilitiesID.put( sessionID, new ArrayList<String>() );
		
		GameUtils.enemy_activeAbilitiesUsed.put( oppID, new ArrayList<String>() );
		GameUtils.enemy_activeCharsUsedSkill.put( oppID, new ArrayList<String>() );
		GameUtils.enemy_activeTargets.put( oppID, new ArrayList<String>() );
		GameUtils.enemy_activeAllyEnemy.put( oppID, new ArrayList<String>() );
		GameUtils.enemy_activeAbilitiesID.put( oppID, new ArrayList<String>() );
	}

	@SuppressWarnings("unchecked")
	private void checkActiveSkills(
			PrintWriter pw, int id, Character this1, Character this2, Character this3, Character opp1, Character opp2, Character opp3, HttpSession session, 
			Hashtable<Integer, ArrayList<String>> abilitiesUsed,  Hashtable<Integer, ArrayList<String>> charsUsedSkill,
			Hashtable<Integer, ArrayList<String>> targets, Hashtable<Integer, ArrayList<String>> allyEnemy, 
			Hashtable<Integer, ArrayList<String>> abilitiesID, boolean isUnlock) {

		ArrayList<String> activeThisChar1 = (ArrayList<String>) session.getAttribute("activeThisChar1");
		ArrayList<String> activeThisChar2 = (ArrayList<String>) session.getAttribute("activeThisChar2");
		ArrayList<String> activeThisChar3 = (ArrayList<String>) session.getAttribute("activeThisChar3");
		ArrayList<String> activeOppChar1 = (ArrayList<String>) session.getAttribute("activeOppChar1");
		ArrayList<String> activeOppChar2 = (ArrayList<String>) session.getAttribute("activeOppChar2");
		ArrayList<String> activeOppChar3 = (ArrayList<String>) session.getAttribute("activeOppChar3");
		
		ArrayList<String> allAbilitiesUsed = abilitiesUsed.get(id);
		ArrayList<String> allCharsUsedSkill = charsUsedSkill.get(id);
		ArrayList<String> allTargets = targets.get(id);
		ArrayList<String> allAllyEnemy = allyEnemy.get(id);
		ArrayList<String> allAbilitiesID = abilitiesID.get(id);
		
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();

		if (allAbilitiesUsed.size()>0) {
	
			for (int i = 0; i < allAbilitiesUsed.size(); i++) {

				Character c = null;
				
				c = (isUnlock) ? getCharacterUsed(allCharsUsedSkill.get(i), this1, this2, this3) : getCharacterUsed(allCharsUsedSkill.get(i), opp1, opp2, opp3);

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
				
				
			
				if (delete || a.getActiveDuration().get(0).equalsIgnoreCase("permanent")) {
					
					removeIndex.add(i);
					
					String s = allAbilitiesID.get(i);
					activeThisChar1.removeIf(name -> name.contains(s));
					activeThisChar2.removeIf(name -> name.contains(s));
					activeThisChar3.removeIf(name -> name.contains(s));
					activeOppChar1.removeIf(name -> name.contains(s));
					activeOppChar2.removeIf(name -> name.contains(s));
					activeOppChar3.removeIf(name -> name.contains(s));
					
					
					Ability novo = new Ability(Integer.parseInt( allAbilitiesID.get(i) ));
					//Character target = new Character(Integer.parseInt(allTargets.get(i) ));
					Character target = 
							(isUnlock) ? getTarget(allAllyEnemy.get(i), allTargets.get(i), this1, this2, this3, opp1, opp2, opp3)
									: getTarget(allAllyEnemy.get(i), allTargets.get(i), opp1, opp2, opp3, this1, this2, this3);
					
					int[] damageNovo = novo.getDamage();
					a.setDamage(damageNovo);
					int[] gainHPNovo = novo.getGainHP();
					a.setGainHP(gainHPNovo);
					int[] permanentDamageNovo = novo.getPermanentDamageIncrease();
					a.setPermanentDamageIncrease(permanentDamageNovo);
					int[] temporaryDamageNovo = novo.getTemporaryDamageIncrease();
					a.setTemporaryDamageIncrease(temporaryDamageNovo);
					int[] drNovo = novo.getDR();
					a.setDR(drNovo);
					//System.out.println("current: "+c.getDr()+"\nSubtract: "+drNovo[0]+"\nAfter: "+(c.getDr()-drNovo[0]));
					if (a.getGainDRTarget().equalsIgnoreCase("self")) {
						c.setDr( c.getDr()-drNovo[0] );
					}
					int[] gainNatureNovo = novo.getGainNature();
					a.setGainNature(gainNatureNovo);
					target.setNatureGain( target.getNatureGain() - gainNatureNovo[0] );
					
					int[] removeNatureNovo = novo.getRemoveNature();
					a.setRemoveNature(removeNatureNovo);
					target.setNatureLoss( target.getNatureLoss() - removeNatureNovo[0] );
					
					int invulNovo = novo.getBecomeInvulDuration();
					a.setBecomeInvulDuration(invulNovo);
					target.setInvul(false);
					
					int stunNovo = novo.getStunDuration();
					a.setStunDuration(stunNovo);
					target.setStunned(false);
					
					int[] ddNovo = novo.getGainDD();
					a.setGainDD(ddNovo);
					a.setCurrentDD( a.getCurrentDD()-ddNovo[0] );
					
					
					//target.setDr( target.getDr()-drNovo[0] );
//					System.out.println("a3: "+c.getAbility3().getCurrentTemporaryDamage());
//					System.out.println("n3: "+temporaryDamageNovo[0]);
//					System.out.println("final: "+ (c.getAbility3().getCurrentTemporaryDamage() - temporaryDamageNovo[0]) );
					c.getAbility1().setCurrentTemporaryDamage( c.getAbility1().getCurrentTemporaryDamage() - temporaryDamageNovo[0] );
					c.getAbility2().setCurrentTemporaryDamage( c.getAbility2().getCurrentTemporaryDamage() - temporaryDamageNovo[0] );
					c.getAbility3().setCurrentTemporaryDamage( c.getAbility3().getCurrentTemporaryDamage() - temporaryDamageNovo[0] );
					c.getAbility4().setCurrentTemporaryDamage( c.getAbility4().getCurrentTemporaryDamage() - temporaryDamageNovo[0] );
								
					
					if (a.getActiveDuration().get(0).equalsIgnoreCase("permanent")) {
						writeThis(i, a, allAbilitiesUsed, allCharsUsedSkill, allTargets, a.getActiveTarget().get(0), allAbilitiesID, isUnlock, 
								activeThisChar1, activeThisChar2,activeThisChar3, activeOppChar1, activeOppChar2, activeOppChar3);
					}
				}
				
				else {
					writeThis(i, a, allAbilitiesUsed, allCharsUsedSkill, allTargets, a.getActiveTarget().get(0), allAbilitiesID, isUnlock, 
							activeThisChar1, activeThisChar2,activeThisChar3, activeOppChar1, activeOppChar2, activeOppChar3);
				}
			}
		}
		
		removeIfExists(removeIndex, id, abilitiesUsed, charsUsedSkill, targets, allyEnemy, abilitiesID);
		writeActiveSkills(pw, activeThisChar1, activeThisChar2, activeThisChar3, activeOppChar1, activeOppChar2, activeOppChar3, session);

	}
	
	private void writeThis(
			int i, Ability a, ArrayList<String> allAbilitiesUsed, ArrayList<String> allCharsUsedSkill, ArrayList<String> allTargets, 
			String activeTarget, ArrayList<String> allAbilitiesID, boolean isUnlock, ArrayList<String> activeThisChar1,
			ArrayList<String> activeThisChar2, ArrayList<String> activeThisChar3, ArrayList<String> activeOppChar1, 
			ArrayList<String> activeOppChar2, ArrayList<String> activeOppChar3 ) {

		if (
			(activeTarget.trim().equalsIgnoreCase("ally") && isUnlock) ||
			(activeTarget.trim().equalsIgnoreCase("enemy") && !isUnlock)
				) {

			String resposta = getResponseAlly(a, i, allAbilitiesID);
			
			if (isUnlock) {
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
			else {
				if (allTargets.get(i).equalsIgnoreCase("1")) {
					activeThisChar1.add(resposta);
				}
				else if (allTargets.get(i).equalsIgnoreCase("2")) {
					activeThisChar2.add(resposta);
				}
				else if (allTargets.get(i).equalsIgnoreCase("3")) {
					activeThisChar3.add(resposta);
				}
				else {
					System.out.println("ALLY GONE WRONG");
				}
			}
			
		}
		else if (
				(activeTarget.trim().equalsIgnoreCase("enemy") && isUnlock) ||
				(activeTarget.trim().equalsIgnoreCase("ally") && !isUnlock)
				) {

			String resposta = getResponseEnemy(a, i, allAbilitiesID);
		
			
			if (isUnlock) {
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
			else {
				if (allTargets.get(i).equalsIgnoreCase("0")) {
					activeOppChar1.add(resposta);
				}
				else if (allTargets.get(i).equalsIgnoreCase("1")) {
					activeOppChar2.add(resposta);
				}
				else if (allTargets.get(i).equalsIgnoreCase("2")) {
					activeOppChar3.add(resposta);
				}
				else {
					System.out.println("ENEMY GONE WRONG");
				}
			}
			
		}
		else if ( activeTarget.trim().equalsIgnoreCase("self") ) {
			
			if (isUnlock) {

				String resposta = getResponseAlly(a, i, allAbilitiesID);
				
				if (allCharsUsedSkill.get(i).equalsIgnoreCase("0")) {
					activeThisChar1.add(resposta);
				}
				else if (allCharsUsedSkill.get(i).equalsIgnoreCase("1")) {
					activeThisChar2.add(resposta);
				}
				else if (allCharsUsedSkill.get(i).equalsIgnoreCase("2")) {
					activeThisChar3.add(resposta);
				}
				else {
					System.out.println("ALLY GONE WRONG SELF");
				}
			}
			else {

				String resposta = getResponseEnemy(a, i, allAbilitiesID);

				if (allCharsUsedSkill.get(i).equalsIgnoreCase("0")) {
					activeOppChar1.add(resposta);
				}
				else if (allCharsUsedSkill.get(i).equalsIgnoreCase("1")) {
					activeOppChar2.add(resposta);					
					}
				else if (allCharsUsedSkill.get(i).equalsIgnoreCase("2")) {
					activeOppChar3.add(resposta);
				}
				else {
					System.out.println("ENEMY GONE WRONG SELF");
				}
			}
		}

		
	}
	
	private String getResponseAlly(Ability a, int i,  ArrayList<String> allAbilitiesID) {
		
		String resposta = "";
		resposta += "\n<div class='effects_border0 zindex1'>";
		resposta += "\n<img src='ViewAbility?id="+allAbilitiesID.get(i)+"' id='activeSkill"+allAbilitiesID.get(i)+"' onmouseover='seeActiveSkill("+allAbilitiesID.get(i)+")' onmouseleave='hideActiveSkill()'>";
		resposta += "\n<span class='tooltiptext' id='tooltiptext"+allAbilitiesID.get(i)+"'>";

		resposta += "\n<span class='tooltiptextname'>"+a.getName()+"</span>";
		
		String[] descDD = a.getActiveDescription().get(0).split("a.getCurrentDD()");
		if (descDD.length>=2) {
			resposta += "\n<span class='tooltiptextdesc'>"+descDD[0]+a.getCurrentDD()+descDD[1].substring(2)+"</span>";
		}
		else {
			resposta += "\n<span class='tooltiptextdesc'>"+a.getActiveDescription().get(0)+"</span>";
		}
		
		if (a.getActiveDuration().get(0).equalsIgnoreCase("permanent")) {
			resposta += "\n<span class='tooltiptextduration'>INFINITE</span>";
		}
		else {
			int turnsLeft = Integer.parseInt(a.getActiveDuration().get(0)) + 1;
			resposta += "\n<span class='tooltiptextduration'>"+turnsLeft+" TURN LEFT</span>";
		}
		
		resposta += "\n</span>";
		resposta += "\n</div>";
		
		return resposta;
	}
	
	private String getResponseEnemy(Ability a, int i,  ArrayList<String> allAbilitiesID) {
		
		String resposta = "";
		
		resposta += "\n<div class='effects_border1 zindex0'>";
		resposta += "\n<img src='ViewAbility?id="+allAbilitiesID.get(i)+"' id='activeSkill"+allAbilitiesID.get(i)+"' onmouseover='seeActiveSkillEnemy("+allAbilitiesID.get(i)+")' onmouseleave='hideActiveSkillEnemy()'>";
		resposta += "\n<span class='tooltiptext1' id='tooltiptext"+allAbilitiesID.get(i)+"'>";
	
		resposta += "\n<span class='tooltiptextname'>"+a.getName()+"</span>";
		
		String[] descDD = a.getActiveDescription().get(0).split("a.getCurrentDD()");		
		if (descDD.length>=2) {
			resposta += "\n<span class='tooltiptextdesc'>"+descDD[0]+a.getCurrentDD()+descDD[1].substring(2)+"</span>";
		}
		else {
			resposta += "\n<span class='tooltiptextdesc'>"+a.getActiveDescription().get(0)+"</span>";
		}
		
		
		if (a.getActiveDuration().get(0).equalsIgnoreCase("permanent")) {
			resposta += "\n<span class='tooltiptextduration'>INFINITE</span>";
		}
		else {
			int turnsLeft = Integer.parseInt(a.getActiveDuration().get(0)) + 1;
			resposta += "\n<span class='tooltiptextduration'>"+turnsLeft+" TURN LEFT</span>";
		}
		
		resposta += "\n</span>";
		resposta += "\n</div>";
		
		return resposta;
	}
	
	private void removeIfExists(
			ArrayList<Integer> removeIndex, int id, Hashtable<Integer, ArrayList<String>> abilitiesUsed,  Hashtable<Integer, ArrayList<String>> charsUsedSkill,
			Hashtable<Integer, ArrayList<String>> targets, Hashtable<Integer, ArrayList<String>> allyEnemy, Hashtable<Integer, ArrayList<String>> abilitiesID) {
		
		if (removeIndex.size()>0) {
			int size = abilitiesUsed.get(id).size();
			
			String[] arrayAbilitiesUsed = new String[size];
			String[] arrayCharsUsedSkill = new String[size];
			String[] arrayTargets = new String[size];
			String[] arrayAllyEnemy = new String[size];
			String[] arrayAbilitiesID = new String[size];

			abilitiesUsed.get(id).toArray(arrayAbilitiesUsed);
			charsUsedSkill.get(id).toArray(arrayCharsUsedSkill);
			targets.get(id).toArray(arrayTargets);
			allyEnemy.get(id).toArray(arrayAllyEnemy);
			abilitiesID.get(id).toArray(arrayAbilitiesID);
		
			for (int i = 0; i < size; i++) {
				for (int rem : removeIndex) {
					if (rem==i) {
						//Ability a = new Ability(Integer.parseInt( arrayAbilitiesID[i] ));
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
			
			
			abilitiesUsed.put(id, copyAbilitiesUsed);
			abilitiesUsed.put(id, copyAbilitiesUsed);
			charsUsedSkill.put(id, copyCharsUsedSkill);
			targets.put(id, copyTargets);
			allyEnemy.put(id, copyAllyEnemy);
			abilitiesID.put(id, copyAbilitiesID);
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
	
	private String getHPColor(int hp) {
		if (hp>=75) {
			return "background-color: #3BDF3F;";
		}
		else if (hp<35) {
			return "background-color: #ff1324;";
			
		}
		else {
			return "background-color: #ffdc19;";
		}
	}

	
	private void writeResponse(PrintWriter pw, Character this1,Character this2,Character this3,
			Character opp1,Character opp2,Character opp3) {
		
			pw.println("<div id='bar_00' style='"+getHPColor(this1.getHp())+" width: "+this1.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_00' class='mc_char_card_lifetext'>");
			pw.println(this1.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id='bar_01' style='"+getHPColor(this2.getHp())+" width: "+this2.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_01' class='mc_char_card_lifetext'>");
			pw.println(this2.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id='bar_02' style='"+getHPColor(this3.getHp())+" width: "+this3.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_02' class='mc_char_card_lifetext'>");
			pw.println(this3.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id='bar_10' style='"+getHPColor(opp1.getHp())+" width: "+opp1.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_10' class='mc_char_card_lifetext'>");
			pw.println(opp1.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id='bar_10' style='"+getHPColor(opp2.getHp())+" width: "+opp2.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_10' class='mc_char_card_lifetext'>");
			pw.println(opp2.getHp()+"/100</div>");
			pw.println("break");
			pw.println("<div id='bar_10' style='"+getHPColor(opp3.getHp())+" width: "+opp3.getHp()+"%'></div>");	
			pw.println("<div id='bar_text_10' class='mc_char_card_lifetext'>");
			pw.println(opp3.getHp()+"/100</div>");
	}
	
	private Character getTarget(String allyEnemy, String pos, 
			Character thisChar1, Character thisChar2, Character thisChar3, Character oppChar1, Character oppChar2, Character oppChar3) {
		
		Character target = null; 
		
		if (allyEnemy.equalsIgnoreCase("ally")) {
			switch (pos) {
				case "0":
					target = thisChar1;
					break;
				case "1":
					target = thisChar2;
					break;
				case "2":
					target = thisChar3;
					break;
				}
		}
		else if (allyEnemy.equalsIgnoreCase("enemy")) {
			switch (pos) {
			case "1":
				target = oppChar1;
				break;
			case "2":
				target = oppChar2;
				break;
			case "3":
				target = oppChar3;
				break;
			}
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
		if (number>0) {
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
	
	private void writeIfCharIsStunned(PrintWriter pw, Character thisChar1, Character thisChar2, Character thisChar3) {
		
		pw.println("break");
		pw.println(thisChar1.isStunned()+"-"+thisChar2.isStunned()+"-"+thisChar3.isStunned());
		
		writeIfAbilityOnCooldown(pw, thisChar1, thisChar2, thisChar3);
	
	}
	
	private void writeIfAbilityOnCooldown(PrintWriter pw, Character thisChar1, Character thisChar2, Character thisChar3) {
		writeCharCooldown(pw, thisChar1);
		writeCharCooldown(pw, thisChar2);
		writeCharCooldown(pw, thisChar3);
	}
	
	private void writeCharCooldown(PrintWriter pw, Character c) {
		pw.println("break");
		pw.println(
				c.getAbility1().getCurrentCooldown()+"-"+c.getAbility2().getCurrentCooldown()+"-"+
				c.getAbility3().getCurrentCooldown()+"-"+c.getAbility4().getCurrentCooldown()
		);
	}

	private void createCharacters(HttpSession session) {
		
		session.setAttribute("this_char1_game", new Character( Integer.parseInt((String) session.getAttribute("this_char1")) ));
		session.setAttribute("this_char2_game", new Character( Integer.parseInt((String) session.getAttribute("this_char2")) ));
		session.setAttribute("this_char3_game", new Character( Integer.parseInt((String) session.getAttribute("this_char3")) ));
		session.setAttribute("opp_char1_game", new Character( Integer.parseInt((String) session.getAttribute("opp_char1")) ));
		session.setAttribute("opp_char2_game", new Character( Integer.parseInt((String) session.getAttribute("opp_char2")) ));
		session.setAttribute("opp_char3_game", new Character( Integer.parseInt((String) session.getAttribute("opp_char3")) ));
		
	}
	
	
	
}
