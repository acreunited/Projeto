package game;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mechanics.Ability;
import mechanics.Character;


@WebServlet("/AbilityActions")
public class AbilityActions extends HttpServlet {
	
	private static final long serialVersionUID = 7215979604673189309L;

	public GamesInfo gameInfo;
	
	public AbilityActions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
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
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("seeTarget")) {
			
			int abilityPos = Integer.parseInt( request.getParameter("abilityPos") );
			int selfChar = Integer.parseInt( request.getParameter("selfChar") );

			Ability a = getSelectedAbility(abilityPos, selfChar, thisChar1, thisChar2, thisChar3);
			
			if (a!=null) {
				if (a.isIgnoresInvul()) {
					pw.write(a.getTargetClick()+"-false-false-false");
				}
				else {
					pw.write(a.getTargetClick()
							+"-"+oppChar1.isInvul()+"-"+oppChar2.isInvul()+"-"+oppChar3.isInvul());
				}
				
			}
			else {
				System.out.println("HABILIDADE NULL");
			}
		
		}

		else if (action.equalsIgnoreCase("applyAbility")) {
			//System.out.println("chegou servlet apply ability");
			String abilityUsedID = request.getParameter("abilityUsedID");
			String allyEnemy = request.getParameter("allyEnemy");
			
			if (abilityUsedID.equalsIgnoreCase("null")) {
				pw.println("nada");
			}
			else {
				
				if (allyEnemy.trim().equalsIgnoreCase("ally")) {
					pw.println("<div class='effects_border0 zindex1'>");
				}
				else if (allyEnemy.trim().equalsIgnoreCase("enemy")) {
					pw.println("<div class='effects_border1 zindex0'>");
				}
				else {
					System.out.println("ERROR ALLYENEMY APPLY-ABILITY ABILITYACTIONS.JAVA");
				}

				pw.println("<img src='ViewAbility?id="+abilityUsedID+"' id='activeSkill"+abilityUsedID+"' onmouseover='seeActiveSkill(id)' onmouseleave='hideActiveSkill()'>");
//				pw.println("<span class='tooltiptext' id='tooltiptextid'>");
//					pw.println("<span class='tooltiptextname'>SPRINKLING NEEDLES</span>");
//					pw.println("<span class='tooltiptextdesc'>This character will take 10 damage.</span>");
//					pw.println("<span class='tooltiptextduration'>1 TURN LEFT</span>");
//				pw.println("</span>");
				pw.println("</div>");
				
				pw.println("break");
				updateNaturesThisAbility(session, pw, new Ability(Integer.parseInt( abilityUsedID )));
			
			}
		
		}
		else if (action.equalsIgnoreCase("saveAbilities")) {
			
			saveAbilities(request, session, id, thisChar1, thisChar2, thisChar3);
		}
		else if (action.equalsIgnoreCase("seeNatureCost")) {
			int selfChar = Integer.parseInt( request.getParameter("selfChar") );
			int abilityPos = Integer.parseInt( request.getParameter("abilityPos") );
			
			Ability a = getSelectedAbility(abilityPos, selfChar, thisChar1, thisChar2, thisChar3);
			abilityCost(pw, a);
		}
		else if (action.equalsIgnoreCase("abilityHasNature")) {
			checkCost(session, pw, thisChar1);
			checkCost(session, pw, thisChar2);
			checkCost(session, pw, thisChar3);
		}
		else if (action.equalsIgnoreCase("cancelAbility")) {
			int abilityID = Integer.parseInt( request.getParameter("id") );
			regainAbilityCost(session, pw, new Ability(abilityID));
		}
		
		
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void regainAbilityCost(HttpSession session, PrintWriter pw, Ability a) {
		int newTaijutsu = (int) session.getAttribute("taijutsu") + a.getnTaijutsu();
		int newHeart = (int) session.getAttribute("heart") + a.getnHeart();
		int newEnergy = (int) session.getAttribute("energy") + a.getnEnergy();
		int newSpirit = (int) session.getAttribute("spirit") + a.getnSpirit();
		int newRandom = (newTaijutsu+newHeart+newEnergy+newSpirit) + a.getnRandom();
		
		updateNatureInGame(session, pw, newTaijutsu, newHeart, newEnergy, newSpirit, newRandom);
	}
	
	private void updateNaturesThisAbility(HttpSession session, PrintWriter pw, Ability a) {
		int newTaijutsu = (int) session.getAttribute("taijutsu") - a.getnTaijutsu();
		int newHeart = (int) session.getAttribute("heart") - a.getnHeart();
		int newEnergy = (int) session.getAttribute("energy") - a.getnEnergy();
		int newSpirit = (int) session.getAttribute("spirit") - a.getnSpirit();
		int newRandom = (newTaijutsu+newHeart+newEnergy+newSpirit) - a.getnRandom();
		
		updateNatureInGame(session, pw, newTaijutsu, newHeart, newEnergy, newSpirit, newRandom);
	}
	
	private void updateNatureInGame(HttpSession session, PrintWriter pw,
			int newTaijutsu, int newHeart, int newEnergy, int newSpirit, int newRandom) {
		
		System.out.println("ANTES: ");
		System.out.println(session.getAttribute("taijutsu"));
		System.out.println(session.getAttribute("heart"));
		System.out.println(session.getAttribute("energy"));
		System.out.println(session.getAttribute("spirit"));
		System.out.println(session.getAttribute("random"));
		
		session.setAttribute("taijutsu", newTaijutsu);
		session.setAttribute("heart", newHeart);
		session.setAttribute("energy", newEnergy);
		session.setAttribute("spirit", newSpirit);
		session.setAttribute("random", newRandom);
		
		pw.println(" <strong class=\"energy0\">x"+session.getAttribute("taijutsu")+"</strong>");
		pw.println(" <strong class=\"energy1\">x"+session.getAttribute("heart")+"</strong>");
		pw.println(" <strong class=\"energy2\">x"+session.getAttribute("energy")+"</strong>");
		pw.println(" <strong class=\"energy3\">x"+session.getAttribute("spirit")+"</strong>");
		pw.println(" <strong class=\"energy4\">x"+session.getAttribute("random")+"</strong>");
		
		System.out.println("DEPOIS: ");
		System.out.println(session.getAttribute("taijutsu"));
		System.out.println(session.getAttribute("heart"));
		System.out.println(session.getAttribute("energy"));
		System.out.println(session.getAttribute("spirit"));
		System.out.println(session.getAttribute("random"));
		
	}
	
	private void checkCost(HttpSession session, PrintWriter pw, Character c) {
		
		pw.println( canUse(session, c.getAbility1()) + "-");
		pw.println( canUse(session, c.getAbility2())  + "-");
		pw.println( canUse(session, c.getAbility3())  + "-");
		pw.println( canUse(session, c.getAbility4())  + "-");
		
	}
	
	private boolean canUse(HttpSession session, Ability a) {

		int taijutsu = (int) session.getAttribute("taijutsu");
		int heart = (int) session.getAttribute("heart");
		int energy = (int) session.getAttribute("energy");
		int spirit = (int) session.getAttribute("spirit");
		int random = (int) session.getAttribute("random");
		
		boolean hasTaijutsu = taijutsu >= a.getnTaijutsu();
		boolean hasHeart = heart >= a.getnHeart();
		boolean hasEnergy = energy >= a.getnEnergy();
		boolean hasSpirit = spirit >= a.getnSpirit();
		boolean hasRandom = random >= a.getnRandom();
		
		if (hasTaijutsu&&hasHeart&&hasEnergy&&hasSpirit&&hasRandom) {
			return true;
		}
				
		return false;
	}
	
	private void abilityCost(PrintWriter pw, Ability a) {
		
		if (a.getnTaijutsu()>0) {
			for (int i = 0; i < a.getnTaijutsu(); i++) {
				pw.println("<img src='battle/Taijutsu.png'>");
			}
		}
		if (a.getnHeart()>0) {
			for (int i = 0; i < a.getnHeart(); i++) {
				pw.println("<img src='battle/Heart.png'>");
			}
		}
		if (a.getnEnergy()>0) {
			for (int i = 0; i < a.getnEnergy(); i++) {
				pw.println("<img src='battle/Energy.png'>");
			}
		}
		if (a.getnSpirit() > 0) {
			for (int i = 0; i < a.getnSpirit(); i++) {
				pw.println("<img src='battle/Spirit.png'>");
			}
		}
		if (a.getnRandom()>0) {
			for (int i = 0; i < a.getnRandom(); i++) {
				pw.println("<img src='battle/Random.png'>");
			}
		}
	}

	private Ability getSelectedAbility(int abilityPos, int selfChar, Character thisChar1,Character thisChar2,Character thisChar3) {
		Character c = null;
		Ability a = null;
		
		if (selfChar==0) {
			c = thisChar1;
		}
		else if (selfChar==1) {
			c = thisChar2;
		}
		else if (selfChar==2) {
			c = thisChar3;
		}
		
		if (abilityPos==0) {
			a = c.getAbility1();
		}
		else if (abilityPos==1) {
			a = c.getAbility2();
		}
		else if (abilityPos==2) {
			a = c.getAbility3();
		}
		else if (abilityPos==3) {
			a = c.getAbility4();
		}
		return a;
	}
	
	private void saveAbilities(HttpServletRequest request, HttpSession session, int id, Character thisChar1,Character thisChar2,Character thisChar3) {
		String uuid = (String) session.getAttribute("uuid");
		
		String allAbilitiesUsed = request.getParameter("allAbilitiesUsed");
		String allCharsUsedSkill = request.getParameter("allCharsUsedSkill");
		String allTargets = request.getParameter("allTargets");
		String allAllyEnemy = request.getParameter("allAllyEnemy");
		String allAbilitiesID = request.getParameter("allAbilitiesID");
		
		String[] abilitiesUsed = allAbilitiesUsed.split(",");
		String[] charsUsedSkill = allCharsUsedSkill.split(",");
		String[] targets = allTargets.split(",");
		String[] allyEnemy = allAllyEnemy.split(",");
		String[] abilitiesID = allAbilitiesID.split(",");
	
		for (int i = 0; i < abilitiesUsed.length; i++) {
			if (abilitiesUsed[i].length() > 0) {
//				int abilityPos = Integer.parseInt( request.getParameter("abilityPos") );
//				int selfChar = Integer.parseInt( request.getParameter("selfChar") );
				
				//Character c = getCharacterUsed(charsUsedSkill[i], thisChar1, thisChar2, thisChar3);
				Ability a = getSelectedAbility(Integer.parseInt(abilitiesUsed[i]), Integer.parseInt(charsUsedSkill[i]), thisChar1, thisChar2, thisChar3);
				a.setCurrentCooldown( a.getCooldown()+1 );
				
				GameUtils.activeAbilitiesUsed.get(id).add(abilitiesUsed[i]);
				GameUtils.activeCharsUsedSkill.get(id).add(charsUsedSkill[i]);
				GameUtils.activeTargets.get(id).add(targets[i]);
				GameUtils.activeAllyEnemy.get(id).add(allyEnemy[i]);
				GameUtils.activeAbilitiesID.get(id).add(abilitiesID[i]);
				
				GameUtils.enemy_activeAbilitiesUsed.get(id).add(abilitiesUsed[i]);
				GameUtils.enemy_activeCharsUsedSkill.get(id).add(charsUsedSkill[i]);
				GameUtils.enemy_activeTargets.get(id).add(targets[i]);
				GameUtils.enemy_activeAllyEnemy.get(id).add(allyEnemy[i]);
				GameUtils.enemy_activeAbilitiesID.get(id).add(abilitiesID[i]);
			}
			
		}
	
	}
	
	
	
}
