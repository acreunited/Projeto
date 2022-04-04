package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import DB.InsertIntoBD;


/**
 * Servlet implementation class addNewRecurso
 */
@WebServlet("/CreateCharacterMission")
@MultipartConfig(maxFileSize = 134217728) // Esta cena custou-me umas horas valentes de debug

public class CreateCharacterMission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCharacterMission() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			HttpSession session = request.getSession(false); // Se j� est� a adicionar recursos e n�o tem sessao 
			
			String characterName = request.getParameter("characterName");
			if (characterName == null || characterName.equals("null") || characterName.equals("")) {
				sendFailMessage(request, response, "Character Name cannot be null");
				return;
			}
			String characterDescription = request.getParameter("characterDescription");
			if (characterDescription == null || characterDescription.equals("null") || characterDescription.equals("")) {
				sendFailMessage(request, response, "Character Description cannot be null");
				return;
			}
			
			Part characterPic = request.getPart("charPic");
			
			String charAnime = request.getParameter("charAnime");
			
			String defaultORmission = request.getParameter("defaultmission");

			InsertIntoBD insert = new InsertIntoBD();

			InputStream inputCharPic = null; // input stream of the upload file
			int characterID = insert.createCharacter();
			
			//TODO se algo correu mal, � preciso apagar esta personagem!
			if (characterID == -1) {
				sendFailMessage(request, response, "Character Was Not Created");
				return;
			}
			
			//se personagem foi criada, precisamos adicionar ao anime correspondente
			switch (charAnime) {
			
			case "Bleach":
				insert.createBleach(characterID);
				break;
			}
			
			//create Character Theme
			if (characterPic!=null && characterPic.getSize()!=0) {
				inputCharPic = characterPic.getInputStream();
				
				insert.createThemeCharacter(characterID, 0, "name char "+characterID, null, "descricao char "+characterID);
				insert.createThemeCharacter(characterID, 1, characterName, inputCharPic, characterDescription);
			}
			
			//create abilities
			String ability1Name = request.getParameter("ability1");
			if (ability1Name == null || ability1Name.equals("null") || ability1Name.equals("")) {
				sendFailMessage(request, response, "Ability Name cannot be null");
				return;
			}
			String ability1Description = request.getParameter("ability1Description");
			if (ability1Description == null || ability1Description.equals("null") || ability1Description.equals("")) {
				sendFailMessage(request, response, "Ability Description cannot be null");
				return;
			}
			String ability1cooldown = request.getParameter("ability1cd");
			if (ability1cooldown == null || ability1cooldown.equals("null") || ability1cooldown.equals("")) {
				sendFailMessage(request, response, "Ability Cooldown cannot be null");
				return;
			}
			
			int abilityID = insert.createAbility(Integer.parseInt(ability1cooldown), characterID);
			
			Part ability1Pic = request.getPart("ability1image");
			InputStream inputAbility1 = null; 
			
			//create THEME_ABILITY
			if (ability1Pic!=null && ability1Pic.getSize()!=0) {
				inputAbility1 = ability1Pic.getInputStream();
				
				insert.createAbilityTheme(abilityID, 0, "name ability "+abilityID, null, "descricao ability "+abilityID);
				insert.createAbilityTheme(abilityID, 1, ability1Name, inputAbility1, ability1Description);
			}
			
			
			//ABILITY 2
			String ability2Name = request.getParameter("ability2");
			if (ability2Name == null || ability2Name.equals("null") || ability2Name.equals("")) {
				sendFailMessage(request, response, "Ability Name cannot be null");
				return;
			}
			String ability2Description = request.getParameter("ability2Description");
			if (ability2Description == null || ability2Description.equals("null") || ability2Description.equals("")) {
				sendFailMessage(request, response, "Ability Description cannot be null");
				return;
			}
			String ability2cooldown = request.getParameter("ability2cd");
			if (ability2cooldown == null || ability2cooldown.equals("null") || ability2cooldown.equals("")) {
				sendFailMessage(request, response, "Ability Cooldown cannot be null");
				return;
			}
			
			int ability2ID = insert.createAbility(Integer.parseInt(ability2cooldown), characterID);
			
			Part ability2Pic = request.getPart("ability2image");
			InputStream inputAbility2 = null; 
			
			//create THEME_ABILITY
			if (ability2Pic!=null && ability2Pic.getSize()!=0) {
				inputAbility2 = ability2Pic.getInputStream();
				
				insert.createAbilityTheme(ability2ID, 0, "name ability "+ability2ID, null, "descricao ability "+ability2ID);
				insert.createAbilityTheme(ability2ID, 1, ability2Name, inputAbility2, ability2Description);
			}
			
			//ABILITY 3
			String ability3Name = request.getParameter("ability3");
			if (ability3Name == null || ability3Name.equals("null") || ability3Name.equals("")) {
				sendFailMessage(request, response, "Ability Name cannot be null");
				return;
			}
			String ability3Description = request.getParameter("ability3Description");
			if (ability3Description == null || ability3Description.equals("null") || ability3Description.equals("")) {
				sendFailMessage(request, response, "Ability Description cannot be null");
				return;
			}
			String ability3cooldown = request.getParameter("ability3cd");
			if (ability3cooldown == null || ability3cooldown.equals("null") || ability3cooldown.equals("")) {
				sendFailMessage(request, response, "Ability Cooldown cannot be null");
				return;
			}
			
			int ability3ID = insert.createAbility(Integer.parseInt(ability3cooldown), characterID);
			
			Part ability3Pic = request.getPart("ability3image");
			InputStream inputAbility3 = null; 
			
			//create THEME_ABILITY
			if (ability3Pic!=null && ability3Pic.getSize()!=0) {
				inputAbility3 = ability3Pic.getInputStream();
				
				insert.createAbilityTheme(ability3ID, 0, "name ability "+ability3ID, null, "descricao ability "+ability3ID);
				insert.createAbilityTheme(ability3ID, 1, ability3Name, inputAbility3, ability3Description);
			}
			
			
			
			//ABILITY 4
			String ability4Name = request.getParameter("ability4");
			if (ability4Name == null || ability4Name.equals("null") || ability4Name.equals("")) {
				sendFailMessage(request, response, "Ability Name cannot be null");
				return;
			}
			String ability4Description = request.getParameter("ability4Description");
			if (ability4Description == null || ability4Description.equals("null") || ability4Description.equals("")) {
				sendFailMessage(request, response, "Ability Description cannot be null");
				return;
			}
			String ability4cooldown = request.getParameter("ability4cd");
			if (ability4cooldown == null || ability4cooldown.equals("null") || ability4cooldown.equals("")) {
				sendFailMessage(request, response, "Ability Cooldown cannot be null");
				return;
			}
			
			int ability4ID = insert.createAbility(Integer.parseInt(ability4cooldown), characterID);
			
			Part ability4Pic = request.getPart("ability4image");
			InputStream inputAbility4 = null; 
			
			//create THEME_ABILITY
			if (ability4Pic!=null && ability4Pic.getSize()!=0) {
				inputAbility4 = ability4Pic.getInputStream();
				
				insert.createAbilityTheme(ability4ID, 0, "name ability "+ability4ID, null, "descricao ability "+ability4ID);
				insert.createAbilityTheme(ability4ID, 1, ability4Name, inputAbility4, ability4Description);
			}
			
			
			//MISSION
			String isMission = request.getParameter("defaultmission"); 
			if (isMission.equalsIgnoreCase("Mission")) {
				String missionName = request.getParameter("missionName");
				String missionDescription = request.getParameter("missionDescription");
				String minLevel = request.getParameter("minLevel");
				Part missionMic = request.getPart("missionImage");
				InputStream inputMission = null;
				if (missionMic!=null && missionMic.getSize()!=0) {
					inputMission = missionMic.getInputStream();
					
					insert.createMission(missionName, missionDescription, inputMission, Integer.parseInt(minLevel), characterID);
				}
				else {
					sendFailMessage(request, response, "Mission Picture cannot be null");
					return;
				}
			}
			
			response.sendRedirect("index.jsp");
			
		} catch (MysqlDataTruncation e) {
			sendFailMessage(request, response, "One of the files is too big");
			e.printStackTrace();

		} catch (SQLException e) {
			sendFailMessage(request, response, "Something went wrong, fire your programmer!");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void sendFailMessage(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		if (message.equals("") || message == null) {
			message = "Error in Form";
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("error", message);

		out.println("<div class='container modal'><h3>" + message + "</h3></div>");

		RequestDispatcher rd = request.getRequestDispatcher("/create.jsp");
		rd.include(request, response);
		return;
	}

}
