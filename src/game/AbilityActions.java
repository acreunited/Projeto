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
				pw.write(a.getTargetClick());
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

				pw.println("<img src='ViewAbility?id="+abilityUsedID+"' id='activeSkill"+abilityUsedID+"' onmouseover='seeActiveSkill(id)' onmouseleave='hideActiveSkill()'>");
//				pw.println("<span class='tooltiptext' id='tooltiptextid'>");
//					pw.println("<span class='tooltiptextname'>SPRINKLING NEEDLES</span>");
//					pw.println("<span class='tooltiptextdesc'>This character will take 10 damage.</span>");
//					pw.println("<span class='tooltiptextduration'>1 TURN LEFT</span>");
//				pw.println("</span>");
				pw.println("</div>");
			
			}
		
		}
		else if (action.equalsIgnoreCase("saveAbilities")) {
			
			saveAbilities(request, session, id);
		}
		
	
		
		
		pw.close();

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
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
	
	private void saveAbilities(HttpServletRequest request, HttpSession session, int id) {
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
				GameUtils.activeAbilitiesUsed.get(id).add(abilitiesUsed[i]);
				GameUtils.activeCharsUsedSkill.get(id).add(charsUsedSkill[i]);
				GameUtils.activeTargets.get(id).add(targets[i]);
				GameUtils.activeAllyEnemy.get(id).add(allyEnemy[i]);
				GameUtils.activeAbilitiesID.get(id).add(abilitiesID[i]);
			}
			
		}
		
//		GameUtils.allAbilitiesUsed.put(uuid, abilitiesUsed);
//		GameUtils.allCharsUsedSkill.put(uuid, charsUsedSkill);
//		GameUtils.allTargets.put(uuid, targets);
//		GameUtils.allAllyEnemy.put(uuid, allyEnemy);
//		GameUtils.allAbilitiesID.put(uuid, abilitiesID);
	}
	
	
	
}
