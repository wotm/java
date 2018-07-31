package fr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.banque.Client;
import fr.bd.AccesBD;

/**
 * Servlet qui va gerer le login du client. <br/>
 */
@WebServlet(urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletLogin() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String inLogin = request.getParameter("inLogin");
		String inPass = request.getParameter("inPass");

		// Si les informations d'authentifications sont bien renseignées
		if (inLogin != null && !inLogin.isEmpty() && inPass != null && !inPass.isEmpty()) {
			AccesBD bddInstance = AccesBD.getInstance();
			int userId = -3;
			try {
				userId = bddInstance.authentifier(inLogin, inPass);
			} catch (SQLException e) {
				request.setAttribute("authError", "Erreur d'authentification ! (" + e.getMessage() + ")");
				// on logue la pile d'erreurs à l'avenir
			}

			// Mise en session de l'id utilisateur s'il n'y a pas de problèmes particuliers
			if (userId != -1 && userId != -2) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userId", userId);
				Client user = new Client();
				try {
					user = bddInstance.selectClient((int) session.getAttribute("userId"));
				} catch (SQLException e) {
					request.setAttribute("clientDataError",
							"Erreur de récupération des informations du client ! (" + e.getMessage() + ")");
				}
				session.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
				dispatcher.forward(request, response);
				return;

			} else {
				// Gestion des erreurs d'authentification et redirection vers la page
				// d'authentification
				if (userId == -1) {
					request.setAttribute("loginError", "Il y a erreur avec le login !");
				}

				else if (userId == -2) {
					request.setAttribute("loginError", "Il y a erreur avec le mot de passe !");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		// 1 - Recupere les parametres du formulaire
		// Verifie qu'ils ne sont ni null ni vides
		// Si null ou vides => Direction page login avec un attribut message
		// place dans la request
		// Sinon, on continue
		// 2 - Se connecte a la base de donnees via fr.bd.AccesBD
		// Recupere une information via la methode authentifier de AccesBD
		// Place l'information dans le scope session
		// Part vers la page menu.jsp
		// 3 - Si un probleme ou une erreur survient lors de la recuperation des
		// donnees
		// On place un message dans la request et on va vers la page login
	}
}
