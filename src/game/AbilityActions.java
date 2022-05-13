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
					//System.out.println("ALLY");
					pw.println("<div class='effects_border0 zindex1'>");
				}
				else if (allyEnemy.trim().equalsIgnoreCase("enemy")) {
					//System.out.println("ENEMY");
					pw.println("<div class='effects_border1 zindex0'>");
				}

				pw.println("<img src='ViewAbility?id="+abilityUsedID+"' id='activeSkill"+abilityUsedID+"' onmouseover='seeActiveSkill(id)' onmouseleave='hideActiveSkill()'>");
				pw.println("<span class='tooltiptext' id='tooltiptextid'>");
					pw.println("<span class='tooltiptextname'>SPRINKLING NEEDLES</span>");
					pw.println("<span class='tooltiptextdesc'>This character will take 10 damage.</span>");
					pw.println("<span class='tooltiptextduration'>1 TURN LEFT</span>");
				pw.println("</span>");
				pw.println("</div>");
			
			}
		
		}
		else if (action.equalsIgnoreCase("saveAbilities")) {
			
			String uuid = (String) session.getAttribute("uuid");
			
			String[] allAbilitiesUsed = request.getParameterValues("allAbilitiesUsed");
			String[] allCharsUsedSkill = request.getParameterValues("allCharsUsedSkill");
			String[] allTargets = request.getParameterValues("allTargets");
			String[] allAllyEnemy = request.getParameterValues("allAllyEnemy");
			String[] allAbilitiesID = request.getParameterValues("allAbilitiesID");
			
			GameUtils.allAbilitiesUsed.put(uuid, allAbilitiesUsed);
			GameUtils.allCharsUsedSkill.put(uuid, allCharsUsedSkill);
			GameUtils.allTargets.put(uuid, allTargets);
			GameUtils.allAllyEnemy.put(uuid, allAllyEnemy);
			GameUtils.allAbilitiesID.put(uuid, allAbilitiesID);
//			request.getServletContext().setAttribute("allAbilitiesUsed", allAbilitiesUsed);
//			request.getServletContext().setAttribute("allCharsUsedSkill", allCharsUsedSkill);
//			request.getServletContext().setAttribute("allTargets", allTargets);
//			request.getServletContext().setAttribute("allAllyEnemy", allAllyEnemy);
//			request.getServletContext().setAttribute("allAbilitiesID", allAbilitiesID);
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
	
	
	
}
