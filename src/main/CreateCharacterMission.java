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
		System.out.println("chegou aqui");
		try {
			HttpSession session = request.getSession(false); // Se já está a adicionar recursos e não tem sessao 
			
			//int idUtilizador = (int) session.getAttribute("idPessoa");
			// mal. Deixa que dê null pointer exception á frente
			
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

			// TODO Fazer validacao para o resto das cenas
			//CriadorDeRecursos criador = new CriadorDeRecursos();
			//String resumo = request.getParameter("Descricao");
			
			InsertIntoBD insert = new InsertIntoBD();

			InputStream inputCharPic = null; // input stream of the upload file
			int characterID = insert.createCharacter();
			
			//TODO se algo correu mal, é preciso apagar esta personagem!
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
